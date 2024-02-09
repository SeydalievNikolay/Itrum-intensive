package org.seydaliev;

import org.seydaliev.com.geometrylibrary.Rectangle;
import org.seydaliev.com.geometrylibrary.Triangle;
import org.seydaliev.com.geometrylibrary.Circle;
import org.seydaliev.com.geometrylibrary.*;
import org.seydaliev.threedimensionalshapes.Cube;
import org.seydaliev.threedimensionalshapes.Sphere;
import org.seydaliev.unitsconverter.UnitsConverter;

public class GeometryApp {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4,  6);
        Triangle triangle = new Triangle(3,  4,  5); // Предполагаем, что мы добавили третий аргумент для длины стороны

        System.out.println("Круг:");
        System.out.println("Площадь: " + circle.calculateArea());
        System.out.println("Периметр: " + circle.calculatePerimeter());

        System.out.println("\nПрямоугольник:");
        System.out.println("Площадь: " + rectangle.calculateArea());
        System.out.println("Периметр: " + rectangle.calculatePerimeter());

        System.out.println("\nТреугольник:");
        System.out.println("Площадь: " + triangle.calculateArea());
        System.out.println("Периметр: " + triangle.calculatePerimeter());

        double lengthInMeters =  5;
        double lengthInCentimeters = UnitsConverter.convertMetersToCentimeters(lengthInMeters);

        System.out.println(lengthInMeters + " метров равно " + lengthInCentimeters + " сантиметров.");

        Cube cube = new Cube(5);
        System.out.println("Площадь поверхности куба: " + cube.calculateSurfaceArea());
        System.out.println("Объем куба: " + cube.calculateVolume());

        Sphere sphere = new Sphere(3);
        System.out.println("Площадь поверхности сферы: " + sphere.calculateSurfaceArea());
        System.out.println("Объем сферы: " + sphere.calculateVolume());
    }
}