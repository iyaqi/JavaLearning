package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(int port){

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
