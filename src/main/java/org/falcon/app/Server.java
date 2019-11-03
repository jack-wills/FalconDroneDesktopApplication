package org.falcon.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Server extends Thread {
    private static BlockingQueue networkQueue;
    ServerSocket serverSocket = null;
    Socket socket = null;

    Server(BlockingQueue networkQueue) {
        this.networkQueue = networkQueue;
    }

    @Override
    public void run() {
        try {

            serverSocket = new ServerSocket(4444);

            while (true) {
                socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equals("keepalive")) {
                        out.println("keepalive");
                        continue;
                    }
                    networkQueue.put(inputLine);
                }
            }
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}
