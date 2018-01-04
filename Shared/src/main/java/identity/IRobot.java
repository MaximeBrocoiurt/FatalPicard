package identity;

public interface IRobot
{
    int getEnergy();
    int getLife();
    int getX();
    int getY();
    void setEnergy(int energy);
    void setLife(int life);
    int calculateDistance(IRobot robot);
    boolean verifyRange(IRobot target);
    void attack(int decreaseLife);
}
