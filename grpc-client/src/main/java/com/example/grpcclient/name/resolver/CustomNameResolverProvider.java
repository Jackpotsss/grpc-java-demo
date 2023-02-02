package com.example.grpcclient.name.resolver;

import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
@Component
public class CustomNameResolverProvider extends NameResolverProvider {

    @Autowired
    private CustomNameResolver customNameResolver;

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        return customNameResolver;
    }

    @Override
    public String getDefaultScheme() {
        return "http";
    }
}
