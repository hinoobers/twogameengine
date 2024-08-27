package org.hinoob.tge;

import org.hinoob.tge.event.GameClosedListener;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.Listener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Window {

    private String title;
    private int width, height;
    private boolean resizable = false;
    private JFrame frame;

    private final List<Renderer> attachedRenderers = new ArrayList<>();
    private final List<Listener> attachedListeners = new ArrayList<>();
    private final SoundPlayer soundPlayer = new SoundPlayer(this);

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public Window(String title, int width, int height, boolean resizable) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.resizable = resizable;
    }

    public void attachRenderer(Renderer renderer) {
        attachedRenderers.add(renderer);
    }

    public void attachListener(Listener listener) {
        attachedListeners.add(listener);
    }

    public List<Renderer> getRenderers() {
        return attachedRenderers;
    }

    public List<Listener> getListeners(Class<?> type) {
        return attachedListeners.stream().filter(type::isInstance).toList();
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public void display() {
        // use JFrame

        this.frame = new JFrame(title);
        // use JPanel
        frame.add(new GamePanel(this));
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(this.resizable);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                getListeners(GameClosedListener.class).forEach(listener -> ((GameClosedListener) listener).onGameClosed());
            }
        });
        frame.setVisible(true);

    }
}
