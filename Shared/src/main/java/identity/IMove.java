package identity;

import identity.IRobot;

import java.util.ArrayList;

public interface IMove
{
    public void move(IRobot subject, ArrayList<IRobot> foes);
}
