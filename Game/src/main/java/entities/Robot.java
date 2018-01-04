package entities;

import engine.War;
import plugins.IAttack;
import plugins.IMove;
import plugins.attacks.Attack;
import identity.IRobot;

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
    private IMove move;

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
        this.attack = new Attack(this, 10, 10);
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
    public void move(ArrayList<IRobot> robots)
    {

    }

    /**
     * Vérifie sur le robot fourni en paramètre est à portée de tir.
     * @param target cible
     * @return vrai si la cible est à portée
     */
    @Override
    public boolean checkRange(IRobot target)
    {
        return calculateDistance(target) <= attack.getRange();
    }

    /**
     * Demande au robot de faire quelque chose.
     * @param robots liste des
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
        life -= amount;
    }

    @Override
    public void increaseLife(int amount)
    {
        life += amount;
    }

    @Override
    public void decreaseEnergy(int amount)
    {
        life -= amount;
    }

    @Override
    public void increaseEnergy(int amount)
    {
        life += amount;
    }


}
