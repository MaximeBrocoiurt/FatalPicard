package annotedPlugins.graphic;

import annotations.Graphic;
import annotations.Plugin;
import identity.IRobot;

import java.awt.*;

@Plugin(type = Plugin.Type.GRAPHIC)
public class SpecialGraphic extends BaseGraphic
{
    private static final int BASE_WIDTH = 10;

    public SpecialGraphic()
    {
        super();
    }

    @Graphic(nature = Graphic.Nature.MAIN)
    public void draw(IRobot subject, Graphics g)
    {
        super.draw(subject, g);
        g.setColor(Color.GREEN);
        int displayLife = subject.getLife() / 3;
        g.fillRect(subject.getX() - displayLife /2 , subject.getY() + BASE_WIDTH, displayLife , 5);
    }
}
