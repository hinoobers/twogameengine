package org.hinoob.tge;

import org.hinoob.tge.exception.PortAlreadyBoundException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TGEServer {

    private int port;
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
        new Thread(() -> {
            try {
                // Example code for client handling
                ByteReader reader = new ByteReader(client.getInputStream());
                while (client.isConnected()) {
                    // Read messages from client
                    listener.onMessage(client, reader);
                }
            } finally {
                listener.onClientDisconnected(client);
                client.close();
            }
        }).start();
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