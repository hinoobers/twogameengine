package org.hinoob.tge;

import org.hinoob.tge.event.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class GamePanel extends JPanel{

    private final Window window;
    private final Timer timer;

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
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(Listener listener : window.getListeners(org.hinoob.tge.event.MouseListener.class)) {
                    org.hinoob.tge.event.MouseListener mouseListener = (org.hinoob.tge.event.MouseListener) listener;

                    mouseListener.onClick(e.getX(), e.getY(), e.getButton());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                for(Listener listener : window.getListeners(org.hinoob.tge.event.MouseListener.class)) {
                    org.hinoob.tge.event.MouseListener mouseListener = (org.hinoob.tge.event.MouseListener) listener;

                    mouseListener.onPress(e.getX(), e.getY(), e.getButton());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for(Listener listener : window.getListeners(org.hinoob.tge.event.MouseListener.class)) {
                    org.hinoob.tge.event.MouseListener mouseListener = (org.hinoob.tge.event.MouseListener) listener;

                    mouseListener.onRelease(e.getX(), e.getY(), e.getButton());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
        java.util.List<Renderer> toRemove = new java.util.ArrayList<>();
        for(Renderer renderer : window.getRenderers()) {
            if(renderer.shouldDestroy()) {
                toRemove.add(renderer);
                continue;
            }

            if(renderer.shouldRender())
                renderer.render(g);
        }

        for (Renderer renderer : toRemove) {
            window.getRenderers().remove(renderer);
        }
    }
}
