package org.hinoob.tge;

import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {

    private Window window;

    private final Map<String, Clip> sounds = new HashMap<>();

    public SoundPlayer(Window window) {
        this.window = window;
    }

    public void loadSound(String PATH, String location) {
        try {
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));

            BufferedInputStream bufferedInputStream = new BufferedInputStream(audioInputStream);
            audioInputStream = new AudioInputStream(bufferedInputStream, audioInputStream.getFormat(), audioInputStream.getFrameLength());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            sounds.put(PATH, clip);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playSound(String PATH) {
        if(sounds.containsKey(PATH)) {
            Clip clip = sounds.get(PATH);
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stopSounds() {
        for(Clip clip : sounds.values()) {
            clip.stop();
        }
    }

public void stopSound(String PATH) {
        if(sounds.containsKey(PATH)) {
            Clip clip = sounds.get(PATH);
            clip.stop();
        }
    }

    public void loopSound(String PATH) {
        if(sounds.containsKey(PATH)) {
            Clip clip = sounds.get(PATH);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void loopSound(String PATH, int times) {
        if(sounds.containsKey(PATH)) {
            Clip clip = sounds.get(PATH);
            clip.loop(times);
        }
    }
}
