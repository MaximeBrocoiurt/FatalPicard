package entities;

import annotations.Attack;
import identity.IRobot;
import loader.PluginLoader;


import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Robot implements IRobot
{
    private static final int BASE_LIFE = 100, BASE_ENERGY = 100;
    private static final int BASE_DISTANCE = 5;

    private PluginLoader pl;
    private int life, energy;
    private int x, y;
    private Class<?> attack;
    private Class<?> move;
    private Class<?> drawRobot;

    /**
     * Constructeur.
     * @param x position en absisses du robot
     * @param y position en ordonnées du robot
     *
     */
    //TODO examiner l’intéret de fournir war en paramètre
    public Robot(int x, int y, PluginLoader pl) {
        this.life = BASE_LIFE;
        this.energy = BASE_ENERGY;
        this.x = x;
        this.y = y;
        this.pl=pl;

        Random r = new Random();
        System.out.println(r);
        try {
//            this.attack = r.nextInt(2) < 1 ?
//                    (IAttack) pl.chercherClass("SmallRangeAtttack").newInstance() :
//                    (IAttack) pl.chercherClass("LongRangeAtttack").newInstance();
//            this.move = r.nextInt(3) < 1 ? (IMove) pl.chercherClass("HugMove").newInstance() : r.nextInt(2) < 1 ? (IMove) pl.chercherClass("SchwarzeneggerMove").newInstance() : (IMove) pl.chercherClass("RandomMove").newInstance();
//            this.drawRobot = r.nextInt(2) < 1 ? (IGraphic) pl.chercherClass("BaseGraphic").newInstance() : (IGraphic) pl.chercherClass("HealthBarGraphic").newInstance();
        } catch (NullPointerException e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            e.getCause();
        }
    }

    /**
     * Permet au robot de se dessiner.
     * @param g espace graphique sur lequel il doit se dessiner.
     */
    public void draw(Graphics g) throws Exception
    {
        if(drawRobot != null)
        {
            for(Method m : attack.getDeclaredMethods())
            {
                if(m.getDeclaredAnnotation(Attack.class).nature() == Attack.Nature.MAIN)
                {
                    Object instance = attack.newInstance();
                    System.out.println("TA MERE LA GROSSE CHIENNE ");
                    m.invoke(instance,g);
                }
            }
        }
    }

    /**
     * Demande au robot d’effectuer un mouvement.
     * @param robots
     */
    public void move(ArrayList<IRobot> robots) throws Exception
    {
        if(move != null)
        {
            for(Method m : attack.getDeclaredMethods())
            {
                if(m.getDeclaredAnnotation(Attack.class).nature() == Attack.Nature.MAIN)
                {
                    Object instance = attack.newInstance();
                    System.out.println("TA MERE LA GROSSE CHIENNE ");
                    m.invoke(instance,this, robots);
                }
            }
        }
    }

    /**
     * demande au robot d’en attaquer un autre.
     * @param target
     */
    @Override
    public void attack(IRobot target) throws Exception
    {
        if(attack != null)
        {
            for(Method m : attack.getDeclaredMethods())
            {
                if(m.getDeclaredAnnotation(Attack.class).nature() == Attack.Nature.MAIN)
                {
                    Object instance = attack.newInstance();
                    System.out.println("TA MERE LA GROSSE CHIENNE ");
                    m.invoke(instance,this, target);
                }
            }
        }
    }

    /**
     * Demande au robot de faire quelque chose.
     * @param robots liste des ennemis.
     */
    public void act(ArrayList<IRobot> robots) throws Exception
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

    @Override
    public void setAttack(Class<?> o)
    {
        this.attack = o;
    }

    @Override
    public void setGraphic(Class<?> attack) {
        this.drawRobot = attack;
    }

    @Override
    public void setMove(Class<?> attack) {
        this.move = attack;
    }
}
