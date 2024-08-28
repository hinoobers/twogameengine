package org.hinoob.tge;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ByteReader {

    private byte[] data;
    private int index = 0;

    public ByteReader(byte[] data) {
        this.data = data;
    }

    public ByteReader(InputStream inputStream) {
        try {
            data = inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            data = new byte[0];
        }
    }

    private void ensureCapacity(int length) throws IndexOutOfBoundsException {
        if (index + length > data.length) {
            throw new IndexOutOfBoundsException("Attempted to read beyond the available data.");
        }
    }

    public int readInt() {
        ensureCapacity(4);
        int value = (data[index] & 0xFF) << 24 |
                (data[index + 1] & 0xFF) << 16 |
                (data[index + 2] & 0xFF) << 8 |
                (data[index + 3] & 0xFF);
        index += 4;
        return value;
    }

    public byte readByte() {
        ensureCapacity(1);
        return data[index++];
    }

    public byte[] readBytes(int length) {
        ensureCapacity(length);
        byte[] bytes = new byte[length];
        System.arraycopy(data, index, bytes, 0, length);
        index += length;
        return bytes;
    }

    public boolean readBoolean() {
        return readByte() != 0;
    }

    public String readString() {
        int length = readInt(); // The length of the string is encoded as an integer
        byte[] bytes = readBytes(length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public int available() {
        return data.length - index;
    }
}
