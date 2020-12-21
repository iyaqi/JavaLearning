package org.example;

/**
 * Hello world!
 * @author Evan
 */
public class Bootstrap {
    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(orderService,8888);
    }
}
