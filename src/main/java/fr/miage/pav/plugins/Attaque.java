package fr.miage.pav.plugins;

import fr.miage.pav.entites.Robot;

import java.awt.*;

public class Attaque {
    private Robot me;
    private int range;

    /**
     * Construct atq for robot and range
     * @param me
     */
    public Attaque(Robot me, int range) {
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
            if (me.getEnergie() - 20 <= 0) {
                me.setEnergie(100);
                return "Gain energie";
            } else {
                target.attaquer(power);
            }
            return "-" + power;
        } else {
            return "Missed";
        }
    }
}
