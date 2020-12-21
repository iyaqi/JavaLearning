package org.example;

import com.example.rpc.IOrderService;
import com.example.rpc.ISayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @YQReference
    private IOrderService orderService;

    @YQReference
    private ISayService sayService;

    @GetMapping(value = "/test")
    public String test(){
        return orderService.queryOrderInfo("ada");
    }

    @GetMapping(value = "/test1")
    public String test1(){
        return orderService.queryOrderList();
    }

    @GetMapping(value = "/say")
    public String say(){
        return sayService.sayHello();
    }
}
