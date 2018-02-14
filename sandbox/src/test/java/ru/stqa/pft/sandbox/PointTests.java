package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Math.sqrt;

public class PointTests {

    @Test
    public void testArea(){

        Point p1 = new Point(4,8);
        Point p2 = new Point (10,16);
        Assert.assertEquals(p1.distance(p2),10.0);



    }
    @Test
    public void testArea2(){

        Point p1 = new Point (3,5);
        Point p2 = new Point (13,10);
        Assert.assertEquals(p1.distance(p2),sqrt(125));
    }

}
