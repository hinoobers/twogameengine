package org.hinoob.tge.ui;

import org.hinoob.tge.Renderer;
import org.hinoob.tge.ui.impl.UIInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIScreen implements Renderer {

    private int x, y, width, height, color;
    private final List<UIElement> elements = new ArrayList<>();
    private boolean destroyed = false, hidden = false;

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

    public void destroy(boolean negative) {
        destroyed = !negative;
    }

    public void hide(boolean negative) {
        hidden = !negative;
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

    @Override
    public void keyPressed(int keyCode) {
        for(UIElement element : elements) {
            if(element instanceof UIInput input) {
                input.keyPressed(keyCode);
            }
        }
    }

    @Override
    public boolean shouldRender() {
        return !hidden;
    }

    @Override
    public boolean shouldDestroy() {
        return destroyed;
    }
}
