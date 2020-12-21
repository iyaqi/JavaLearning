package org.example;

import com.example.rpc.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Evan
 */
@Component
public class RemoteInvocationHandler implements InvocationHandler {

    @Value("${yq.host}")
    private String host;

    @Value("${yq.port}")
    private int port;

    public RemoteInvocationHandler() {
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
