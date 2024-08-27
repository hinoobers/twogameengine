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
    public static GoalObject goalObject = new GoalObject(500, 500);
    private static GameState gameState = GameState.MENU;

    public static void main(String[] args) {
        Window window = new Window("Dropper Test Game", 800, 600);
        obstacleRows.add(new ObstacleRow(800, 400));
        window.attachRenderer(graphics -> {
            player.render(graphics);

            for (ObstacleRow row : obstacleRows) {
                row.render(graphics);
            }

            goalObject.render(graphics);
        });
        UIScreen screen = new UIScreen(0, 0, 800, 600, Color.PINK.getRGB());
        UIButton startButton = new UIButton("Click me to start!", 300, 300, 100, 50, Color.GREEN.getRGB());
        startButton.setClickListener(() -> {
            System.out.println("A");
            window.getSoundPlayer().stopSound("TEST"); // Stop it
            screen.hide(false);
            player.move(0, 0, false);
            gameState = GameState.PLAYING;
        });
        screen.addElement(startButton);
        window.attachRenderer(screen);
        window.attachListener((PreRenderListener) () -> {
            // Called before the rendering is done
            if(gameState == GameState.PLAYING) {
                player.move(0, 3, true);
                DimensionBox playerDimension = DimensionBox.of(player.x, player.y, 32,32);
                for (ObstacleRow row : obstacleRows) {
                    DimensionBox rowDimension = DimensionBox.of(0, row.height, 800, 15);
                    boolean inSpace = false;
                    for (int i = 0; i < row.spaces.size(); i++) {
                        DimensionBox DIme = DimensionBox.of(row.spaces.get(i) * 15, row.height, 15, 15);
                        if(CollisionUtils.isColliding(playerDimension, DIme)) {
                            inSpace = true;
                        }
                    }

                    if(CollisionUtils.isColliding(playerDimension, rowDimension) && !inSpace) {
                        System.out.println("Game Over!");
                        gameState = GameState.GAME_OVER;
                        window.getSoundPlayer().loopSound("TEST"); // Loop it
                    }
                }

                DimensionBox goalDimension = DimensionBox.of(goalObject.x, goalObject.y, 32, 32);
                if(CollisionUtils.isColliding(playerDimension, goalDimension)) {
                    System.out.println("You won!");
                    gameState = GameState.GAME_WON;
                    window.getSoundPlayer().stopSound("TEST"); // Stop it
                }

                if(player.y > 600) {
                    System.out.println("Game Over!");
                    gameState = GameState.GAME_OVER;
                    window.getSoundPlayer().loopSound("TEST"); // Loop it
                }

            }
        });
        window.attachListener(new KeyListener() {
            @Override
            public void onKeyPress(KeyCode key) {
                if(key == KeyCode.KEY_A) {
                    player.move(-6, 0, true);
                } else if(key == KeyCode.KEY_D) {
                    player.move(6, 0, true);
                } else if(key == KeyCode.KEY_J) {
                    screen.hide(true);
                    gameState = GameState.MENU;
                }
            }

            @Override
            public void onKeyPressRaw(int key) {

            }
        });
        window.attachRenderer(new Renderer() {
            @Override
            public void render(Graphics graphics) {
                if(gameState == GameState.GAME_OVER) {
                    graphics.setColor(Color.RED);
                    graphics.drawString("Game Over!", 400, 300);
                } else if(gameState == GameState.GAME_WON) {
                    graphics.setColor(Color.GREEN);
                    graphics.drawString("You won!", 400, 300);
                }
            }

            @Override
            public boolean shouldRender() {
                return gameState == GameState.GAME_OVER || gameState == GameState.GAME_WON;
            }
        });

        window.getSoundPlayer().loadSound("TEST", "test.wav"); // Load to memory
        window.display();
    }
}
