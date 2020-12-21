package org.example;

import org.example.rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Evan
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcNetTransport  rpcNetTransport = new RpcNetTransport(host,port);

        RpcRequest request = new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypes(method.getParameterTypes());
        request.setMethodName(method.getName());

        return rpcNetTransport.sent(request);
    }
}
