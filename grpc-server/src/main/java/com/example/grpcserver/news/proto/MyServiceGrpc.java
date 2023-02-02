package com.example.grpcserver.news.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.52.1)",
        comments = "Source: news.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MyServiceGrpc {

  private MyServiceGrpc() {}

  public static final String SERVICE_NAME = "news.MyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageSingleMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "sendMessageSingle",
          requestType = com.example.grpcserver.news.proto.MyMemberRequest.class,
          responseType = com.example.grpcserver.news.proto.MyResponse.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageSingleMethod() {
    io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse> getSendMessageSingleMethod;
    if ((getSendMessageSingleMethod = MyServiceGrpc.getSendMessageSingleMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getSendMessageSingleMethod = MyServiceGrpc.getSendMessageSingleMethod) == null) {
          MyServiceGrpc.getSendMessageSingleMethod = getSendMessageSingleMethod =
                  io.grpc.MethodDescriptor.<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessageSingle"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyMemberRequest.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyResponse.getDefaultInstance()))
                          .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("sendMessageSingle"))
                          .build();
        }
      }
    }
    return getSendMessageSingleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "sendMessageServer",
          requestType = com.example.grpcserver.news.proto.MyMemberRequest.class,
          responseType = com.example.grpcserver.news.proto.MyResponse.class,
          methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageServerMethod() {
    io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse> getSendMessageServerMethod;
    if ((getSendMessageServerMethod = MyServiceGrpc.getSendMessageServerMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getSendMessageServerMethod = MyServiceGrpc.getSendMessageServerMethod) == null) {
          MyServiceGrpc.getSendMessageServerMethod = getSendMessageServerMethod =
                  io.grpc.MethodDescriptor.<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessageServer"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyMemberRequest.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyResponse.getDefaultInstance()))
                          .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("sendMessageServer"))
                          .build();
        }
      }
    }
    return getSendMessageServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "sendMessageClient",
          requestType = com.example.grpcserver.news.proto.MyMemberRequest.class,
          responseType = com.example.grpcserver.news.proto.MyResponse.class,
          methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageClientMethod() {
    io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse> getSendMessageClientMethod;
    if ((getSendMessageClientMethod = MyServiceGrpc.getSendMessageClientMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getSendMessageClientMethod = MyServiceGrpc.getSendMessageClientMethod) == null) {
          MyServiceGrpc.getSendMessageClientMethod = getSendMessageClientMethod =
                  io.grpc.MethodDescriptor.<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessageClient"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyMemberRequest.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyResponse.getDefaultInstance()))
                          .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("sendMessageClient"))
                          .build();
        }
      }
    }
    return getSendMessageClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageBothMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "sendMessageBoth",
          requestType = com.example.grpcserver.news.proto.MyMemberRequest.class,
          responseType = com.example.grpcserver.news.proto.MyResponse.class,
          methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest,
          com.example.grpcserver.news.proto.MyResponse> getSendMessageBothMethod() {
    io.grpc.MethodDescriptor<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse> getSendMessageBothMethod;
    if ((getSendMessageBothMethod = MyServiceGrpc.getSendMessageBothMethod) == null) {
      synchronized (MyServiceGrpc.class) {
        if ((getSendMessageBothMethod = MyServiceGrpc.getSendMessageBothMethod) == null) {
          MyServiceGrpc.getSendMessageBothMethod = getSendMessageBothMethod =
                  io.grpc.MethodDescriptor.<com.example.grpcserver.news.proto.MyMemberRequest, com.example.grpcserver.news.proto.MyResponse>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessageBoth"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyMemberRequest.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  com.example.grpcserver.news.proto.MyResponse.getDefaultInstance()))
                          .setSchemaDescriptor(new MyServiceMethodDescriptorSupplier("sendMessageBoth"))
                          .build();
        }
      }
    }
    return getSendMessageBothMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<MyServiceStub>() {
              @java.lang.Override
              public MyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new MyServiceStub(channel, callOptions);
              }
            };
    return MyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MyServiceBlockingStub newBlockingStub(
          io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceBlockingStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<MyServiceBlockingStub>() {
              @java.lang.Override
              public MyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new MyServiceBlockingStub(channel, callOptions);
              }
            };
    return MyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MyServiceFutureStub newFutureStub(
          io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MyServiceFutureStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<MyServiceFutureStub>() {
              @java.lang.Override
              public MyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new MyServiceFutureStub(channel, callOptions);
              }
            };
    return MyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessageSingle(com.example.grpcserver.news.proto.MyMemberRequest request,
                                  io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageSingleMethod(), responseObserver);
    }

    /**
     */
    public void sendMessageServer(com.example.grpcserver.news.proto.MyMemberRequest request,
                                  io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageServerMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyMemberRequest> sendMessageClient(
            io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendMessageClientMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyMemberRequest> sendMessageBoth(
            io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendMessageBothMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      getSendMessageSingleMethod(),
                      io.grpc.stub.ServerCalls.asyncUnaryCall(
                              new MethodHandlers<
                                      com.example.grpcserver.news.proto.MyMemberRequest,
                                      com.example.grpcserver.news.proto.MyResponse>(
                                      this, METHODID_SEND_MESSAGE_SINGLE)))
              .addMethod(
                      getSendMessageServerMethod(),
                      io.grpc.stub.ServerCalls.asyncServerStreamingCall(
                              new MethodHandlers<
                                      com.example.grpcserver.news.proto.MyMemberRequest,
                                      com.example.grpcserver.news.proto.MyResponse>(
                                      this, METHODID_SEND_MESSAGE_SERVER)))
              .addMethod(
                      getSendMessageClientMethod(),
                      io.grpc.stub.ServerCalls.asyncClientStreamingCall(
                              new MethodHandlers<
                                      com.example.grpcserver.news.proto.MyMemberRequest,
                                      com.example.grpcserver.news.proto.MyResponse>(
                                      this, METHODID_SEND_MESSAGE_CLIENT)))
              .addMethod(
                      getSendMessageBothMethod(),
                      io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
                              new MethodHandlers<
                                      com.example.grpcserver.news.proto.MyMemberRequest,
                                      com.example.grpcserver.news.proto.MyResponse>(
                                      this, METHODID_SEND_MESSAGE_BOTH)))
              .build();
    }
  }

  /**
   */
  public static final class MyServiceStub extends io.grpc.stub.AbstractAsyncStub<MyServiceStub> {
    private MyServiceStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessageSingle(com.example.grpcserver.news.proto.MyMemberRequest request,
                                  io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getSendMessageSingleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessageServer(com.example.grpcserver.news.proto.MyMemberRequest request,
                                  io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
              getChannel().newCall(getSendMessageServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyMemberRequest> sendMessageClient(
            io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
              getChannel().newCall(getSendMessageClientMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyMemberRequest> sendMessageBoth(
            io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
              getChannel().newCall(getSendMessageBothMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MyServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MyServiceBlockingStub> {
    private MyServiceBlockingStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceBlockingStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpcserver.news.proto.MyResponse sendMessageSingle(com.example.grpcserver.news.proto.MyMemberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getSendMessageSingleMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.example.grpcserver.news.proto.MyResponse> sendMessageServer(
            com.example.grpcserver.news.proto.MyMemberRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
              getChannel(), getSendMessageServerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MyServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MyServiceFutureStub> {
    private MyServiceFutureStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MyServiceFutureStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpcserver.news.proto.MyResponse> sendMessageSingle(
            com.example.grpcserver.news.proto.MyMemberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getSendMessageSingleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE_SINGLE = 0;
  private static final int METHODID_SEND_MESSAGE_SERVER = 1;
  private static final int METHODID_SEND_MESSAGE_CLIENT = 2;
  private static final int METHODID_SEND_MESSAGE_BOTH = 3;

  private static final class MethodHandlers<Req, Resp> implements
          io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE_SINGLE:
          serviceImpl.sendMessageSingle((com.example.grpcserver.news.proto.MyMemberRequest) request,
                  (io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE_SERVER:
          serviceImpl.sendMessageServer((com.example.grpcserver.news.proto.MyMemberRequest) request,
                  (io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
            io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE_CLIENT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMessageClient(
                  (io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse>) responseObserver);
        case METHODID_SEND_MESSAGE_BOTH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMessageBoth(
                  (io.grpc.stub.StreamObserver<com.example.grpcserver.news.proto.MyResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MyServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpcserver.news.proto.NewsProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MyService");
    }
  }

  private static final class MyServiceFileDescriptorSupplier
          extends MyServiceBaseDescriptorSupplier {
    MyServiceFileDescriptorSupplier() {}
  }

  private static final class MyServiceMethodDescriptorSupplier
          extends MyServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                  .setSchemaDescriptor(new MyServiceFileDescriptorSupplier())
                  .addMethod(getSendMessageSingleMethod())
                  .addMethod(getSendMessageServerMethod())
                  .addMethod(getSendMessageClientMethod())
                  .addMethod(getSendMessageBothMethod())
                  .build();
        }
      }
    }
    return result;
  }
}
