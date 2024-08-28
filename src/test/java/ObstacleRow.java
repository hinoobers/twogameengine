import org.hinoob.tge.GameObject;
import org.hinoob.tge.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleRow extends GameObject {

    public int height = 5;
    public List<Integer> spaces = new ArrayList<>();

    public ObstacleRow(int x, int y, int width, int height) {
        super(x, y, width, height);

        for(int i = 1; i < 5; i++) {
            int pixel = (int) ((Math.random() * width)/15);
            spaces.add(pixel);
            spaces.add(pixel + 1);
        }
    }


    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < width; i++) {
            if (!spaces.contains(i)) {
                graphics.fillRect(i * 15, y, 15, height);
            }
        }
    }
}
