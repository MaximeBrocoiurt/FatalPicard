package plugins.attack;

import annotations.Attack;
import annotations.Plugin;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;

@Plugin(type = Plugin.Type.ATTACK)
public class LongRangeAtttack
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
    @Attack(nature = Attack.Nature.MAIN)
    public void attack(IRobot subject, IRobot target)
    {
        if(subject.getEnergy() > ENERGY_REQUIRED)
        {
            if(subject.calculateDistance(target) <= RANGE)
            {
                target.decreaseLife(POWER);
                try {subject.decreaseEnergy(ENERGY_REQUIRED);}catch (Exception e){}
            }
        }
    }

    @Attack(nature = Attack.Nature.POWER)
    public int getPower()
    {
        return this.POWER;
    }

    @Attack(nature = Attack.Nature.RANGE)
    public int getRange()
    {
        return this.RANGE;
    }
}
