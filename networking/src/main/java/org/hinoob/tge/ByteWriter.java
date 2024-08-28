package org.hinoob.tge;

import java.nio.charset.StandardCharsets;

public class ByteWriter {

    private byte[] bytes;
    private int currentPosition;

    public ByteWriter(int size) {
        bytes = new byte[size];
        currentPosition = 0;
    }

    public void ensureCapacity(int additionalSize) {
        int requiredCapacity = currentPosition + additionalSize;
        if (bytes.length < requiredCapacity) {
            byte[] newBytes = new byte[requiredCapacity];
            System.arraycopy(bytes, 0, newBytes, 0, currentPosition);
            bytes = newBytes;
        }
    }

    public ByteWriter writeInt(int value) {
        ensureCapacity(4);
        bytes[currentPosition] = (byte) (value >> 24);
        bytes[currentPosition + 1] = (byte) (value >> 16);
        bytes[currentPosition + 2] = (byte) (value >> 8);
        bytes[currentPosition + 3] = (byte) value;
        currentPosition += 4;
        return this;
    }

    public ByteWriter writeByte(byte value) {
        ensureCapacity(1);
        bytes[currentPosition] = value;
        currentPosition++;
        return this;
    }

    public ByteWriter writeBytes(byte[] value) {
        ensureCapacity(value.length);
        System.arraycopy(value, 0, bytes, currentPosition, value.length);
        currentPosition += value.length;
        return this;
    }

    public ByteWriter writeBoolean(boolean value) {
        return writeByte((byte) (value ? 1 : 0));
    }

    public ByteWriter writeString(String value) {
        byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
        writeInt(stringBytes.length);
        writeBytes(stringBytes);
        return this;
    }

    public byte[] getBytes() {
        byte[] result = new byte[currentPosition];
        System.arraycopy(bytes, 0, result, 0, currentPosition);
        return result;
    }
}