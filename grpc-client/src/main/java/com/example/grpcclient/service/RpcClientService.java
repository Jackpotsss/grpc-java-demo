package com.example.grpcclient.service;

import com.example.grpcclient.name.resolver.CustomNameResolverProvider;
import com.example.grpcclient.news.proto.ErrorInfo;
import com.example.grpcclient.news.proto.MyMemberRequest;
import com.example.grpcclient.news.proto.MyResponse;
import com.example.grpcclient.news.proto.MyServiceGrpc;
import io.grpc.*;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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

    @Autowired
    private CustomNameResolverProvider customNameResolverProvider;

    private ManagedChannel createChannelLB(){
        NameResolverRegistry.getDefaultRegistry().register(customNameResolverProvider);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .defaultLoadBalancingPolicy("round_robin")
                .usePlaintext()
                .build();
        return channel;
    }
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
