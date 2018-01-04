package plugins;

import identity.IRobot;

public interface IAttack
{
    int getPower();
    int getRange();
    void attack(IRobot target) throws Exception;
}
