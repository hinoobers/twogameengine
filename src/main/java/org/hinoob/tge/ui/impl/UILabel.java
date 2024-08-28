package org.hinoob.tge.ui.impl;

import org.hinoob.tge.ui.UIElement;

import java.awt.*;

public class UILabel extends UIElement {

    private String value;

    public UILabel(String value, int x, int y, int width, int height, int color) {
        super(x, y, width, height, color);
        this.value = value;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(color));
        graphics.drawString(value, x + 5, y + 15);
    }
}
