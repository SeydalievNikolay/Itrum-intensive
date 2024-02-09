package org.seydaliev.model;

import org.seydaliev.Shape;

public class Triangle extends Shape {
    private double base;
    private double height;
    private double side;

    public Triangle(double base, double height, double side) {
        this.base = base;
        this.height = height;
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return  0.5 * base * height;
    }

    @Override
    public double calculatePerimeter() {
        double p = Math.sqrt(Math.pow(base,  2) + Math.pow(height,  2)) /  2;
        return base + height +  2 * p;
    }
}
