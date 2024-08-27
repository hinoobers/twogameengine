import org.hinoob.tge.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleRow implements Renderer {

    private int width;
    private int height = 5;
    private List<Integer> spaces = new ArrayList<>();

    public ObstacleRow(int width, int height) {
        this.width = width;
        this.height = height;
        int spaceCount = (int) (Math.random() * 3) + 1;
        for (int i = 0; i < spaceCount; i++) {
            spaces.add((int) (Math.random() * width));
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < width; i++) {
            if (!spaces.contains(i)) {
                graphics.fillRect(i, height, width, 15);
            }
        }
    }
}
