package org.hinoob.tge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window {

    private String title;
    private int width, height;
    private boolean resizable = false;
    private JFrame frame;

    private final List<Renderer> attachedRenderers = new ArrayList<>();
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

    public List<Renderer> getRenderers() {
        return attachedRenderers;
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
        frame.setVisible(true);

    }
}
