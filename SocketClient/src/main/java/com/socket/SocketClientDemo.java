package com.socket;


import java.io.*;
import java.net.Socket;

public class SocketClientDemo {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("Hello, Server!\n");
            writer.flush();



            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverLine=bufferedReader.readLine(); //读取服务端返回的数据
            System.out.println("服务端返回的数据:"+serverLine);;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
