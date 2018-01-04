package engine;

import entities.Robot;

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

    private ArrayList<Robot> robots;
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
        int i = 0, numberOfAliveRobots = (int)robots.stream().filter(r -> r.getLife() > 0).count();
        while(numberOfAliveRobots > 1)
        {
            int lifeTarget = robots.get((i + 1) % numberOfAliveRobots).getLife();
            if(robots.get(i).getLife() > 0)
            {
                robots.get(i).act(robots);
                String out = robots.get(i).getAttackSmallRange().atqRobot(robots.get(i).findCloser(robots));
                System.out.println(out);
            }
            robots = winner(robots);
            numberOfAliveRobots = (int)robots.stream().filter(r -> r.getLife() > 0).count();
            i = (i + 1) % numberOfAliveRobots;
            try{Thread.sleep(DELAY);} catch(Exception e) {}
            repaint();
            System.out.println(numberOfAliveRobots);
        }
        System.out.println("Fin du duel " + robots.get(0) + " gagne avec " + robots.get(0).getLife() + " point de vie");
    }

    private ArrayList<Robot> winner(ArrayList<Robot> listRobots) {
        robots.removeIf(r -> r.getLife() == 0);
        return listRobots;
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
    public ArrayList<Robot> getRobots()
    {
        return robots;
    }

    /**
     * Retourne une liste des robots encore en vie
     * @return liste des robots avec une vie supￃﾩrieure ￃﾠ 0.
     */
    public ArrayList<Robot> getAliveRobots()
    {
        return robots.stream().filter(r -> r.getLife() > 0).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void paint(Graphics gr)
    {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3f));
        super.paint(g);
        for(Robot r : robots)
            r.draw(g);
    }
}