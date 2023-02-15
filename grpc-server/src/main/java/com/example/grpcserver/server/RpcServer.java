package com.example.grpcserver.server;

import com.example.grpcserver.service.NewsService;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Component
public class RpcServer implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Server server;
    private int port = 9090;

    @Override
    public void run(String... args) throws Exception {
        String address = Inet4Address.getLocalHost().getHostAddress();
        registerToConsul(address,port);
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

    private void registerToConsul(String address, int port) {
        Consul client = Consul.builder().build();
        String serviceId = "Server-" + UUID.randomUUID().toString();
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .name("grpc-server")
                .address(address)
                .port(port)
                .check(Registration.RegCheck.tcp(address + ":" + port, 10, 10))
                .tags(Collections.singletonList("server"))
                .meta(Collections.singletonMap("version", "1.0"))
                .build();
        AgentClient agentClient = client.agentClient();
        agentClient.register(service);// 向 Consul 注册服务
        logger.info("gRPC 服务已注册到Consul, 服务名称: {} ", "grpc-server");
    }

}
