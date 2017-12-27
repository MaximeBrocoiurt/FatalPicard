package fr.miage.pav.plugins;

import fr.miage.pav.entites.Robot;

import java.awt.*;

public class Attaque {
    private Robot me;

    public Attaque(Robot me) {
        this.me = me;
    }

    /**
     * Method for detect if the target can be atq by the robot
     * @param target
     * @param range
     * @return
     */
    public boolean verifRange(Robot target, int range) {
        int absTarget = target.getX();
        int ordTarget = target.getY();
        int absMe = me.getX();
        int ordMe = me.getY();

        return (Math.abs(absMe - absTarget) <= range) && (Math.abs(ordMe - ordTarget)) <= range;
    }

    public void drawAtq(Graphics draw) {

    }

    public void atqRobot(Robot target, int power) {

    }
}
