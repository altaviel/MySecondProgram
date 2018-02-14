package ru.stqa.pft.sandbox;

public class Distance {
    public static void main(String[] args) {

        Point p1 = new Point(5,7);
        Point p2 = new Point (3,8);


        System.out.println("Расстояние на плоскости между точками с координатами " + p1.x + "," + p1.y + " и " + p2.x + "," + p2.y + " = " + p1.distance(p2));

    }
}
