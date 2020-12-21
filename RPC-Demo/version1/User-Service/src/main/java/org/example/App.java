package org.example;

import org.example.rpc.IOrderService;

/**
 *
 * @author Evan
 */
public class App {
    public static void main(String[] args) {
        RpcProxy rpcProxy = new RpcProxy();
        IOrderService orderService = rpcProxy.clientProxy(IOrderService.class,"localhost",8888);

        System.out.println(orderService.queryOrderInfo("ada"));
        System.out.println(orderService.queryOrderList());
    }
}
