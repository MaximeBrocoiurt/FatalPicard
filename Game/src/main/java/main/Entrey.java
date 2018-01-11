package main;

import annotations.Plugin;
import engine.War;
import identity.IRobot;
import loader.PluginLoader;
import processor.PluginProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.List;


public class Entrey
{
    public static void main(String[] args)
    {
        //initialisation du classloader & du pluginprocessor
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separator + "plugins");

        PluginLoader pluginLoader = new PluginLoader(basPathPlugin);
        PluginProcessor pluginProcessor = new PluginProcessor();

        List<Class<?>> plugins = pluginLoader.getListClasses();

        for (Class<?> c : plugins)
        {
            System.out.println("Classe trouvée : " + c);
        }

        //initialisation de l’interface graphique
        JFrame f = new JFrame("RobotWar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startButton = new JButton();
        JMenuBar menu = new JMenuBar();

        startButton.setText("TO THE FUTURE");
        JMenu menuAttack = new JMenu("Attaque");
        JMenu menuGraphic = new JMenu("Graphisme");
        JMenu menuMove = new JMenu("Mouvement");

        menu.add(menuAttack);
        menu.add(menuGraphic);
        menu.add(menuMove);

        War w = new War(500, 500, 10, pluginProcessor);

        //mise en place des événements
        for (Class<?> plugin : pluginLoader.getListClasses())
        {
            Annotation a = plugin.getAnnotation(Plugin.class);
            if(a != null)
            {
                System.out.println(((Plugin)a).type() + " : " + plugin.getSimpleName());
                switch(((Plugin)a).type())
                {
                    case ATTACK:
                        menuAttack.add(new JMenuItem(new AbstractAction(plugin.getSimpleName())
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                Class<?> c = pluginLoader.chercherClass(plugin.getSimpleName());
                                for(IRobot r : w.getRobots())
                                {
                                    r.setAttack(c);
                                }
                            }
                        }));
                        break;
                    case GRAPHIC:
                        menuGraphic.add(new JMenuItem(new AbstractAction(plugin.getSimpleName())
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                Class<?> c = pluginLoader.chercherClass(plugin.getSimpleName());
                                for(IRobot r : w.getRobots())
                                {
                                    r.setGraphic(c);
                                }
                            }
                        }));
                        break;
                    case MOVE:
                        menuMove.add(new JMenuItem(new AbstractAction(plugin.getSimpleName())
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                Class<?> c = pluginLoader.chercherClass(plugin.getSimpleName());
                                for(IRobot r : w.getRobots())
                                {
                                    r.setMove(c);
                                }
                            }
                        }));
                        break;
                    default:
                        System.out.println("ne devrait jamais arriver ¯_(ツ)_/¯");
                        break;
                }
            }
        }

        //mise en place des plugins obligatoires
        for(IRobot r : w.getRobots())
        {
            Class<?> c = pluginLoader.chercherClass("SmallRangeAttack");
            r.setAttack(c);
            c = pluginLoader.chercherClass("BaseGraphic");
            r.setGraphic(c);
            c = pluginLoader.chercherClass("HugMove");
            r.setMove(c);
        }

        //finition de la mise en place de l’interface graphique
        f.getContentPane().add(w, BorderLayout.CENTER);
        f.getContentPane().add(menu, BorderLayout.NORTH);
        f.getContentPane().add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> { w.launch(); });

        w.repaint();
        f.pack();
        f.setVisible(true);
    }

    public static void addItemMenu(JMenu menu, PluginLoader pluginLoader, Class<?> c)
    {
        JMenuItem newMenu = new JMenuItem(new AbstractAction(c.getSimpleName()) 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Evenement de base");
                pluginLoader.loadFile(c.getSimpleName());
            }
        });
        menu.add(newMenu);
    }
}