package org.hinoob.tge.ui.impl;

import org.hinoob.tge.ui.UIElement;
import org.hinoob.tge.util.UIUtils;

import java.awt.*;
import java.util.Random;

public class UIInput extends UIElement {

    private String value = "";
    private boolean digitsOnly = false;
    private boolean focused = false;

    public UIInput(int x, int y, int width, int height, int color) {
        super(x, y, width, height, color);
    }

    public void setDigitsOnly(boolean digitsOnly) {
        this.digitsOnly = digitsOnly;
    }


    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(color));
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.BLACK);
        graphics.drawString(value != null ? value : "", x + 5, y + height - 5);
    }

    @Override
    public void onMouseClick(int x, int y, int button) {
        if(UIUtils.isOver(this.x, this.y, width, height, x, y)) {

            focused = true;
        } else {
            focused = false;
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        if(focused) {
            if(keyCode == 8 && !value.isEmpty()) {
                value = value.substring(0, value.length() - 1);
            } else if(keyCode != 8) {
                if(digitsOnly && !Character.isDigit(keyCode)) return;
                value += (char) keyCode;
            }
        }
    }

    public String getValue() {
        return value;
    }
}
