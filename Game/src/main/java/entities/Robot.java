package entities;

import engine.War;
import plugins.attack.IAttack;
import plugins.move.IMove;
import identity.IRobot;
import plugins.attack.LongRangeAtttack;
import plugins.attack.SmallRangeAtttack;
import plugins.move.HugMove;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Robot implements IRobot
{
    private static final int BASE_LIFE = 100, BASE_ENERGY = 100;
    private static final int BASE_WIDTH = 10, BASE_DISTANCE = 5;

    private int life, energy;
    private int x, y;
    private War war;
    private IAttack attack;
    private IMove move;
    private Color c;

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
        this.attack = new Random().nextInt(2) > 1 ? new SmallRangeAtttack() : new LongRangeAtttack();
        this.move = new HugMove();
        Random r = new Random();
        c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
    }

    /**
     * Permet au robot de se dessiner.
     * @param g espace graphique sur lequel il doit se dessiner.
     */
    public void draw(Graphics g)
    {
        g.setColor(c);
        g.fillRect(x - BASE_WIDTH / 2, y - BASE_WIDTH / 2, BASE_WIDTH, BASE_WIDTH);
    }

    /**
     * Demande au robot d’effectuer un mouvement.
     * @param robots
     */
    public void move(ArrayList<IRobot> robots)
    {
        move.move(this, robots);
    }

    /**
     * demande au robot d’en attaquer un autre.
     * @param target
     */
    @Override
    public void attack(IRobot target) throws Exception
    {
        attack.attack(this, target);
    }

    /**
     * Demande au robot de faire quelque chose.
     * @param robots liste des ennemis.
     */
    public void act(ArrayList<IRobot> robots)
    {
        move(robots);
    }

    /**
     * Permet de calculer la distance du robot avec un autre
     * @param r autre robot
     * @return distance
     */
    @Override
    public int calculateDistance(IRobot r)
    {
        return (int)Math.hypot(this.x - r.getX(), this.y - r.getY());
    }

    @Override
    public int getEnergy()
    {
        return energy;
    }

    @Override
    public int getLife()
    {
        return life;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public void decreaseLife(int amount)
    {
        life = life - amount;
        if(life < 0)
            life = 0;
    }

    @Override
    public void increaseLife(int amount)
    {
        life = life + amount;
        if(life > BASE_LIFE)
            life = BASE_LIFE;
    }

    @Override
    public void decreaseEnergy(int amount)
    {
        energy -= amount;
        if(energy < 0)
            energy = 0;
    }

    @Override
    public void increaseEnergy(int amount)
    {
        energy += amount;
        if(energy > BASE_ENERGY)
            energy = BASE_ENERGY;
    }

    public void setAttack(IAttack attackSmallRange) {
        this.attack = attackSmallRange;
    }
}
