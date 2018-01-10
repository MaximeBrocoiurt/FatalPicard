package engine;

import identity.IRobot;
import entities.Robot;
import loader.PluginLoader;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class War extends JPanel implements Runnable
{
    /**
     * Delai entre deux tours.
     */
    private static final int DELAY = 100;
    private static final int ENERGY_REFILL = 20;

    private ArrayList<IRobot> robots;
    private Thread thread;
    private PluginLoader pl;

    /**
     * Constructeur. DUH.
     * @param width width du terrain, en pixels
     * @param height height du terrain, en pixels
     * @param number number de robots à générer
     */
    public War(int width, int height, int number, PluginLoader pl)
    {
        this.pl=pl;
        setPreferredSize(new Dimension(width, height));
        generateRobots(number, width, height);
    }

    /**
     * Lance un thread
     */
    public void launch()
    {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Implémentation de run.
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        int i = 0;
        ArrayList<IRobot> ennemis;
        while(robots.size() > 1)
        {
            System.out.format("Début du tour du robot ; il a %d vie, %d énergie et se trouve en %d;%d\n", robots.get(i).getLife(), robots.get(i).getEnergy(), robots.get(i).getX(), robots.get(i).getY());
            robots.get(i).increaseEnergy(ENERGY_REFILL);
            ennemis = new ArrayList<>(robots);
            ennemis.remove(i);
            try{
            robots.get(i).act(ennemis);}catch (Exception e) {e.printStackTrace();}
            System.out.println("Le robot a agi.");
            removeDestroyedRobots();
            repaint();
            try
            {
                Thread.sleep(DELAY / robots.size());
            } catch(Exception e) {}
            i = (i + 1) % robots.size();
        }
        System.out.println("Fin du duel " + robots.get(0) + " gagne avec " + robots.get(0).getLife() + " point de vie");
    }

    /**
     * Permet de retirer tous les robots dont la vie est inférieure à zéro.
     */
    private void removeDestroyedRobots()
    {
        robots.removeIf(r -> r.getLife() <= 0);
    }

    /**
     * Permet de gￃﾩnￃﾩrer des robots positionnￃﾩs alFatoirement sur la war.
     * @param number number de robots ￃﾠ gￃﾩnￃﾩrer.
     */
    public void generateRobots(int number, int width, int height)
    {
        robots = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < number; i++)
        {
            robots.add(new Robot(r.nextInt(width), r.nextInt(height), this.pl));
        }
    }

    /**
     * Accesseur de robots.
     * @return liste des robots prￃﾩsents sur la war
     */
    public ArrayList<IRobot> getRobots()
    {
        return robots;
    }

    @Override
    public void paint(Graphics gr)
    {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3f));
        super.paint(g);
        for(IRobot r : robots)
        {
            try{r.draw(g);}catch (Exception e){}
        }
    }
}