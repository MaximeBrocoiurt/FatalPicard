package processor;

import annotations.Attack;
import annotations.Plugin;
import identity.IRobot;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * sert à interpréter les plugins après leur chargement
 */
public class PluginProcessor
{
    public static void test(Class<?> test, ArrayList<IRobot> robotArrayList) throws Exception
    {
        System.out.print(test.getCanonicalName() +" ");
        if(test.getAnnotation(Plugin.class) == null)
        {
            System.out.println("va niquer ta mère");
        } else
        {
            switch(test.getAnnotation(Plugin.class).type())
            {
                case ATTACK:
                    System.out.println("watahshi ha attack da");

                    break;
                case GRAPHIC:
                    System.out.println("watahshi ha graphic da");

                    break;
                case MOVE:
                    System.out.println("watahshi ha move da");
                    break;
            }

        }
    }
}