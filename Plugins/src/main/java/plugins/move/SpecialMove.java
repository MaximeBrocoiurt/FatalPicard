package plugins.move;

import annotations.Attack;
import annotations.Move;
import annotations.Plugin;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;
import identity.exceptions.NoPluginException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Plugin(type = Plugin.Type.MOVE)
public class SpecialMove
{
    private static final int DISTANCE = 5;
    private static final int ENERGY_CONSUMED = 10;

    @Move(nature = Move.Nature.MAIN)
    public void move(IRobot subject, ArrayList<IRobot> foes) throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        IRobot closer = findCloser(subject, foes);
        int distance = closer.calculateDistance(subject);
        if(distance != 0)
        {
            subject.setX((DISTANCE * (closer.getX() - subject.getX())) / distance + subject.getX());
            subject.setY((DISTANCE * (closer.getY() - subject.getY())) / distance + subject.getY());
        }
        try
        {
            subject.decreaseEnergy(ENERGY_CONSUMED);
        } catch (NotEnoughEnergyException e) {

        }
        try
        {
            int attackConsumption = Integer.MAX_VALUE;
//            Class<?> attack = subject.getAttack();
            Class<?> attack = null;
            for(Method m : attack.getDeclaredMethods())
            {
                if(m.getDeclaredAnnotation(Attack.class).nature() == Attack.Nature.CONSUMPTION)
                {
                    attackConsumption = (int)m.invoke(attack.newInstance());
                }
            }
            while (subject.getEnergy() >= attackConsumption)
            {
                subject.attack(closer);
            }
        } catch (InvocationTargetException e)
        {
            if(e.getCause().getClass() == NotEnoughEnergyException.class)
            {
                System.out.println("Je n’ai pas assez d’énergie pour attaquer.");
            } else if(e.getCause().getClass() == NotInRangeException.class)
            {
                System.out.println("Je suis trop loin pour attaquer.");
            } else {
                throw new InvocationTargetException(e);
            }
        }
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
