package org.hinoob.tge;

import org.hinoob.tge.util.DimensionBox;

import java.awt.*;

public class GameObject implements Renderer{

    protected int x, y, width, height;
    public Environment environment;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public DimensionBox getDimensionBox() {
        return new DimensionBox(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, width, height);
    }
}
