package plugins.moves;

import identity.IRobot;
import plugins.IMove;

import java.util.ArrayList;

public class HugMove implements IMove
{
    private static final int RANGE = 5;
    private static final int ENERGY_CONSUMED = 5;
    @Override
    public void move(IRobot subject, ArrayList<IRobot> foes)
    {
        IRobot closer = findCloser(subject, foes);
        int distance = closer.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((RANGE * (closer.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((RANGE * (closer.getY() - subject.getY())) / distance + subject.getY());
        }
        subject.decreaseEnergy(ENERGY_CONSUMED);
        try
        {
            subject.attack(closer);
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
