package plugins.attacks;

import identity.IRobot;
import plugins.IAttack;
import plugins.attacks.exceptions.NotEnoughEnergyException;
import plugins.attacks.exceptions.NotInRangeException;

public class Attack implements IAttack
{
    private static int ENERGY_REQUIRED = 20;

    private IRobot me;
    private int range;
    private int power;

    /**
     * Constructeur. LOL.
     * @param me robot à l’origine de l’attauque
     * @param range portée de l’attaque
     * @param power puissance de l’attaque
     */
    public Attack(IRobot me, int range, int power)
    {
        this.range = range;
        this.me = me;
        this.power = power;
    }

    /**
     * Implémentation de attack
     * @param target cible à attaquer
     * @throws NotEnoughEnergyException lorsqu’il n’y a pas assez d’énergie pour attaquer.
     * @throws NotInRangeException lorsque le robot ennemi n’est pas à portée.
     */
    @Override
    public void attack(IRobot target) throws NotEnoughEnergyException, NotInRangeException
    {
        if(me.getEnergy() > ENERGY_REQUIRED)
        {
            if(me.checkRange(target))
            {
                target.decreaseLife(power);
                me.decreaseEnergy(power);
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
        return this.power;
    }

    @Override
    public int getRange()
    {
        return this.range;
    }
}
