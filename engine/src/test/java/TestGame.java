import org.hinoob.tge.*;
import org.hinoob.tge.Window;
import org.hinoob.tge.event.KeyListener;
import org.hinoob.tge.event.MouseListener;
import org.hinoob.tge.event.PreRenderListener;
import org.hinoob.tge.ui.UIScreen;
import org.hinoob.tge.ui.impl.UIButton;
import org.hinoob.tge.ui.impl.UIInput;
import org.hinoob.tge.ui.impl.UILabel;
import org.hinoob.tge.util.CollisionUtils;
import org.hinoob.tge.util.DimensionBox;
import org.hinoob.tge.util.RandomUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestGame {

    public static Player player = new Player(50, 50);
    public static int environment = 0;
    public static List<Environment> environments = new ArrayList<>();
    private static GameState gameState = GameState.MENU;

    public static void main(String[] args) throws Exception{
        TGEServer server = new TGEServer(8080);
        server.start(new TGEServer.ServerListener() {
            @Override
            public void onClientConnected(TGEClient client) {
                System.out.println("Client connected!");
                client.sendBytes(new ByteWriter(0).writeString("Hello from server!").getBytes());
            }

            @Override
            public void onClientDisconnected(TGEClient client) {
                System.out.println("Client disconnected!");
            }

            @Override
            public void onMessage(TGEClient client, ByteReader reader) {
                System.out.println("Received message from client: " + reader.readString());
            }
        });

        TGEClient client = new TGEClient("localhost", 8080, new TGEClient.ClientListener() {
            @Override
            public void onMessage(ByteReader reader) {
                System.out.println("Received message from server: " + reader.readString());
            }
        });
        client.sendBytes(new ByteWriter(0).writeString("HEllo!").getBytes());

        Environment map1 = new Environment();
        map1.addRenderer(new GoalObject(500, 500, 32, 32));
        map1.addRenderer(new ObstacleRow(500, 400, 800, 15, map1));
        map1.addRenderer(new ObstacleRow(500, 300, 800, 15, map1));
        map1.addRenderer(new ObstacleRow(500, 200, 800, 15, map1));

        environments.add(map1);

        Environment map2 = new Environment();
        map2.addRenderer(new GoalObject(500, 500, 32, 32));
        map2.addRenderer(new ObstacleRow(500, 400, 800, 15, map2));
        map2.addRenderer(new ObstacleRow(500, 300, 800, 15, map2));
        map2.addRenderer(new ObstacleRow(500, 200, 800, 15, map2));

        environments.add(map2);

        environment = 0;

        Window window = new Window("Dropper Test Game", 800, 600);
        window.attachRenderer(graphics -> {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 800, 600);
            player.render(graphics);

            for (Renderer renderer : environments.get(environment).getRenderers()) {
                renderer.render(graphics);
            }
        });
        UIScreen screen = new UIScreen(0, 0, 800, 600, Color.PINK.getRGB());

        UIButton startButton = new UIButton("Click me to start!", 300, 300, 100, 50, Color.GREEN.getRGB());
        startButton.setClickListener(() -> {
            System.out.println("A");
            window.getSoundPlayer().stopSound("TEST"); // Stop it
            screen.hide(false);
            player.move(RandomUtils.randomInt(0, 800-player.getWidth()), 0, false);
            gameState = GameState.PLAYING;
        });

        UILabel label = new UILabel("Enter your speed:", 300, 200, 200, 50, Color.BLACK.getRGB());

        UIInput playerSpeed = new UIInput(300, 200, 200, 50, Color.WHITE.getRGB());
        playerSpeed.setValue("5");
        playerSpeed.setDigitsOnly(true);

        screen.addElement(playerSpeed);
        screen.addElement(label);
        screen.addElement(startButton);
        window.attachRenderer(screen);
        window.attachListener((PreRenderListener) () -> {
            // Called before the rendering is done
            if(gameState == GameState.PLAYING) {
                player.move(0, 3, true); // Gravity
                DimensionBox playerDimension = player.getDimensionBox();

                for (ObstacleRow row : environments.get(environment).getRenderers(ObstacleRow.class).stream().map(ObstacleRow.class::cast).toList()) {
                    DimensionBox rowDimension = DimensionBox.of(0, row.getY(), 800, row.getHeight());


                    boolean inSpace = false;
                    for (int i = 0; i < row.spaces.size(); i++) {
                        DimensionBox DIme = DimensionBox.of(row.spaces.get(i) * 15, row.getY(), 15, row.getHeight());
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

                GoalObject goalObject = environments.get(environment).getRenderers(GoalObject.class).stream().map(GoalObject.class::cast).findFirst().orElse(null);
                DimensionBox goalDimension = DimensionBox.of(goalObject.getX(), goalObject.getY(), 32, 32);
                if(CollisionUtils.isColliding(playerDimension, goalDimension)) {
                    if(environment == environments.size() - 1) {
                        System.out.println("You won!");
                        gameState = GameState.GAME_WON;
                        window.getSoundPlayer().stopSound("TEST"); // Stop it
                    } else {
                        environment++;
                        player.move(RandomUtils.randomInt(0, 800-player.getWidth()), 0, false);
                    }
                }

                if(player.getY() > 600) {
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
                    player.move(-Integer.parseInt(playerSpeed.getValue()), 0, true);
                } else if(key == KeyCode.KEY_D) {
                    player.move(Integer.parseInt(playerSpeed.getValue()), 0, true);
                } else if(key == KeyCode.KEY_J) {
                    if(environment == environments.size() - 1)
                        environment = 0;
                    screen.hide(true);
                    window.getSoundPlayer().stopSound("TEST"); // Stop it
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
                    graphics.setFont(new Font("Arial", Font.BOLD, 50));
                    graphics.drawString("Game Over!", 400, 300);
                } else if(gameState == GameState.GAME_WON) {
                    graphics.setColor(Color.GREEN);
                    graphics.setFont(new Font("Arial", Font.BOLD, 50));
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
