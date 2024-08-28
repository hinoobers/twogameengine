package org.hinoob.tge;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TGEClient {

    private int id;
    private Socket socket;
    private ClientListener listener;
    private volatile boolean running = false;

    public TGEClient(Socket socket) {
        this.socket = socket;
    }

    public TGEClient(String ip, int port, ClientListener listener) {
        try {
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            this.listener = listener;
            this.running = true;
            startListening();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public InputStream getInputStream() {
        try {
            return socket.getInputStream();
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void close() {
        try {
            running = false;
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendBytes(byte[] data) {
        System.out.println("Sending bytes: " + new String(data));
        try {
            socket.getOutputStream().write(data);
            socket.getOutputStream().flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void startListening() {
        new Thread(() -> {
            try (BufferedInputStream inputStream = new BufferedInputStream(getInputStream())) {
                while (running && isConnected()) {
                    if (inputStream.available() > 0) {
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);
                        ByteReader reader = new ByteReader(buffer);
                        listener.onMessage(reader);
                    } else {
                        Thread.sleep(10);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    public interface ClientListener {
        void onMessage(ByteReader reader);
    }
}
