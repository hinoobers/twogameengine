import org.hinoob.tge.KeyCode;
import org.hinoob.tge.Renderer;
import org.hinoob.tge.Window;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.MouseListener;
import org.hinoob.tge.event.PreRenderListener;
import org.hinoob.tge.ui.UIScreen;
import org.hinoob.tge.ui.impl.UIButton;
import org.hinoob.tge.util.CollisionUtils;
import org.hinoob.tge.util.DimensionBox;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class TestGame {

    public static Player player = new Player(50, 50);
    public static List<ObstacleRow> obstacleRows = new ArrayList<>();
    private static boolean isGameActive = false;

    public static void main(String[] args) {
        Window window = new Window("Dropper Test Game", 800, 600);
        obstacleRows.add(new ObstacleRow(800, 400));
        window.attachRenderer(graphics -> {
            player.render(graphics);

            for (ObstacleRow row : obstacleRows) {
                row.render(graphics);
            }
        });
        UIScreen screen = new UIScreen(0, 0, 800, 600, Color.PINK.getRGB());
        UIButton startButton = new UIButton("Click me to start!", 300, 300, 100, 50, Color.GREEN.getRGB());
        startButton.setClickListener(new UIButton.ClickListener() {
            @Override
            public void onClick() {
                screen.destroy();
                isGameActive = true;
            }
        });
        screen.addElement(startButton);
        window.attachRenderer(screen);
        window.attachListener(new PreRenderListener() {
            @Override
            public void onPreRender() {
                // Called before the rendering is done
                if(isGameActive) {
                    player.move(0, 1);
                    DimensionBox playerDimension = DimensionBox.of(player.x, player.y, 32,32);
                    for (ObstacleRow row : obstacleRows) {
                        for (int i = 0; i < row.spaces.size(); i++) {
                            DimensionBox DIme = DimensionBox.of(row.spaces.get(i) * 15, row.height, 15, 15);
                            if(CollisionUtils.isColliding(playerDimension, DIme)) {
                                System.out.println("Collided!");
                            }
                        }
                    }

                }
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
