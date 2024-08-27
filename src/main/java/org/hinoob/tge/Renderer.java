package org.hinoob.tge;

import java.awt.*;

public interface Renderer {

    void render(Graphics graphics);
    default boolean shouldRender() {
        return true;
    }
    default boolean shouldDestroy() {
        return false;
    }
}
