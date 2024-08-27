package org.hinoob.tge;

import org.hinoob.tge.event.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel{

    private Window window;
    private Timer timer;

    public GamePanel(Window window) {
        this.window = window;
        setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                for(Listener listener : window.getListeners(org.hinoob.tge.event.KeyListener.class)) {
                    org.hinoob.tge.event.KeyListener keyListener = (org.hinoob.tge.event.KeyListener) listener;
                    keyListener.onKeyPressRaw(e.getKeyCode());

                    if(KeyCode.fromKeycode(e.getKeyCode()) != null)
                        keyListener.onKeyPress(KeyCode.fromKeycode(e.getKeyCode()));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        timer = new Timer(16, e -> {
            for(Listener listener : window.getListeners(org.hinoob.tge.event.PreRenderListener.class)) {
                ((org.hinoob.tge.event.PreRenderListener) listener).onPreRender();
            }
            repaint();
            for(Listener listener : window.getListeners(org.hinoob.tge.event.PostRenderListener.class)) {
                ((org.hinoob.tge.event.PostRenderListener) listener).onPostRender();
            }
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Renderer renderer : window.getRenderers()) {
            renderer.render(g);
        }
    }
}
