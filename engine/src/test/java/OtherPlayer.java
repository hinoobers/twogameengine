import java.awt.*;

public class OtherPlayer extends Player{

    public OtherPlayer(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x, y, 32, 32);
    }
}
