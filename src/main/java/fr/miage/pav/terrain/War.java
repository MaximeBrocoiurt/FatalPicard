package fr.miage.pav.terrain;

import fr.miage.pav.entites.Robot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class War extends JPanel implements Runnable
{
    private static final int DELAI = 50;

    private ArrayList<Robot> robots;
    private int longueur, largeur;
    private Thread t;

    /**
     * Constructeur. DUH.
     * @param longueur longueur du terrain, en pixels
     * @param largeur largeur du terrain, en pixels
     * @param nombre nombre de robots à générer
     */
    public War(int longueur, int largeur, int nombre)
    {
        this.longueur = longueur;
        this.largeur = largeur;
        this.robots = genererRobots(nombre);
        setPreferredSize(new Dimension(longueur, largeur));
    }

    public void demarrer()
    {
        t = new Thread(this);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run()
    {
        int i = 0, nombreDeRobotsVivants = (int)robots.stream().filter(r -> r.getVie() > 0).count();
        while(nombreDeRobotsVivants > 0)
        {
            if(robots.get(i).getVie() > 0)
            {
                robots.get(i).agir(robots);
            }
            i = (i + 1) % nombreDeRobotsVivants;
            try{Thread.sleep(DELAI);} catch(Exception e) {}
            repaint();
        }
    }

    /**
     * Permet de gￃﾩnￃﾩrer des robots positionnￃﾩs alￃﾩatoirement sur la war.
     * @param nombre nombre de robots ￃﾠ gￃﾩnￃﾩrer.
     */
    public ArrayList<Robot> genererRobots(int nombre)
    {
        ArrayList<Robot> retour = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < nombre; i++)
        {
            retour.add(new Robot(r.nextInt(longueur), r.nextInt(largeur), this));
        }
        return retour;
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
    public ArrayList<Robot> getRobotsVivants()
    {
        return robots.stream().filter(r -> r.getVie() > 0).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void paint(Graphics gr)
    {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3f));
        super.paint(g);
        for(Robot r : robots)
            r.dessiner(g);
    }
}