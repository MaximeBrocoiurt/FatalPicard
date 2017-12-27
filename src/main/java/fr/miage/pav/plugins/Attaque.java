package fr.miage.pav.plugins;

import fr.miage.pav.entites.Robot;

import java.awt.*;

public class Attaque {
    private Robot me;

    public Attaque(Robot me) {
        this.me = me;
    }

    public boolean verifRange(Robot target, int range) {
        return true;
    }

    public void drawAtq(Graphics draw) {

    }

    public void atqRobot(Robot target, int power) {

    }
}
