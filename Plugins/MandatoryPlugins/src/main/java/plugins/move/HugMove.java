package plugins.move;

import annotations.Move;
import annotations.Plugin;
import engine.NotEnoughEnergyException;
import identity.IRobot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Plugin(type = Plugin.Type.MOVE)
public class HugMove
{
    private static final int DISTANCE = 5;
    private static final int ENERGY_CONSUMED = 5;

    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes) throws NotEnoughEnergyException, InvocationTargetException
    {
        subject.decreaseEnergy(ENERGY_CONSUMED);
        IRobot closer = findCloser(subject, foes);
        int distance = closer.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (closer.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (closer.getY() - subject.getY())) / distance + subject.getY());
        }
        subject.attack(closer);
    }

    /**
     * Permet de trouver le robot le plus proche
     * @param robots liste des robots
     * @return robot le plus proche
     */
    protected IRobot findCloser(IRobot subject, ArrayList<IRobot> robots)
    {
        IRobot closer = null;
        int minimalDistance = Integer.MAX_VALUE, tampon;
        for(IRobot r : robots)
        {
            if(!r.equals(this))
            {
                tampon = r.calculateDistance(subject);
                if(tampon < minimalDistance)
                {
                    minimalDistance = tampon;
                    closer = r;
                }
            }
        }
        return closer;
    }
}
