package ru.oivt.rectanglecli.model;

public class Circle {

    private final double radius;

    public Circle(double radius) {
        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException(
                    "Радиус круга должен быть конечным числом больше 0."
            );
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double diameter() {
        return 2 * radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }
}