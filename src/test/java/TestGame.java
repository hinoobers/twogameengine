import org.hinoob.tge.KeyCode;
import org.hinoob.tge.Renderer;
import org.hinoob.tge.Window;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.MouseListener;
import org.hinoob.tge.event.PreRenderListener;
import org.hinoob.tge.ui.UIScreen;
import org.hinoob.tge.ui.impl.UIButton;

import java.awt.*;
import java.security.Key;

public class TestGame {

    public static Player player = new Player(50, 50);

    public static void main(String[] args) {
        Window window = new Window("Dropper Test Game", 800, 600);
        window.attachRenderer(graphics -> {
            player.render(graphics);
        });
        UIScreen screen = new UIScreen(0, 0, 800, 600, Color.PINK.getRGB());
        UIButton startButton = new UIButton("Click me to start!", 300, 300, 100, 50, Color.GREEN.getRGB());
        startButton.setClickListener(new UIButton.ClickListener() {
            @Override
            public void onClick() {
                screen.destroy();
            }
        });
        screen.addElement(startButton);
        window.attachRenderer(screen);
        window.attachListener(new PreRenderListener() {
            @Override
            public void onPreRender() {
                // Called before the rendering is done
                player.move(0, 1);
            }
        });
        window.attachListener(new KeyListener() {
            @Override
            public void onKeyPress(KeyCode key) {
                if(key == KeyCode.KEY_A) {
                    player.move(-2, 0);
                } else if(key == KeyCode.KEY_D) {
                    player.move(2, 0);
                }
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
