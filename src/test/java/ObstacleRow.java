import org.hinoob.tge.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleRow implements Renderer {

    private int width;
    public int height = 5;
    public List<Integer> spaces = new ArrayList<>();

    public ObstacleRow(int width, int height) {
        this.width = width;
        this.height = height;


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
                graphics.fillRect(i * 15, height, 15, 15);
            }
        }

        graphics.setColor(Color.WHITE);

        for (int space : spaces) {
            graphics.clearRect(space * 15, height, 15, 15);
        }
    }
}
