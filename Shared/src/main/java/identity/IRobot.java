package identity;

import java.awt.*;
import java.util.ArrayList;

public interface IRobot
{
    void act(ArrayList<IRobot> foes);
    void draw(Graphics g);
    void attack(IRobot target) throws Exception;
    int calculateDistance(IRobot robot);
    boolean checkRange(IRobot robot);
    void decreaseLife(int amount);
    void increaseLife(int amount);
    void decreaseEnergy(int amount);
    void increaseEnergy(int amount);
    int getEnergy();
    int getLife();
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
}
