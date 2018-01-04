package plugins;

import identity.IRobot;

public interface IAttack
{
    int power();
    int range();
    String atqRobot(IRobot target);
}
