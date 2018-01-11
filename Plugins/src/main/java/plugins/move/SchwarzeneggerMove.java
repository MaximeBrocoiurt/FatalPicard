package plugins.move;

import annotations.Move;
import annotations.Plugin;
import identity.IRobot;

import java.util.ArrayList;

@Plugin(type = Plugin.Type.MOVE)
public class SchwarzeneggerMove
{
    private static final int DISTANCE = 5;
    private static final int ENERGY_CONSUMED = 10;

    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes)
    {
        IRobot closer = findCloser(subject, foes);
        int distance = closer.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (closer.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (closer.getY() - subject.getY())) / distance + subject.getY());
        }
        try {subject.decreaseEnergy(ENERGY_CONSUMED);}catch (Exception e){}
        try
        {
            while (subject.getEnergy() > ENERGY_CONSUMED)
            {
                subject.attack(closer);
            }
        } catch (Exception e) { }
    }

    /**
     * Permet de trouver le robot le plus proche
     * @param robots liste des robots
     * @return robot le plus proche
     */
    private IRobot findCloser(IRobot subject, ArrayList<IRobot> robots)
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
