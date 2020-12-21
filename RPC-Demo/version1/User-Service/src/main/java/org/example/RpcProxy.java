package org.example;

import org.example.rpc.IOrderService;

import java.lang.reflect.Proxy;

public class RpcProxy {
    public <T> T clientProxy(final Class<IOrderService> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                new RemoteInvocationHandler(host, port));
    }
}
