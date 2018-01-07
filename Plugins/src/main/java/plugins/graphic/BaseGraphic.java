package plugins.graphic;

import identity.IRobot;

import java.awt.*;

public class BaseGraphic implements IGraphic{
    private Color c;
    private static final int BASE_WIDTH = 10;

    @Override
    public void draw(IRobot subject, Graphics g) {
        g.setColor(c);
        g.fillRect(subject.getX() - BASE_WIDTH / 2, subject.getY() - BASE_WIDTH / 2, BASE_WIDTH, BASE_WIDTH);
    }
}
