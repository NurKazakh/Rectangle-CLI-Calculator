package ru.oivt.rectanglecli.service;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.model.Square;
import ru.oivt.rectanglecli.model.Circle;
import ru.oivt.rectanglecli.model.Triangle;

public class ShapeCalculator {

    public double[] calculateRectangle(double length, double width) {

        Rectangle rectangle = new Rectangle(length, width);

        return new double[] {
                rectangle.area(),
                rectangle.perimeter()
        };
    }

    public double[] calculateSquare(double side) {

        Square square = new Square(side);

        return new double[] {
                square.area(),
                square.perimeter()
        };
    }

    public double[] calculateCircle(double radius) {

        Circle circle = new Circle(radius);

        return new double[] {
                circle.area(),
                circle.circumference()
        };
    }

    public double[] calculateTriangle(double sideA, double sideB, double sideC) {

        Triangle triangle = new Triangle(sideA, sideB, sideC);

        return new double[] {
                triangle.area(),
                triangle.perimeter()
        };
    }
}