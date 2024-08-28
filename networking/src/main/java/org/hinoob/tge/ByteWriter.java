package org.hinoob.tge;

public class ByteWriter {

    private byte[] bytes;

    public ByteWriter(int size) {
        bytes = new byte[size];
    }

    public void ensureCapacity(int size) {
        if (bytes.length < size) {
            byte[] newBytes = new byte[size];
            System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
            bytes = newBytes;
        }
    }

    public void writeInt(int value) {
        ensureCapacity(bytes.length + 4);
        bytes[bytes.length] = (byte) (value >> 24);
        bytes[bytes.length + 1] = (byte) (value >> 16);
        bytes[bytes.length + 2] = (byte) (value >> 8);
        bytes[bytes.length + 3] = (byte) value;
    }

    public void writeByte(byte value) {
        ensureCapacity(bytes.length + 1);
        bytes[bytes.length] = value;
    }

    public void writeBytes(byte[] value) {
        ensureCapacity(bytes.length + value.length);
        System.arraycopy(value, 0, bytes, bytes.length, value.length);
    }

    public void writeBoolean(boolean value) {
        ensureCapacity(bytes.length + 1);
        bytes[bytes.length] = (byte) (value ? 1 : 0);
    }

    public void writeString(String value) {
        byte[] bytes = value.getBytes();
        writeInt(bytes.length);
        writeBytes(bytes);
    }
}
