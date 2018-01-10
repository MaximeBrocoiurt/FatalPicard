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


public class Entrey {
    public static void main(String[] args) {
        //On indique où se trouve le dossier contenant les .jar pour y chercher toutes les classes qu'ils faut charger
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "target");
        // System.out.println("Chemin plugins " + basPathPlugin.getPath());
        PluginLoader myLoader = new PluginLoader(basPathPlugin);
        //Une fois chargé, elles sont disponible dans cette liste

        List<Class<?>> myPlugin=myLoader.getListClasses();


        //System.out.println(myPlugin);
        for (Class classe : myPlugin) {
            System.out.println("Classes trouvées : " + classe);
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

        War w = new War(500, 500, 10, myLoader);

        for (Class classe : myLoader.getListClasses()) {
            // System.out.println("Classes chargées du plugin : "+classe.getName());
            Class[] interfaces = classe.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i].getName().contains("IGraphic")) {
                    menuGraphique.add(new JMenuItem(new AbstractAction(classe.getSimpleName()) {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("IGraphic");
                            Class c= myLoader.chercherClass(classe.getSimpleName());
                            for(int i=0;i<w.getRobots().size();i++){
                                w.getRobots().get(i).setGraphic(c);
                            }
                        }
                    }));
                } else if (interfaces[i].getName().contains("IAttack")) {
                    menuAttack.add(new JMenuItem(new AbstractAction(classe.getSimpleName()) {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("IAttack");
                           Class c= myLoader.chercherClass(classe.getSimpleName());
                            for(int i=0;i<w.getRobots().size();i++){
                                w.getRobots().get(i).setAttack(c);
                            }

                        }
                    }));
                } else if (interfaces[i].getName().contains("IMove")) {
                    menuMove.add(new JMenuItem(new AbstractAction(classe.getSimpleName()) {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("IMove");
                            Class c= myLoader.chercherClass(classe.getSimpleName());
                            for(int i=0;i<w.getRobots().size();i++){
                                w.getRobots().get(i).setMove(c);
                            }

                        }
                    }));
                }
              //  System.out.println(interfaces[i].getName());
            }

        }

        f.getContentPane().add(menu, BorderLayout.NORTH);

        f.getContentPane().add(w, BorderLayout.CENTER);

        f.getContentPane().add(start, BorderLayout.SOUTH);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                w.launch();
            }
        });

        f.pack();
        f.setVisible(true);
    }

    public static void addItemMenu(JMenu menu, PluginLoader myLoader, Class classe){
        JMenuItem newMenu = new JMenuItem(new AbstractAction(classe.getSimpleName()) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Evenement de base");
                myLoader.loadFile(classe.getSimpleName());
            }
        });
        menu.add(newMenu);
    }

}
