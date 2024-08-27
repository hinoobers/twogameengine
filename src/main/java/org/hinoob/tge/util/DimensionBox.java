package org.hinoob.tge.util;

// can be used as a bounding box
public class DimensionBox {

    public int x, y, width, height;

    public DimensionBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static DimensionBox of(int x, int y, int width, int height) {
        return new DimensionBox(x, y, width, height);
    }
}
