import java.awt.*;

public class Player {

    public int x, y;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
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

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 32, 32);
    }
}
