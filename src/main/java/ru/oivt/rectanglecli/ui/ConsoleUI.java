package ru.oivt.rectanglecli.ui;

import java.util.Scanner;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.util.InputUtils;

public class ConsoleUI {

    private final Scanner scanner;
    
    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {

        printHeader();

        Rectangle rectangle = readRectangle();

        printRectangleInfo(rectangle);

        System.out.println("\nМеню операций.");
    }

    private void printHeader() {

        System.out.println("========================================");
        System.out.println("       Rectangle CLI Calculator");
        System.out.println("========================================");
        System.out.println();
    }

    private Rectangle readRectangle() {

        double a = InputUtils.readPositiveDouble(
                scanner,
                "Введите длину прямоугольника a: "
        );

        double b = InputUtils.readPositiveDouble(
                scanner,
                "Введите ширину прямоугольника b: "
        );

        return new Rectangle(a, b);
    }

    private void printRectangleInfo(Rectangle rectangle) {

        System.out.println("\n----------------------------------------");
        System.out.printf(
                "Параметры прямоугольника: a = %.2f, b = %.2f%n",
                rectangle.getA(),
                rectangle.getB()
        );
        System.out.println("----------------------------------------");
    }
}