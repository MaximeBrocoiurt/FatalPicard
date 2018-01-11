package plugins.move;

import annotations.Move;
import annotations.Plugin;
import engine.*;
import identity.IRobot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

@Plugin(type = Plugin.Type.MOVE)
public class ChaoticMove
{
    private static final int DISTANCE = 15;
    private static final int ENERGY_CONSUMED = 5;

    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes) throws InvocationTargetException, NotEnoughEnergyException
    {
        IRobot closer = foes.get(new Random().nextInt(foes.size()));
        int distance = closer.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (closer.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (closer.getY() - subject.getY())) / distance + subject.getY());
        }
        subject.decreaseEnergy(ENERGY_CONSUMED);
        subject.attack(closer);
    }
}
