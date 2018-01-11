package plugins.move;

import annotations.Move;
import annotations.Plugin;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

@Plugin(type = Plugin.Type.MOVE)
public class RandomMove
{
    private static final int DISTANCE = 5;
    private static final int ENERGY_CONSUMED = 5;
    private IRobot target;

    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes) throws NotEnoughEnergyException, InvocationTargetException
    {
        if(target == null || target.getLife() <= 0)
        {
            target = foes.get(new Random().nextInt(foes.size()));
        }
        subject.decreaseEnergy(ENERGY_CONSUMED);
        int distance = target.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (target.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (target.getY() - subject.getY())) / distance + subject.getY());
        }
        subject.attack(target);
    }
}
