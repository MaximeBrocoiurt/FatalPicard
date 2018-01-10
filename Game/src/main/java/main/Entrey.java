package main;

import engine.War;
import loader.PluginLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Timer;


public class Entrey
{
    public static void main(String[] args)
    {
        //On indique où se trouve le dossier contenant les .jar pour y chercher toutes les classes qu'ils faut charger
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separator + "plugins");
        // System.out.println("Chemin plugins " + basPathPlugin.getPath());
        PluginLoader myLoader = new PluginLoader(basPathPlugin);
        //Une fois chargé, elles sont disponible dans cette liste


        //System.out.println(myPlugin);
        for(Class classe : myLoader.getListClasses()){
            System.out.println("Classes chargées du plugin : "+classe.getName());
        }

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

        for(Class classe : myLoader.getListClasses()){
           // System.out.println("Classes chargées du plugin : "+classe.getName());
            Class[] interfaces=classe.getInterfaces();
            for(int i=0;i<interfaces.length;i++){
                if(interfaces[i].getName().contains("IGraphic")) {
                    addItemMenu(menuGraphique, classe.getSimpleName());
                }
                if(interfaces[i].getName().contains("IAttack")) {
                    addItemMenu(menuAttack,  classe.getSimpleName());
                }
                if(interfaces[i].getName().contains("IMove")) {
                    addItemMenu(menuMove,  classe.getSimpleName());
                }
               // System.out.println(interfaces[i].getName());
            }

        }


        f.getContentPane().add(menu, BorderLayout.NORTH);

        War w = new War(500, 500, 10,myLoader);


        f.getContentPane().add(w, BorderLayout.CENTER);
        f.getContentPane().add(start, BorderLayout.SOUTH);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer= new Timer();
                timer.schedule(new PluginLoader(basPathPlugin), 0, 5000);
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
    private static void addItemMenu(JMenu menu, String message) {
        JMenuItem newMenu = new JMenuItem(message);
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
