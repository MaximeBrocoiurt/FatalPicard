package main;

import engine.War;
import loader.PluginLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Entrey
{
    public static void main(String[] args)
    {
        //On indique où se trouve le dossier contenant les .jar pour y chercher toutes les classes qu'ils faut charger
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separatorChar + "plugins"+ File.separatorChar + "target");
       // System.out.println("Chemin plugins " + basPathPlugin.getPath());
        PluginLoader myLoader = new PluginLoader(basPathPlugin);

        //Une fois chargé, elles sont disponible dans cette liste
        List<Class<?>> myPlugin = myLoader.load();
        //System.out.println(myPlugin);
        for(Class classe : myPlugin){
            System.out.println("Classes chargées du plugin : "+classe.getName());
        }

        JFrame f = new JFrame("RobotWar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        War w = new War(500, 500, Integer.valueOf(args[0]));
        f.getContentPane().add(w, BorderLayout.CENTER);
        w.launch();

        f.pack();
        f.setVisible(true);
    }
}
