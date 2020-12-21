package org.example;

import com.example.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() throws IOException {
        return new Socket(host, port);
    }


    public Object sent(RpcRequest rpcRequest) {

        try {
            Socket socket = newSocket();
            try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                outputStream.writeObject(rpcRequest);
                outputStream.flush();

                return inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
