package com.example;

import com.example.rpc.ISayService;


/**
 * @author Evan
 */
@GPRemoteService
public class SayServiceImpl implements ISayService {
    @Override
    public String sayHello() {
        return "Hello,World";
    }
}
