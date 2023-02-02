package com.example.grpcserver.server;

import com.example.grpcserver.service.NewsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 供客户端负载均衡调用
 */
@Component
public class RpcServer2 implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Server server;
    private int port = 9091;

    @Override
    public void run(String... args) throws Exception {

        server = ServerBuilder.forPort(port)
                .addService(new NewsService())
                .build()
                .start();
        server.awaitTermination();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.warn("shutting down gRPC server since JVM is shutting down");
                try {
                    if (server != null) {
                        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                logger.warn("rpc server shut down");
            }
        });
        logger.info("gRPC 服务已启动, 端口号: {} ", port);
    }

}
