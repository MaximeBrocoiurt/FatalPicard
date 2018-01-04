package entities;

import engine.War;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class RobotTest
{
    @Test
    public void CorrectlyCalculateDistanceX()
    {
        War w = Mockito.mock(War.class);
        Robot r1 = new Robot(0, 0, w);
        Robot r2 = new Robot(500, 0, w);
        assertEquals(500, r1.calculateDistance(r2));
    }

    @Test
    public void CorrectlyCalculateDistanceY()
    {
        War w = Mockito.mock(War.class);
        Robot r1 = new Robot(0, 0, w);
        Robot r2 = new Robot(0, 500, w);
        assertEquals(500, r1.calculateDistance(r2));
    }

    @Test
    public void CorrectlyCalculateDistance()
    {
        War w = Mockito.mock(War.class);
        Robot r1 = new Robot(0, 0, w);
        Robot r2 = new Robot(500, 500, w);
        assertEquals(r1.calculateDistance(r2), r2.calculateDistance(r1));
    }
}