package ru.oivt.rectanglecli.service;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.model.Square;
import ru.oivt.rectanglecli.model.Circle;
import ru.oivt.rectanglecli.model.Triangle;

public class ShapeCalculator {

    public CalculationResult calculateRectangle(double length, double width) {

        Rectangle rectangle = new Rectangle(length, width);

        CalculationResult result = new CalculationResult("Прямоугольник");

        result.add("Площадь", rectangle.area());
        result.add("Периметр", rectangle.perimeter());
        result.add("Диагональ", rectangle.diagonal());
        result.add("Является квадратом", rectangle.isSquare() ? "Да" : "Нет");

        if (rectangle.isSquare()) {
            result.add(
                    "Радиус вписанной окружности",
                    rectangle.inscribedCircleRadius()
            );
        }

        return result;
    }

    public CalculationResult calculateSquare(double side) {

        Square square = new Square(side);

        CalculationResult result = new CalculationResult("Квадрат");

        result.add("Площадь", square.area());
        result.add("Периметр", square.perimeter());
        result.add("Диагональ", square.diagonal());
        result.add(
                "Радиус описанной окружности",
                square.circumscribedCircleRadius()
        );
        result.add(
                "Длина описанной окружности",
                square.circumscribedCircleLength()
        );
        result.add(
                "Радиус вписанной окружности",
                square.inscribedCircleRadius()
        );

        return result;
    }

    public CalculationResult calculateCircle(double radius) {

        Circle circle = new Circle(radius);

        CalculationResult result = new CalculationResult("Круг");

        result.add("Радиус", circle.getRadius());
        result.add("Диаметр", circle.diameter());
        result.add("Площадь", circle.area());
        result.add("Длина окружности", circle.circumference());

        return result;
    }

    public CalculationResult calculateTriangle(
            double sideA, double sideB, double sideC
    ) {

        Triangle triangle = new Triangle(sideA, sideB, sideC);

        CalculationResult result = new CalculationResult("Треугольник");

        result.add("Периметр", triangle.perimeter());
        result.add("Полупериметр", triangle.semiperimeter());
        result.add("Площадь", triangle.area());

        double[] heights = triangle.heights();

        result.add("Высота к стороне A", heights[0]);
        result.add("Высота к стороне B", heights[1]);
        result.add("Высота к стороне C", heights[2]);

        result.add(
                "Радиус вписанной окружности",
                triangle.inscribedCircleRadius()
        );
        result.add(
                "Радиус описанной окружности",
                triangle.circumscribedCircleRadius()
        );
        result.add(
                "Длина вписанной окружности",
                triangle.inscribedCircleLength()
        );
        result.add(
                "Длина описанной окружности",
                triangle.circumscribedCircleLength()
        );

        return result;
    }
}