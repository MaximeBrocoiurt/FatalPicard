package plugins.attack;

import identity.IRobot;
import plugins.attack.exceptions.NotEnoughEnergyException;
import plugins.attack.exceptions.NotInRangeException;

public class LongRangeAtttack implements IAttack
{
    private final int ENERGY_REQUIRED = 20;
    private final int RANGE = 100;
    private final int POWER = 5;

    /**
     * Implémentation de attack
     * @param target cible à attaquer
     * @throws NotEnoughEnergyException lorsqu’il n’y a pas assez d’énergie pour attaquer.
     * @throws NotInRangeException lorsque le robot ennemi n’est pas à portée.
     */
    @Override
    public void attack(IRobot me, IRobot target) throws NotEnoughEnergyException, NotInRangeException
    {
        if(me.getEnergy() > ENERGY_REQUIRED)
        {
            if(me.calculateDistance(target) <= RANGE)
            {
                target.decreaseLife(POWER);
                me.decreaseEnergy(POWER);
            } else {
                throw new NotInRangeException();
            }
        } else {
            throw new NotEnoughEnergyException();
        }
    }

    @Override
    public int getPower()
    {
        return this.POWER;
    }

    @Override
    public int getRange()
    {
        return this.RANGE;
    }
}
