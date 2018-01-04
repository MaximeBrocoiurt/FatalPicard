import engine.War;

import javax.swing.*;
import java.awt.*;

public class Entrey
{
    public static void main(String[] args)
    {
//        File basPathPlugin = new File("C:" + File.separator + "Users"+ File.separator +
//                "Utilisateur" + File.separator + "Documents" + File.separator + "FatalPicard" +
//                File.separator + "src" + File.separator + "main" + File.separator + "resources");
//        Repository myLoader = new Repository(basPathPlugin);
//        List<Class<?>> myPlugin = myLoader.load();
//        System.out.println(myPlugin.get(0).toString());

        JFrame f = new JFrame("RobotWar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        War w = new War(500, 500, 4);
        f.getContentPane().add(w, BorderLayout.CENTER);
//        new Thread(w).start();
        w.launch();

        f.pack();
        f.setVisible(true);
    }
}
