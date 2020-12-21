package com.example.rpc;

/**
 * @author Evan
 */
public interface IOrderService {
    String queryOrderList();

    String queryOrderInfo(String orderId);
}
