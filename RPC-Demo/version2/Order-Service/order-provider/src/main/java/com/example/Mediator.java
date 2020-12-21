package com.example;

import com.example.rpc.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Mediator {

    public static Map<String, BeanMethod> map = new HashMap<>();

    private volatile static Mediator instance;

    // double check to generate Singleton instance
    public static Mediator getInstance() {
        if (instance == null){
            synchronized (Mediator.class){
                if (instance == null){
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object process(RpcRequest request){

        String key = request.getClassName() + "." + request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (beanMethod == null) {
            return null;
        }

        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();

        try {
            return method.invoke(bean,request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}
