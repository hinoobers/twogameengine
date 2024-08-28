import org.hinoob.tge.GameObject;
import org.hinoob.tge.Renderer;

import java.awt.*;

public class GoalObject extends GameObject {

    public GoalObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x,y, 32, 32);
    }
}
