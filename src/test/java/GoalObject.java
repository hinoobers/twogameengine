import org.hinoob.tge.Renderer;

import java.awt.*;

public class GoalObject implements Renderer {

    public int x, y;

    public GoalObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x,y, 32, 32);
    }
}
