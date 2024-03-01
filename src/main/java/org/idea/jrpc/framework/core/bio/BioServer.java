package org.idea.jrpc.framework.core.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    private static final ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(1009));
        try {
            while (true) {
                // Block point 1
                // The server will wait for the client to connect here
                Socket socket = serverSocket.accept();
                System.out.println("[Server] New connection");
                executors.execute(() -> {
                    while (true) {
                        InputStream inputStream = null;
                        try {
                            // Block point 2
                            // The server will wait for the client to send data here
                            inputStream = socket.getInputStream();
                            byte[] result = new byte[1024];
                            int len = inputStream.read(result);
                            if (len != -1) {
                                System.out.println("[Client -> Server] " + new String(result, 0, len));
                                OutputStream outputStream = socket.getOutputStream();
                                outputStream.write("response data".getBytes());
                                outputStream.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
