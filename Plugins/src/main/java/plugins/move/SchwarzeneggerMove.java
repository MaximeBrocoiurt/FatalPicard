package plugins.move;

import annotations.Attack;
import annotations.Move;
import annotations.Plugin;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;
import processor.PluginProcessor;
import processor.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Plugin(type = Plugin.Type.MOVE)
public class SchwarzeneggerMove extends HugMove
{
    private static final int DISTANCE = 5;
    private static final int ENERGY_CONSUMED = 10;
    private IRobot target;
    private PluginProcessor pluginProcessor = new PluginProcessor();

    @Override
    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes) throws NotEnoughEnergyException, InvocationTargetException
    {
        checkTarget(subject, foes);
        subject.decreaseEnergy(ENERGY_CONSUMED);
        int distance = target.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (target.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (target.getY() - subject.getY())) / distance + subject.getY());
        }
        while(true)
        {
            checkTarget(subject, foes);
            subject.attack(target);
        }
    }

    private void checkTarget(IRobot subject, ArrayList<IRobot> foes)
    {
        if(target == null || target.getLife() == 0)
            target = findCloser(subject, foes);
    }
}
