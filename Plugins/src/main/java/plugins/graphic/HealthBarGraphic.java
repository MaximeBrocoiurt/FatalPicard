package plugins.graphic;

import identity.IRobot;

import java.awt.*;

public class HealthBarGraphic implements IGraphic{
    private Color c;
    private static final int BASE_WIDTH = 10;
    private BaseGraphic rob;

    public HealthBarGraphic() {
        this.rob = new BaseGraphic();
    }

    @Override
    public void draw(IRobot subject, Graphics g) {
        rob.draw(subject, g);
        g.setColor(Color.GREEN);
        int displayLife = subject.getLife() / 3;
        g.fillRect(subject.getX() - displayLife /2 , subject.getY() + BASE_WIDTH, displayLife , 5);
    }
}
