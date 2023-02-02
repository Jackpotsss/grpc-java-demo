package com.example.grpcclient.name.resolver;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomNameResolver extends NameResolver {

    @Value("${grpc.hosts}")
    private String[] hosts;

    @Override
    public String getServiceAuthority() {
        return "none"; //用于验证与服务器连接的权限
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void start(Listener listener) {
        // 获取rpc地址的配置列表,地址格式 host1:8080,host2:8081
        List<EquivalentAddressGroup> addressGroups = new ArrayList<>();
        for (String host:hosts){
            String[] address = host.split(":");
            List<SocketAddress> socketAddresses = new ArrayList<SocketAddress>();
            socketAddresses.add(new InetSocketAddress(address[0], Integer.parseInt(address[1])));
            addressGroups.add(new EquivalentAddressGroup(socketAddresses));
        }
        listener.onAddresses(addressGroups, Attributes.EMPTY);
    }
}
