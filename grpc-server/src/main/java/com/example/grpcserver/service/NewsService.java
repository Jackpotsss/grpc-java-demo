package com.example.grpcserver.service;


import com.example.grpcserver.news.proto.ErrorInfo;
import com.example.grpcserver.news.proto.MyMemberRequest;
import com.example.grpcserver.news.proto.MyResponse;
import com.example.grpcserver.news.proto.MyServiceGrpc;
import com.google.protobuf.ServiceException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsService extends MyServiceGrpc.MyServiceImplBase{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void sendMessageSingle(MyMemberRequest request, StreamObserver<MyResponse> responseObserver) {
        logger.info("rpc server start process request...");
        try{
            String name = request.getName();
            String responseMsg = name+"===";
            if(responseMsg!=null){
//                throw new ServiceException("99999");
            }
            MyResponse response =  MyResponse.newBuilder()
                    .setMessage(responseMsg).build();
            responseObserver.onNext(response);  // 返回数据，完成此次请求
            responseObserver.onCompleted();
        } catch(Throwable t) {
            if (t instanceof ServiceException) {// 业务异常，返回错误码和默认文案到客户端
                ErrorInfo errorInfo = ErrorInfo.newBuilder()
                        .setErrorCode("E0011").setDefaultMsg("rpc server has an error has occurred！").build();
                Metadata metadata = new Metadata();
                Metadata.Key<ErrorInfo> errorInfoKey = ProtoUtils.keyForProto(errorInfo);
                metadata.put(errorInfoKey,errorInfo);
                responseObserver.onError(Status.CANCELLED
                        .withCause(t).withDescription("error description...")
                        .asRuntimeException(metadata));
            }else { // 非业务异常，返回异常详情到客户端。
                responseObserver.onError(Status.UNKNOWN
                        .withDescription(t.getMessage())// 这里就是我们的自定义异常信息
                        .withCause(t)
                        .asRuntimeException());
            }
            throw new RuntimeException(t);
        }
    }

    @Override
    public void sendMessageServer(MyMemberRequest request, StreamObserver<MyResponse> responseObserver) {
        String name = request.getName();
        for (int i = 0; i < 3; i++) {
            MyResponse response =  MyResponse.newBuilder()
                    .setMessage(name+ i).build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<MyMemberRequest> sendMessageClient(StreamObserver<MyResponse> responseObserver) {

        return new StreamObserver<MyMemberRequest>(){
            String name ;
            int i = 0;
            @Override
            public void onNext(MyMemberRequest request) {
                name = request.getName();// 接收客户端的多条数据
                i++;
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(Status.UNKNOWN
                        .withDescription(throwable.getMessage())
                        .withCause(throwable)
                        .asRuntimeException());
            }

            @Override
            public void onCompleted() {//请求完成，返回客户端一个响应信息
                MyResponse response =  MyResponse.newBuilder()
                        .setMessage(name+ i).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<MyMemberRequest> sendMessageBoth(StreamObserver<MyResponse> responseObserver) {
        return new StreamObserver<MyMemberRequest>(){
            String name ;
            int i = 0;
            @Override
            public void onNext(MyMemberRequest request) {
                name = request.getName();
                i++;
                MyResponse response =  MyResponse.newBuilder()
                        .setMessage(name+ i).build();
                responseObserver.onNext(response);// 响应客户端数据，接受一个数据就响应一个数据
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(Status.UNKNOWN
                        .withDescription(throwable.getMessage())
                        .withCause(throwable)
                        .asRuntimeException());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
