package com.example;

import com.example.rpc.IOrderService;

/**
 * @author Evan
 */
@GPRemoteService
public class OrderServiceImpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "QUERY ORDER LIST METHOD!";
    }

    @Override
    public String queryOrderInfo(String orderId) {
        return "QUERY ORDER INFO METHOD!";
    }
}
