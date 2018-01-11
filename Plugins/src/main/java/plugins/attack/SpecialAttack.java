package plugins.attack;

import annotations.Attack;
import annotations.Plugin;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;
import identity.exceptions.NoPluginException;

@Plugin(type = Plugin.Type.ATTACK)
public class SpecialAttack
{
    private final int ENERGY_CONSUMPTION = 20;
    private final int RANGE = 100;
    private final int POWER = 5;

    /**
     * Implémentation de attack
     * @param target cible à attaquer
     * @throws NotEnoughEnergyException lorsqu’il n’y a pas assez d’énergie pour attaquer.
     * @throws NotInRangeException lorsque le robot ennemi n’est pas à portée.
     */
    @Attack(nature = Attack.Nature.MAIN)
    public void attack(IRobot me, IRobot target) throws NotInRangeException, NotEnoughEnergyException
    {
        if(me.getEnergy() >= ENERGY_CONSUMPTION)
        {
            if(me.calculateDistance(target) <= RANGE)
            {
                target.decreaseLife(POWER);
                me.decreaseEnergy(ENERGY_CONSUMPTION);
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
    public int getEnergyConsumption()
    {
        return this.ENERGY_CONSUMPTION;
    }
}
