package com.example;

import com.example.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProcessorHandler implements Runnable {
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                try {
                    RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
                    Mediator instance = Mediator.getInstance();
                    Object rs = instance.process(rpcRequest);
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


}
