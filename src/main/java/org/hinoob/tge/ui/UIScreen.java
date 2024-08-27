package org.hinoob.tge.ui;

import org.hinoob.tge.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIScreen implements Renderer {

    private int x, y, width, height, color;
    private final List<UIElement> elements = new ArrayList<>();

    public UIScreen(int x, int y, int width, int height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void addElement(UIElement element) {
        elements.add(element);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(color));
        graphics.fillRect(x, y, width, height);

        for(UIElement element : elements) {
            element.render(graphics);
        }
    }

    @Override
    public void onMouseClick(int x, int y, int button) {
        for(UIElement element : elements) {
            element.onMouseClick(x, y, button);
        }
    }
}
