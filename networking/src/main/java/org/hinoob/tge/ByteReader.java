package org.hinoob.tge;

import java.io.InputStream;

public class ByteReader {

    private byte[] data;

    private int index;

    public ByteReader(byte[] data) {
        this.data = data;
    }

    public ByteReader(InputStream inputStream) {
        try {
            data = inputStream.readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int readInt() {
        int value = data[index] << 24 | (data[index + 1] & 0xFF) << 16 | (data[index + 2] & 0xFF) << 8 | (data[index + 3] & 0xFF);
        index += 4;
        return value;
    }

    public byte readByte() {
        return data[index++];
    }

    public byte[] readBytes(int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = data[index++];
        }
        return bytes;
    }

    public boolean readBoolean() {
        return data[index++] == 1;
    }

    public String readString() {
        int length = readInt();
        byte[] bytes = readBytes(length);
        return new String(bytes);
    }
}
