package org.hinoob.tge;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TGEClient {

    private int id;
    private Socket socket;

    public TGEClient(Socket socket) {
        this.id = id;
        this.socket = socket;
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
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendBytes(byte[] data) {

    }
}
