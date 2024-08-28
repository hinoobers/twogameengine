package org.hinoob.testgame;

import org.hinoob.tge.Environment;
import org.hinoob.tge.GameObject;
import org.hinoob.tge.TGEClient;

import java.awt.*;

public class Player extends GameObject {


    public Player(int x, int y) {
        super(x, y, 32, 32);
    }


    public void move(int x, int y, boolean relative){
        if(relative){
            this.x += x;
            this.y += y;
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public void setColor(int red, int green, int blue){
        this.color = new Color(red, green, blue).getRGB();
    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color(color));
        graphics.fillRect(x, y, 32, 32);
    }
}
