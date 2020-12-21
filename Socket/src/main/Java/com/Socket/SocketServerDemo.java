package com.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = reader.readLine(); //被阻塞了
            String clientStr = s; //读取客户端的一行数据
            System.out.println("接收到客户端的信息：" + clientStr);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("Hello, Client!\n");
            writer.flush();

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
