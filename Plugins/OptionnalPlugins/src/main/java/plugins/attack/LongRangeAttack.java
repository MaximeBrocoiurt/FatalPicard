package plugins.attack;

import annotations.Attack;
import annotations.Plugin;
import engine.NotEnoughEnergyException;
import engine.NotInRangeException;
import identity.IRobot;

@Plugin(type = Plugin.Type.ATTACK)
public class LongRangeAttack
{
    private final int ENERGY_CONSUMPTION = 20;
    private final int RANGE = 50;
    private final int POWER = 5;

    /**
     * Implémentation de attack
     * @param target cible à attaquer
     * @throws NotEnoughEnergyException lorsqu’il n’y a pas assez d’énergie pour attaquer.
     * @throws NotInRangeException lorsque le robot ennemi n’est pas à portée.
     */
    @Attack(nature = Attack.Nature.MAIN)
    public void attack(IRobot subject, IRobot target) throws NotEnoughEnergyException, NotInRangeException
    {
        if(subject.getEnergy() >= ENERGY_CONSUMPTION)
        {
            if(subject.calculateDistance(target) <= RANGE)
            {
                target.decreaseLife(POWER);
                subject.decreaseEnergy(ENERGY_CONSUMPTION);
            } else {
                throw new NotInRangeException();
            }
        } else {
            throw new NotEnoughEnergyException();
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

    @Attack(nature = Attack.Nature.CONSUMPTION)
    public int getConsumption()
    {
        return this.ENERGY_CONSUMPTION;
    }
}
