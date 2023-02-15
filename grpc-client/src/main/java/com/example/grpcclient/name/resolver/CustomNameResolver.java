package com.example.grpcclient.name.resolver;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.health.ServiceHealth;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CustomNameResolver extends NameResolver {

    @Value("${grpc.hosts}")
    private String[] hosts;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
    private String authority;

    private Listener2 listener;

    private final Consul client;
    public CustomNameResolver(String authority) {
        this.authority = authority;
        this.client = Consul.builder().build();
    }

    @Override
    public String getServiceAuthority() {
        return this.authority; //用于验证与服务器连接的权限
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void start(Listener2 listener) {
        this.listener = listener;
        // 定期从注册中心获取地址
        this.executorService.scheduleAtFixedRate(this::resolve, 10, 10, TimeUnit.SECONDS);

    }

    @Override
    public void refresh() {
        this.resolve();
    }

    private void resolve() {
        logger.info("开始解析服务: {}", this.authority);
        // 从注册中心获取实例列表
        List<InetSocketAddress> addressList = getAddressList(this.authority);
        if (addressList == null || addressList.size() == 0) {
            logger.error("解析服务: {} 失败，没有可用的节点", this.authority);
            listener.onError(Status.UNAVAILABLE.withDescription("没有可用的节点"));
            return;
        }
        List<EquivalentAddressGroup> equivalentAddressGroups = addressList.stream()
                .map(EquivalentAddressGroup::new)
                .collect(Collectors.toList());
        ResolutionResult resolutionResult = ResolutionResult.newBuilder()
                .setAddresses(equivalentAddressGroups)
                .build();
        this.listener.onResult(resolutionResult);
    }

    /**
     * 从 Consul 获取健康的服务
     *
     * @param serviceName 服务名称
     * @return 服务实例列表
     */
    private List<InetSocketAddress> getAddressList(String serviceName) {
        HealthClient healthClient = client.healthClient();
        ConsulResponse<List<ServiceHealth>> response = healthClient.getHealthyServiceInstances(serviceName);
        List<ServiceHealth> healthList = response.getResponse();
        logger.info("从 Consul 中获取到服务: {} 共: {} 个实例", this.authority, healthList.size());
        return healthList.stream()
                .map(ServiceHealth::getService)
                .map(service -> new InetSocketAddress(service.getAddress(), service.getPort()))
                .collect(Collectors.toList());
    }
}
