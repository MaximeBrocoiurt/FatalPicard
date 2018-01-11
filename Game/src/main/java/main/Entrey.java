package main;

import annotations.Attack;
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

import static java.lang.System.exit;

public class Entrey
{
    public static void main(String[] args)
    {
        //On indique où se trouve le dossier contenant les .jar pour y chercher toutes les classes qu'ils faut charger
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separatorChar + "plugins" + File.separatorChar + "target");
//        File basPathPlugin = new File(System.getProperty("user.dir") + File.separator + "plugins");


        PluginLoader myLoader = new PluginLoader(basPathPlugin);
        //Une fois chargé, elles sont disponible dans cette liste
        List<Class<?>> myPlugin = myLoader.load();
        //System.out.println(myPlugin);

        PluginProcessor pluginProcessor = new PluginProcessor();

        JFrame f = new JFrame("RobotWar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton start = new JButton();
        start.setText("Start Game");

        JMenuBar menu = new JMenuBar();
        JMenu menuAttack = new JMenu("IAttack");
        JMenu menuMove = new JMenu("IMove");
        JMenu menuGraphique = new JMenu("IGraphique");

        menu.add(menuAttack);
        menu.add(menuMove);
        menu.add(menuGraphique);

        //TODO: item à mettre dans le menu avec les classes chargé
        addItemMenu(menuAttack);
        addListener(menuAttack);

        War w = new War(500, 500, 20, pluginProcessor);
        for(IRobot r : w.getRobots())
        {
            r.setAttack(myLoader.chercherClass("LongRangeAttack"));
            r.setGraphic(myLoader.chercherClass("RangeGraphic"));
            r.setMove(myLoader.chercherClass("SchwarzeneggerMove"));
        }
        
        f.getContentPane().add(menu, BorderLayout.NORTH);
        f.getContentPane().add(w, BorderLayout.CENTER);
        f.getContentPane().add(start, BorderLayout.SOUTH);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                w.launch();
            }
        });

        f.pack();
        f.setVisible(true);
    }

    /**
     * Method for add item in menu passed in parameters
     * @param menu
     */
    private static void addItemMenu(JMenu menu) {
        JMenuItem newMenu = new JMenuItem("test");
        menu.add(newMenu);
    }

    /**
     * method use for add listener for tab type
     * @param tab
     */
    private static void addListener(JMenu tab) {
        switch (tab.getText()) {
            case "IAttack":
                for (int i=0; i<tab.getItemCount(); i++) {
                    JMenuItem item = tab.getItem(i);
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Evenement de base");
                        }
                    });
                }
                break;
            case "IMove":
                break;
            case "IGraphique":
                break;
        }
    }
}
