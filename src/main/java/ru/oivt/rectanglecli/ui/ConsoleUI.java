package ru.oivt.rectanglecli.ui;

import java.util.Locale;
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

        while (true) {

            printMenu(rectangle);

            int choice = InputUtils.readInt(scanner, "Ваш выбор: ");

            switch (choice) {

	            case 1:
	                showPerimeter(rectangle);
	                break;
	
	            case 2:
	                showArea(rectangle);
	                break;
	
	            case 3:
	                showDiagonal(rectangle);
	                break;
	
	            case 4:
	                showCircumscribedCircleLength(rectangle);
	                break;
	
	            case 5:
	                showInscribedCircleRadius(rectangle);
	                break;
	
	            case 6:
	                System.out.println("\nВвод новых параметров прямоугольника.\n");
	                rectangle = readRectangle();
	                break;
	
	            case 0:
	                System.out.println("\nРабота программы завершена.");
	                return;

                default:
                    System.out.println(
                            "\nОшибка: неизвестный пункт меню (" + choice + ")."
                    );
                    System.out.println("Повторите ввод.\n");
            }
        }
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

    private void printMenu(Rectangle rectangle) {

        System.out.println("----------------------------------------");
        System.out.printf(
                Locale.US,
                "Параметры прямоугольника: a = %.2f, b = %.2f%n",
                rectangle.getA(),
                rectangle.getB()
        );

        System.out.println("----------------------------------------");
        System.out.println("Выберите действие:");
        System.out.println("1) Периметр P = 2(a + b)");
        System.out.println("2) Площадь S = a * b");
        System.out.println("3) Диагональ d = sqrt(a^2 + b^2)");
        System.out.println("4) Длина описанной окружности C = π * d");
        System.out.println("5) Радиус вписанной окружности (только квадрат)");
        System.out.println("6) Ввести новые параметры");
        System.out.println("0) Выход");
        System.out.println("----------------------------------------");
    }

    private void showPerimeter(Rectangle rectangle) {

        double result = rectangle.perimeter();

        System.out.println("\nПериметр:");
        System.out.printf(
                Locale.US,
                "P = 2(a + b) = 2(%.2f + %.2f) = %.2f%n%n",
                rectangle.getA(),
                rectangle.getB(),
                result
        );
    }

    private void showArea(Rectangle rectangle) {

        double result = rectangle.area();

        System.out.println("\nПлощадь:");

        System.out.printf(
                Locale.US,
                "S = a * b = %.2f * %.2f = %.2f%n%n",
                rectangle.getA(),
                rectangle.getB(),
                result
        );
    }
    
    private void showDiagonal(Rectangle rectangle) {

        double result = rectangle.diagonal();

        System.out.println("\nДиагональ:");

        System.out.printf(
                Locale.US,
                "d = sqrt(a^2 + b^2) = sqrt(%.2f^2 + %.2f^2) = %.2f%n%n",
                rectangle.getA(),
                rectangle.getB(),
                result
        );
    }
    
    private void showCircumscribedCircleLength(Rectangle rectangle) {

        double diagonal = rectangle.diagonal();
        double result = Math.PI * diagonal;

        System.out.println("\nДлина описанной окружности:");

        System.out.printf(
                Locale.US,
                "C = π * d = π * %.2f = %.2f%n%n",
                diagonal,
                result
        );
    }
    
    private void showInscribedCircleRadius(Rectangle rectangle) {

        System.out.println("\nРадиус вписанной окружности:");

        if (!rectangle.isSquare()) {
            System.out.println("Вписанная окружность существует только для квадрата.\n");
            return;
        }

        double result = rectangle.inscribedCircleRadius();

        System.out.printf(
                Locale.US,
                "r = a / 2 = %.2f / 2 = %.2f%n%n",
                rectangle.getA(),
                result
        );
    }
}