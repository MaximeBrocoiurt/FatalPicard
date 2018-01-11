package plugins.graphic;

import annotations.Graphic;
import annotations.Plugin;
import identity.IRobot;

import java.awt.*;
import java.util.Random;

@Plugin(type = Plugin.Type.GRAPHIC)
public class BaseGraphic
{
    private Color c = randomColor();
    private static final int BASE_WIDTH = 10;

    private Color randomColor()
    {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    @Graphic(nature = Graphic.Nature.MAIN)
    public void draw(IRobot subject, Graphics g)
    {
        g.setColor(c);
        g.fillRect(subject.getX() - BASE_WIDTH / 2, subject.getY() - BASE_WIDTH / 2, BASE_WIDTH, BASE_WIDTH);
    }
}
