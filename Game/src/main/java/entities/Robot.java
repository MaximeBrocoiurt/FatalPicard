package entities;

import engine.War;
import plugins.AttackSmallRange;
import identity.IRobot;
import plugins.IAttack;

import java.awt.*;
import java.util.ArrayList;

public class Robot implements IRobot
{
    private static final int BASE_LIFE = 100, BASE_ENERGY = 100;
    private static final int BASE_WIDTH = 10, BASE_DISTANCE = 5;

    private int life, energy;
    private int x, y;
    private War war;
    private IAttack attack;

    /**
     * Constructeur.
     * @param x position en absisses du robot
     * @param y position en ordonnées du robot
     * @param war war dont dépend le robot
     */
    //TODO examiner l’intéret de fournir war en paramètre
    public Robot(int x, int y, War war)
    {
        this.life = BASE_LIFE;
        this.energy = BASE_ENERGY;
        this.x = x;
        this.y = y;
        this.war = war;
    }

    /**
     * Permet au robot de se dessiner.
     * @param g espace graphique sur lequel il doit se dessiner.
     */
    public void draw(Graphics g)
    {
        g.fillRect(x - BASE_WIDTH / 2, y - BASE_WIDTH / 2, BASE_WIDTH, BASE_WIDTH);
    }

    /**
     * Demande au robot d’effectuer un mouvement.
     * @param robots
     */
    public void move(ArrayList<Robot> robots)
    {
        Robot closer = findCloser(robots);
        int distance = closer.calculateDistance(this);
        if(distance == 0)
        {
            distance = 1;
        }
        x = (BASE_DISTANCE * (closer.getX() - x)) / distance + x;
        if(x < BASE_WIDTH / 2)
        {
            x = BASE_WIDTH / 2;
        } else if(x > war.getWidth() - BASE_WIDTH / 2)
        {
            x = war.getWidth() + BASE_WIDTH / 2;
        }
        y = (BASE_DISTANCE * (closer.getY() - y)) / distance + y;
        if(y < BASE_WIDTH / 2)
        {
            y = BASE_WIDTH / 2;
        } else if(y > war.getHeight() - BASE_WIDTH / 2)
        {
            y = war.getHeight() + BASE_WIDTH / 2;
        }
    }

    /**
     * Method for detect if the target can be atq by the robot
     * @param target
     * @return
     */
    public boolean verifyRange(IRobot target) {
        int absTarget = target.getX();
        int ordTarget = target.getY();
        int absMe = this.x;
        int ordMe = this.y;

        return (Math.abs(absMe - absTarget) <= attack.range()) && (Math.abs(ordMe - ordTarget)) <= attack.range();
    }

    public void attack(int decrease)
    {
        life -= decrease;
        energy -= 20;
    }

    public void act(ArrayList<Robot> robots)
    {
        move(robots);
    }

    /**
     * Permet de calculer la distance du robot avec un autre
     * @param r autre robot
     * @return distance
     */
    public int calculateDistance(IRobot r)
    {
        return (int)Math.hypot(this.x - r.getX(), this.y - r.getY());
    }

    /**
     * Permet de trouver le robot le plus proche
     * @param robots liste des robots
     * @return robot le plus proche
     */
    public Robot findCloser(ArrayList<Robot> robots)
    {
        Robot closer = null;
        int minimalDistance = Integer.MAX_VALUE, tampon;
        for(Robot r : robots)
        {
            if(!r.equals(this))
            {
                tampon = r.calculateDistance(this);
                if(tampon < minimalDistance)
                {
                    minimalDistance = tampon;
                    closer = r;
                }
            }
        }
        return closer;
    }

    public int getEnergy()
    {
        return energy;
    }

    public int getLife()
    {
        return life;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public IAttack getAttack() {
        return attack;
    }

    public void setAttack(IAttack attackSmallRange) {
        this.attack = attackSmallRange;
    }
}
