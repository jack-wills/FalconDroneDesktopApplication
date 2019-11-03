package org.falcon.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class SerialPassThroughNetworkController extends Thread {
    Socket socket = null;
    BlockingQueue queue;

    SerialPassThroughNetworkController (BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while(true) {
            try {
                socket = new Socket("localhost", 4445);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                int keepAliveTimer = 0;

                while (true) {
                    out.println("TEST 123");
                    keepAliveTimer++;
                    if (keepAliveTimer == 1) {
                        keepAliveTimer = 0;
                        out.println("keepalive");
                        if (in.readLine() == null) {
                            in.close();
                            socket.close();
                            throw new ConnectException();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ConnectException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
