package fr.miage.pav;

import fr.miage.pav.terrain.War;

import javax.swing.*;
import java.awt.*;

public class Entrey
{
    public static void main(String[] args)
    {

        JFrame f = new JFrame("RobotWar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        War w = new War(500, 500, 2);
        f.getContentPane().add(w, BorderLayout.CENTER);
        new Thread(w).start();

        f.pack();
        f.setVisible(true);
    }
}
