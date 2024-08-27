import org.hinoob.tge.KeyCode;
import org.hinoob.tge.Renderer;
import org.hinoob.tge.Window;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.MouseListener;
import org.hinoob.tge.event.PreRenderListener;

import java.awt.*;
import java.security.Key;

public class TestGame {

    public static Player player = new Player(50, 50);

    public static void main(String[] args) {
        Window window = new Window("Test Game", 800, 600);
        window.attachRenderer(graphics -> {
            player.render(graphics);
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
                if(key == KeyCode.KEY_A) {
                    player.move(-1, 0);
                } else if(key == KeyCode.KEY_D) {
                    player.move(1, 0);
                }
            }

            @Override
            public void onKeyPressRaw(int key) {

            }
        });
        window.attachListener(new MouseListener() {
            @Override
            public void onPress(int mouseX, int mouseY, int button) {
                System.out.println("Pressed at " + mouseX + ", " + mouseY + " with button " + button);
            }

            @Override
            public void onClick(int mouseX, int mouseY, int button) {
                System.out.println("Clicked at " + mouseX + ", " + mouseY + " with button " + button);
            }

            @Override
            public void onRelease(int mouseX, int mouseY, int button) {
                System.out.println("Released at " + mouseX + ", " + mouseY + " with button " + button);
            }
        });

        window.getSoundPlayer().loadSound("TEST", "test.wav"); // Load to memory
        window.getSoundPlayer().loopSound("TEST"); // Loop it
        window.display();
    }
}
