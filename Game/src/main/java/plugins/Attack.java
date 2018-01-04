package plugins;

import entities.Robot;

import java.awt.*;

public class Attack {
    private Robot me;
    private int range;

    /**
     * Construct atq for robot and range
     * @param me
     */
    public Attack(Robot me, int range) {
        this.range = range;
        this.me = me;
    }

    /**
     * Method for detect if the target can be atq by the robot
     * @param target
     * @return
     */
    public boolean verifRange(Robot target) {
        int absTarget = target.getX();
        int ordTarget = target.getY();
        int absMe = me.getX();
        int ordMe = me.getY();

        return (Math.abs(absMe - absTarget) <= range) && (Math.abs(ordMe - ordTarget)) <= range;
    }

    /**
     * Method for draw a basic graphic like a rec
     * @param draw
     */
    public void drawAtq(Graphics draw) {

    }

    /**
     * Method for atq target and decrease that life
     * @param target
     * @param power
     */
    public String atqRobot(Robot target, int power) {
        if(verifRange(target)) {
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
}
