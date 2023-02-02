# gRPC-demo

SpringBoot、Maven项目，基于JDK11



工程目录：

grpc-server：	服务端	8081

grpc-client：	客户端	8091



分别启动两个服务后，进行测试验证

```
# 简单rpc
curl http://127.0.0.1:8091/test/rpc/client/sendMsgSimple
# 服务端流式rpc
curl http://127.0.0.1:8091/test/rpc/client/sendMsgServer
# 客户端流式rpc
curl http://127.0.0.1:8091/test/rpc/client/sendMsgClient
# 双向流式rpc
curl http://127.0.0.1:8091/test/rpc/client/sendMsgBoth
```

