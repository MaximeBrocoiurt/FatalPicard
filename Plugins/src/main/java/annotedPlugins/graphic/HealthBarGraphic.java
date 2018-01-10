package annotedPlugins.graphic;

import identity.IRobot;

import java.awt.*;

public class HealthBarGraphic extends BaseGraphic
{
    private static final int BASE_WIDTH = 10;

    public HealthBarGraphic()
    {
        super();
    }

    @Override
    public void draw(IRobot subject, Graphics g)
    {
        super.draw(subject, g);
        g.setColor(Color.GREEN);
        int displayLife = subject.getLife() / 3;
        g.fillRect(subject.getX() - displayLife /2 , subject.getY() + BASE_WIDTH, displayLife , 5);
    }
}
