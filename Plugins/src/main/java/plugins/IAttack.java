package plugins;

import identity.IRobot;

import java.awt.*;

public interface IAttack
{
    int power();
    int range();
    String atqRobot(IRobot target);
}
