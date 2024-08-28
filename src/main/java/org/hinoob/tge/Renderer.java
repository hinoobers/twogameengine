package org.hinoob.tge;

import java.awt.*;

public interface Renderer {

    void render(Graphics graphics);

    default void onMouseClick(int x, int y, int button) {

    }

    default void keyPressed(int keyCode) {

    }

    default boolean shouldRender() {
        return true;
    }
    default boolean shouldDestroy() {
        return false;
    }
}
