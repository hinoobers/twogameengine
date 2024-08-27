import org.hinoob.tge.Renderer;
import org.hinoob.tge.Window;

import java.awt.*;

public class TestGame {

    public static void main(String[] args) {
        Window window = new Window("Test Game", 800, 600);
        window.attachRenderer(graphics -> {
            graphics.setColor(Color.RED);
            graphics.fillRect(0, 0, 800, 600);
            graphics.setColor(Color.BLACK);
            graphics.drawString("Hello, World!", 400, 300);
        });
        window.attachRenderer(graphics -> {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(100, 100, 100, 100);
        });
        window.display();
    }
}
