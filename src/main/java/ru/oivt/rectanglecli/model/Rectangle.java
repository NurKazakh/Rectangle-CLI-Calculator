package ru.oivt.rectanglecli.model;

public class Rectangle {

   private static final double EPS = 1e-9;

   private final double a;
   private final double b;

   public Rectangle(double a, double b) {

        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0) {
            throw new IllegalArgumentException(
            		"Длина и ширина должны быть конечными числами больше 0."
            );
        }
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double perimeter() {
        return 2 * (a + b);
    }

    public double area() {
        return a * b;
    }

    public double diagonal() {
        return Math.hypot(a, b);
    }

    public boolean isSquare() {
        return Math.abs(a - b) <= EPS * Math.max(a, b);
    }

    public double inscribedCircleRadius() {
        if (!isSquare()) {
            throw new IllegalStateException(
                    "Вписанная окружность существует только для квадрата."
            );
        }
        return a / 2;
    }
}
