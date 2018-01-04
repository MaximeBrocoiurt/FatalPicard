package plugins;

import java.awt.*;

public interface IAttack
{
    public int power();
    public int range();
    public void attack(Point cible);
}
