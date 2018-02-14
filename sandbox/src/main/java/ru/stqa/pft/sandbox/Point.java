package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double distance(Point second) {

                return Math.sqrt(Math.pow((second.x - this.x), 2) + Math.pow((second.y - this.y), 2));
    }
}
