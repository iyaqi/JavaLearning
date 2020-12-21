package org.example;

import org.example.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {

        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                try {
                    RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
                    Object rs = invoke(rpcRequest);
                    System.out.println("服务端执行结果:" + rs);
                    outputStream.writeObject(rs);
                    outputStream.flush();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getTypes());
        return method.invoke(service,rpcRequest.getArgs());
    }
}
