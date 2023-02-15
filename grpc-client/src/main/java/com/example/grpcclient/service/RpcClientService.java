package com.example.grpcclient.service;

import com.example.grpcclient.name.resolver.CustomNameResolverProvider;
import com.example.grpcclient.news.proto.ErrorInfo;
import com.example.grpcclient.news.proto.MyMemberRequest;
import com.example.grpcclient.news.proto.MyResponse;
import com.example.grpcclient.news.proto.MyServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.*;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class RpcClientService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static MyServiceGrpc.MyServiceBlockingStub blockingStub;
    private String target = "localhost:9090";

    private ManagedChannel createChannel(){
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext() //使用明文连接到服务器。 默认情况下，将使用 TLS 等安全连接机制
                .build();
        return channel;
    }

    private ManagedChannel createChannelLB(){
        NameResolverRegistry.getDefaultRegistry().register(new CustomNameResolverProvider());//dns:///grpc-server:9090
        ManagedChannel channel = ManagedChannelBuilder.forTarget("grpc-server")
                .defaultLoadBalancingPolicy("round_robin")
                .usePlaintext()
                .build();
        return channel;
    }

    /**
     * 简单rpc请求-同步
     * @throws Exception
     */
    public void sendMsgSimple() throws Exception {
        ManagedChannel channel = createChannelLB();
        try {
            blockingStub = MyServiceGrpc.newBlockingStub(channel);
            MyMemberRequest request = MyMemberRequest.newBuilder().setName("jackpot").build();
            MyResponse response = blockingStub.sendMessageSingle(request);
            logger.info("客户端接收到服务端的响应信息: {}", response.getMessage());
        } catch (StatusRuntimeException e) {//接收服务端可能发生的异常
            Status status = e.getStatus();
            Metadata trailers = e.getTrailers();
            ErrorInfo errorInfo = ErrorInfo.newBuilder().build();
            Metadata.Key<ErrorInfo> errorInfoKey = ProtoUtils.keyForProto(errorInfo);
            ErrorInfo errorInfo1 = trailers.get(errorInfoKey); //获取具体的报错信息
            throw e;
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    /**
     * 简单rpc请求-异步
     * 异步请求后立马返回一个Future对象，我们可以调用get()方法阻塞等待获取响应结果，或者添加监听器，等响应结果到达时执行回调函数；
     * @throws Exception
     */
    public void sendMsgSimpleAsync() throws Exception {
        ManagedChannel channel = createChannelLB();
        try {
            MyServiceGrpc.MyServiceFutureStub myServiceFutureStub = MyServiceGrpc.newFutureStub(channel);
            MyMemberRequest request = MyMemberRequest.newBuilder().setName("jackpot").build();
            ListenableFuture<MyResponse> responseListenableFuture = myServiceFutureStub.sendMessageSingle(request);
            responseListenableFuture.addListener(()->{
                try {
                    MyResponse response = responseListenableFuture.get();
                    logger.info("客户端接收到服务端的响应信息: {}", response.getMessage());
                }catch (InterruptedException i) {
                }catch ( ExecutionException e) {
                }
            },Executors.newFixedThreadPool(3));
            //阻塞获取响应结果
//            MyResponse response = myResponseListenableFuture.get();
//            MyResponse response = myResponseListenableFuture.get(5, TimeUnit.SECONDS); //设定阻塞超时时间
//            logger.info("客户端接收到服务端的响应信息: {}", response.getMessage());
        } catch (StatusRuntimeException e) {//接收服务端可能发生的异常
            throw e;
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    /**
     * 服务端流式调用
     * 服务器可以发送多条响应信息给客户端
     * @throws Exception
     */
    public void sendMsgServer() throws Exception {
        ManagedChannel channel = createChannel();
        try {
            blockingStub = MyServiceGrpc.newBlockingStub(channel);
            MyMemberRequest request = MyMemberRequest.newBuilder().setName("jackpot").build();
            Iterator<MyResponse> myResponseIterator = blockingStub.sendMessageServer(request);
            while (myResponseIterator.hasNext()){
                MyResponse response = myResponseIterator.next();
                logger.info("客户端接收到服务端的响应信息: {}", response.getMessage());
            }
        } catch (StatusRuntimeException e) {
            throw e;
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
    /**
     * 客户端流式调用
     * 客户端可以发送多条请求信息给服务器，服务器统一处理后响应一条数据给客户端
     * @throws Exception
     */
    public void sendMsgClient(){
        ManagedChannel channel = createChannel();
        try {
            MyServiceGrpc.MyServiceStub myServiceStub = MyServiceGrpc.newStub(channel);
            StreamObserver<MyResponse> streamObserver = new StreamObserver<MyResponse>() {
                @Override
                public void onNext(MyResponse myResponse) {
                    logger.info("接收服务端响应信息");//该模式下只会接收一次响应
                }

                @Override
                public void onError(Throwable throwable) {
                    throw new RuntimeException(throwable);
                }

                @Override
                public void onCompleted() {
                    logger.info("onCompleted");
                    channel.shutdownNow();
                }
            };
            StreamObserver<MyMemberRequest> requestObserver = myServiceStub.sendMessageClient(streamObserver);
            for (int i = 0; i < 3; i++) {
                MyMemberRequest request = MyMemberRequest.newBuilder().setName("jackpot").build();
                requestObserver.onNext(request);
            }
            requestObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            throw e;
        }
    }

    /**
     * 双向流式调用
     * 服务器和客户端都可以发送多条数据给对方，服务器接收一条数据就返回一个响应
     */
    public void sendMsgBoth(){
        ManagedChannel channel = createChannel();
        try {
            MyServiceGrpc.MyServiceStub myServiceStub = MyServiceGrpc.newStub(channel);
            StreamObserver<MyResponse> streamObserver = new StreamObserver<MyResponse>() {
                @Override
                public void onNext(MyResponse myResponse) {
                    logger.info("接收服务端响应信息");
                }

                @Override
                public void onError(Throwable throwable) {
                    throw new RuntimeException(throwable);
                }

                @Override
                public void onCompleted() {
                    logger.info("onCompleted");
                    channel.shutdownNow();
                }
            };
            StreamObserver<MyMemberRequest> requestObserver = myServiceStub.sendMessageBoth(streamObserver);
            for (int i = 0; i < 3; i++) {
                MyMemberRequest request = MyMemberRequest.newBuilder().setName("jackpot").build();
                requestObserver.onNext(request);
            }
            requestObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            throw e;
        }
    }

}
