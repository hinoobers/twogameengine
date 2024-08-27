import org.hinoob.tge.KeyCode;
import org.hinoob.tge.Renderer;
import org.hinoob.tge.Window;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.PreRenderListener;

import java.awt.*;
import java.security.Key;

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
        window.attachListener(new PreRenderListener() {
            @Override
            public void onPreRender() {
                // Called before the rendering is done
            }
        });
        window.attachListener(new KeyListener() {
            @Override
            public void onKeyPress(KeyCode key) {
                System.out.println(key);
            }

            @Override
            public void onKeyPressRaw(int key) {

            }
        });

        window.getSoundPlayer().loadSound("TEST", "test.wav"); // Load to memory
        window.getSoundPlayer().loopSound("TEST"); // Loop it
        window.display();
    }
}
