package ru.oivt.rectanglecli.model;

public class Triangle {

    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {

        if (!Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)
                || a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException(
                    "Стороны треугольника должны быть конечными числами больше 0."
            );
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                    "Треугольник с такими сторонами не существует."
            );
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double perimeter() {
        return a + b + c;
    }

    public double semiperimeter() {
        return perimeter() / 2;
    }

    public double area() {
        double p = semiperimeter();

        return Math.sqrt(
                p * (p - a) * (p - b) * (p - c)
        );
    }

    public double[] heights() {
    	double heightA = 2 * area() / a;
	    double heightB = 2 * area() / b;
	    double heightC = 2 * area() / c;
	
	    return new double[] {heightA, heightB, heightC};
    }

    public double inscribedCircleRadius() {
        return area() / semiperimeter();
    }

    public double circumscribedCircleRadius() {
    	return (a * b * c) / (4 * area());
    }

    public double inscribedCircleLength() {
        return 2 * Math.PI * inscribedCircleRadius();
    }

    public double circumscribedCircleLength() {
        return 2 * Math.PI * circumscribedCircleRadius();
    }
}