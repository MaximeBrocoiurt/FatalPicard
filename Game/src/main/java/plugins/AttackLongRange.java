package plugins;

import entities.Robot;
import identity.IRobot;

import java.awt.*;

public class AttackLongRange implements IAttack{
    /**
     * Range et power for melee attack
     */
    private static final int LONGRANGE = 50;
    private static final int POWERDISTANCE = 5;

    private Robot me;
    private int range;
    private int power;

    /**
     * Construct atq for robot and range
     * @param me
     */
    public AttackLongRange(Robot me) {
        this.range = LONGRANGE;
        this.me = me;
        this.power = POWERDISTANCE;
    }

    @Override
    public int power() {
        return 0;
    }

    @Override
    public int range() {
        return 0;
    }

    @Override
    public String atqRobot(IRobot target) {
        return null;
    }

    /**
     * Method for draw a basic graphic like a rec
     * @param draw
     */
    public void drawAtq(Graphics draw) {

    }
}
