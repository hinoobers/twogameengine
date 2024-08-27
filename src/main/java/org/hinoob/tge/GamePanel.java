package org.hinoob.tge;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    private Window window;
    private Timer timer;

    public GamePanel(Window window) {
        this.window = window;
        timer = new Timer(16, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    public void update() {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Renderer renderer : window.getRenderers()) {
            renderer.render(g);
        }
    }
}
