package annotedPlugins.attack;

import annotations.Attack;
import annotations.Plugin;
import annotedPlugins.attack.exceptions.NotEnoughEnergyException;
import annotedPlugins.attack.exceptions.NotInRangeException;
import identity.IAttack;
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
