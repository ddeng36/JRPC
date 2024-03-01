package org.idea.jrpc.framework.core.bio;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioClient {
    private static final ExecutorService executors = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(1009));
        System.out.println("[Client] Connected to server");
        OutputStream outputStream = null;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();
            outputStream = socket.getOutputStream();
            outputStream.write(nextLine.getBytes());
            outputStream.flush();
            System.out.println("[Client -> Server] Send finished");
            executors.execute(() -> {
                while (true) {
                    try {
                        // Block point
                        // The client will wait for the server to send data here
                        byte[] response = new byte[1024];
                        int len = socket.getInputStream().read(response);
                        if (len != -1) {
                            System.out.println("[Server -> Client] " + new String(response, 0, len));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
