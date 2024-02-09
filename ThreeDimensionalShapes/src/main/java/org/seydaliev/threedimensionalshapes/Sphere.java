package org.seydaliev.threedimensionalshapes;

public class Sphere {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double calculateSurfaceArea() {
        return  4 * Math.PI * Math.pow(radius,  2);
    }

    public double calculateVolume() {
        return ((double) 4 / 3) * Math.PI * Math.pow(radius,  3);
    }
}
