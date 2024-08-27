package org.hinoob.tge.ui.impl;

import org.hinoob.tge.ui.UIElement;
import org.hinoob.tge.util.UIUtils;

import java.awt.*;
import java.util.Random;

public class UIButton extends UIElement {

    private String text;
    private int textColor;
    private ClickListener clickListener;

    public UIButton(String text, int x, int y, int width, int height, int color) {
        super(x, y, width, height, color);

        this.text = text;
        this.textColor = Color.BLACK.getRGB();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(color));
        graphics.fillRect(x, y, width, height);

        graphics.setColor(new Color(textColor));
        graphics.drawString(text, (x + width / 2) - (text.length() * 4), (y + height / 2) + 5);
    }

    @Override
    public void onMouseClick(int x, int y, int button) {
        clickListener.onClick();
    }

    public interface ClickListener {
        void onClick();
    }
}
