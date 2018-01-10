package identity;

public interface IAttack
{
    int getPower();
    int getRange();
    void attack(IRobot me, IRobot target) throws Exception;
}
