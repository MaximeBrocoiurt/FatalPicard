package entities;

import annotations.Attack;
import annotations.Graphic;
import annotations.Move;
import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.IRobot;
import processor.PluginProcessor;
import processor.Tuple;


import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Robot implements IRobot
{
    private static final int BASE_LIFE = 100, BASE_ENERGY = 100;
    private static final int BASE_DISTANCE = 5;

    private PluginProcessor pluginProcessor;
    private int life, energy;
    private int x, y;
    private Tuple<?> attack;
    private Tuple<?> move;
    private Tuple<?> graphic;

    /**
     * Constructeur.
     * @param x position en absisses du robot
     * @param y position en ordonnées du robot
     * @param pluginProcessor
     */
    public Robot(int x, int y, PluginProcessor pluginProcessor)
    {
        this.life = BASE_LIFE;
        this.energy = BASE_ENERGY;
        this.x = x;
        this.y = y;
        this.pluginProcessor = pluginProcessor;
    }

    /**
     * demande au robot d’en attaquer un autre.
     * @param target
     */
    @Override
    public void attack(IRobot target) throws InvocationTargetException
    {
        if(attack != null)
        {
            try
            {
                pluginProcessor.executeMethod(attack, Attack.Nature.MAIN, this, target);
            }
            catch (InvocationTargetException e)
            {
                if(e.getCause().getClass() == NotEnoughEnergyException.class)
                {
                    throw new InvocationTargetException(new NotEnoughEnergyException());
                }
                else if(e.getCause().getClass() == NotInRangeException.class)
                {
                    throw new InvocationTargetException(new NotInRangeException());
                }
                else
                {
                    throw new InvocationTargetException(e);
                }
            }
        }
    }

    /**
     * Permet au robot de se dessiner.
     * @param g espace graphique sur lequel il doit se dessiner.
     */
    @Override
    public void draw(Graphics g) throws InvocationTargetException
    {
        if(graphic != null)
        {
            pluginProcessor.executeMethod(graphic, Graphic.Nature.MAIN, this, g);
        }
    }

    /**
     * Demande au robot d’effectuer un mouvement.
     * @param robots
     */
    @Override
    public void move(ArrayList<IRobot> robots) throws InvocationTargetException
    {
        if(move != null)
        {
            try
            {
                pluginProcessor.executeMethod(move,  Move.Nature.MAIN, this, robots);
            }
            catch (InvocationTargetException e)
            {
                if(e.getCause().getClass() == NotEnoughEnergyException.class)
                {
                    System.out.println("Pas assez de mana.");
                } else if(e.getCause().getClass() == NotInRangeException.class)
                {
                    System.out.println("Je suis trop loin.");
                }
            }
        }
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

    @Override
    public void setAttack(Class<?> c)
    {
        try
        {
            this.attack = new Tuple<>(c);
        } catch (Exception e) {}
    }

    @Override
    public void setGraphic(Class<?> c)
    {
        try
        {
            this.graphic = new Tuple<>(c);
        } catch (Exception e) {}
    }

    @Override
    public void setMove(Class<?> c) {
        try
        {
            this.move = new Tuple<>(c);
        } catch (Exception e) {}
    }

    @Override
    public Object getAttack() {
        return attack.instance;
    }

    @Override
    public Object getGraphic() {
        return graphic.instance;
    }

    @Override
    public Object getMove() {
        return move.instance;
    }
}
