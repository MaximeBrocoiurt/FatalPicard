package plugins.graphic;

import identity.IGraphic;
import identity.IRobot;

import java.awt.*;
import java.util.Random;

public class BaseGraphic implements IGraphic
{
    private Color c;
    private static final int BASE_WIDTH = 10;

    public BaseGraphic()
    {
        c = randomColor();
    }

    private Color randomColor()
    {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    @Override
    public void draw(IRobot subject, Graphics g)
    {
        g.setColor(c);
        g.fillRect(subject.getX() - BASE_WIDTH / 2, subject.getY() - BASE_WIDTH / 2, BASE_WIDTH, BASE_WIDTH);
    }
}
