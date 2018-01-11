package plugins.graphic;

import annotations.Attack;
import annotations.Graphic;
import annotations.Plugin;
import identity.IRobot;
import processor.PluginProcessor;
import processor.Tuple;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

@Plugin(type = Plugin.Type.GRAPHIC)
public class RangeGraphic extends BaseGraphic
{
    private PluginProcessor pluginProcessor = new PluginProcessor();

    @Graphic(nature = Graphic.Nature.MAIN)
    public void draw(IRobot subject, Graphics g)
    {
        int range = Integer.MIN_VALUE;
        try
        {
            range = (int)pluginProcessor.executeMethod(new Tuple<>(subject.getAttack().getClass()), Attack.Nature.RANGE);
        }
        catch(IllegalAccessException|InstantiationException e)
        {
            e.printStackTrace();
        }
        catch(InvocationTargetException e)
        {
            e.printStackTrace();
        }
        g.setColor(Color.RED);
        g.fillOval(subject.getX(), subject.getY(), range, range);
        super.draw(subject, g);
    }
}
