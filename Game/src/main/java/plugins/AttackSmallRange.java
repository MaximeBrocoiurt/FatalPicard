package plugins;

import entities.Robot;
import identity.IRobot;

import java.awt.*;

public class AttackSmallRange implements IAttack{
    /**
     * Range et power for melee attack
     */
    private static final int RANGEMELEE = 10;
    private static final int POWERMELEE = 10;

    private Robot me;
    private int range;
    private int power;

    /**
     * Construct atq for robot and range
     * @param me
     */
    public AttackSmallRange(Robot me) {
        this.range = RANGEMELEE;
        this.me = me;
        this.power = POWERMELEE;
    }

    @Override
    public int power() {
        return this.power;
    }

    @Override
    public int range() {
        return this.range;
    }

    @Override
    public String atqRobot(IRobot target) {
        if(me.verifyRange(target)) {
            if (me.getEnergy() - 20 < 0) {
                me.setEnergy(100);
                return "Gain energie";
            } else if(target.getLife() - power <= 0) {
                target.setLife(0);
                return "killed";
            } else {
                target.attack(power);
                return "-" + power;
            }
        } else {
            return "Missed";
        }
    }

    /**
     * Method for draw a basic graphic like a rec
     * @param draw
     */
    public void drawAtq(Graphics draw) {

    }
}
