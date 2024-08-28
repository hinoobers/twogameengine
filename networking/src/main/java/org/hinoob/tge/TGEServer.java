package org.hinoob.tge;

import org.hinoob.tge.exception.PortAlreadyBoundException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TGEServer {

    private final int port;
    private ServerListener listener;
    private ServerSocket serverSocket;
    private volatile boolean running = false;

    public TGEServer(int port) {
        this.port = port;
    }

    public void start(ServerListener listener) throws PortAlreadyBoundException {
        this.listener = listener;
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            acceptClients();
        } catch (IOException e) {
            throw new PortAlreadyBoundException();
        }
    }

    private void acceptClients() {
        new Thread(() -> {
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    TGEClient client = new TGEClient(clientSocket);
                    listener.onClientConnected(client);
                    handleClient(client);
                } catch (IOException e) {
                    if (running) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void handleClient(TGEClient client) {
        try (BufferedInputStream inputStream = new BufferedInputStream(client.getInputStream())) {
            while (running && client.isConnected()) {
                if (inputStream.available() > 0) {
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    ByteReader reader = new ByteReader(buffer);
                    listener.onMessage(client, reader);
                } else {
                    Thread.sleep(10);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ServerListener {
        void onClientConnected(TGEClient client);
        void onClientDisconnected(TGEClient client);
        void onMessage(TGEClient client, ByteReader reader);
    }
}