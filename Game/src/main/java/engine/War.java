package engine;

import entities.Robot;
import identity.IRobot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class War extends JPanel implements Runnable
{
    /**
     * Delai entre deux tours.
     */
    private static final int DELAY = 50;
    private static final int ENERGY_REFILL = 20;

    private ArrayList<IRobot> robots;
    private Thread thread;

    /**
     * Constructeur. DUH.
     * @param width width du terrain, en pixels
     * @param height height du terrain, en pixels
     * @param number number de robots à générer
     */
    public War(int width, int height, int number)
    {
        setPreferredSize(new Dimension(width, height));
        generateRobots(number, width, height);
    }

    /**
     * Lance un thread
     */
    public void launch()
    {
        thread = new Thread(this);
    }

    /**
     * Implémentation de run.
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        int i = 0;
        while(robots.size() > 1)
        {
            robots.get(i).increaseEnergy(ENERGY_REFILL);
            robots.get(i).act(robots);
            removeDestroyedRobots();
            repaint();
            try
            {
                Thread.sleep(DELAY);
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
            robots.add(new Robot(r.nextInt(width), r.nextInt(height), this));
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
            r.draw(g);
        }
    }
}