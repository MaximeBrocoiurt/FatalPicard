package identity;

import exceptions.NotEnoughEnergyException;
import exceptions.NotInRangeException;
import identity.exceptions.NoPluginException;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface IRobot
{
    void move(ArrayList<IRobot> foes) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    void draw(Graphics g) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    void attack(IRobot target) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    int calculateDistance(IRobot robot);
    void decreaseLife(int amount);
    void increaseLife(int amount);
    void decreaseEnergy(int amount) throws NotEnoughEnergyException;
    void increaseEnergy(int amount);
    int getEnergy();
    int getLife();
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    void setAttack(Class<?> attack);
    void setGraphic(Class<?> attack);
    void setMove(Class<?> attack);
    Object getAttack();
    Object getGraphic();
    Object getMove();
}
