package ru.oivt.rectanglecli.ui;

import java.util.Scanner;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.model.Square;
import ru.oivt.rectanglecli.model.Triangle;
import ru.oivt.rectanglecli.model.Circle;
import ru.oivt.rectanglecli.util.InputUtils;

public class ConsoleUI {

    private final Scanner scanner;
    private final List<String> history = new ArrayList<>();

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {

        printHeader();

        while (true) {

            printMainMenu();

            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {

                case "1":
                    rectangleMenu();
                    break;

                case "2":
                    squareMenu();
                    break;

                case "3":
                    triangleMenu();
                    break;

                case "4":
                    circleMenu();
                    break;

                case "history":
                    showHistory();
                    InputUtils.pause(scanner);
                    break;

                case "help":
                    showHelp();
                    InputUtils.pause(scanner);
                    break;

                case "about":
                    showAbout();
                    InputUtils.pause(scanner);
                    break;

                case "clear":
                    clearConsole();
                    printHeader();
                    break;

                case "0":
                    System.out.println("\nРабота программы завершена.");
                    return;

                default:
                    System.out.println("\nОшибка: неизвестная команда.");
                    System.out.println("Введите help для просмотра доступных команд.\n");
            }
        }
    }

    private void printHeader() {

        System.out.println("========================================");
        System.out.println("       Geometric CLI Calculator");
        System.out.println("========================================");
        System.out.println();
    }

    private void printMainMenu() {

        System.out.println("Выберите геометрическую фигуру:\n");

        System.out.println("1. Прямоугольник");
        System.out.println("2. Квадрат");
        System.out.println("3. Треугольник");
        System.out.println("4. Круг");

        System.out.println("\nДополнительные команды:");

        System.out.println("history — история вычислений");
        System.out.println("help    — справка");
        System.out.println("about   — информация о программе");
        System.out.println("clear   — очистить консоль");

        System.out.println("\n0. Выход");
        System.out.println();
    }

    private void rectangleMenu() {

        System.out.println("\n========== ПРЯМОУГОЛЬНИК ==========\n");

        double a = InputUtils.readPositiveDouble(
                scanner, "Введите длину прямоугольника a: ");

        double b = InputUtils.readPositiveDouble(
                scanner, "Введите ширину прямоугольника b: ");

        Rectangle rectangle = new Rectangle(a, b);

        while (true) {

            System.out.printf(Locale.US,
                    "\nПараметры: a = %.2f, b = %.2f%n",
                    rectangle.getA(), rectangle.getB());

            System.out.println("\nВыберите вычисление:");
            System.out.println("1. Периметр");
            System.out.println("2. Площадь");
            System.out.println("3. Диагональ");
            System.out.println("4. Длина описанной окружности");
            System.out.println("5. Радиус вписанной окружности");
            System.out.println("6. Ввести новые размеры");
            System.out.println("0. Вернуться в главное меню");

            int choice = InputUtils.readInt(scanner, "\nВаш выбор: ");

            switch (choice) {

                case 1: {
                    double result = rectangle.perimeter();

                    System.out.printf(Locale.US,
                            "\nПериметр: P = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Прямоугольник: периметр = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 2: {
                    double result = rectangle.area();

                    System.out.printf(Locale.US,
                            "\nПлощадь: S = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Прямоугольник: площадь = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 3: {
                    double result = rectangle.diagonal();

                    System.out.printf(Locale.US,
                            "\nДиагональ: d = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Прямоугольник: диагональ = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 4: {
                    double result = Math.PI * rectangle.diagonal();

                    System.out.printf(Locale.US,
                            "\nДлина описанной окружности: C = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Прямоугольник: длина описанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 5: {

                    if (rectangle.isSquare()) {

                        double result = rectangle.inscribedCircleRadius();

                        System.out.printf(Locale.US,
                                "\nРадиус вписанной окружности: r = %.2f%n",
                                result);

                        history.add(String.format(Locale.US,
                                "Прямоугольник: радиус вписанной окружности = %.2f",
                                result));

                    } else {
                        System.out.println(
                                "\nОшибка: вписанная окружность существует только для квадрата."
                        );
                    }
                    InputUtils.pause(scanner);
                    break;
                }

                case 6: {

                    System.out.println("\nВвод новых размеров.");

                    a = InputUtils.readPositiveDouble(
                            scanner, "Введите длину a: ");

                    b = InputUtils.readPositiveDouble(
                            scanner, "Введите ширину b: ");

                    rectangle = new Rectangle(a, b);
                    break;
                }

                case 0:
                    System.out.println("\nВозвращение в главное меню.\n");
                    return;

                default:
                    System.out.println("\nОшибка: неизвестный пункт меню.");
            }
        }
    }

    private void squareMenu() {

        System.out.println("\n========== КВАДРАТ ==========\n");

        double side = InputUtils.readPositiveDouble(
                scanner, "Введите сторону квадрата: ");

        Square square = new Square(side);

        while (true) {

            System.out.printf(Locale.US,
                    "\nСторона квадрата: a = %.2f%n",
                    square.getSide());

            System.out.println("\nВыберите вычисление:");
            System.out.println("1. Периметр");
            System.out.println("2. Площадь");
            System.out.println("3. Диагональ");
            System.out.println("4. Радиус описанной окружности");
            System.out.println("5. Длина описанной окружности");
            System.out.println("6. Радиус вписанной окружности");
            System.out.println("0. Вернуться в главное меню");

            int choice = InputUtils.readInt(scanner, "\nВаш выбор: ");

            switch (choice) {

                case 1: {
                    double result = square.perimeter();

                    System.out.printf(Locale.US,
                            "\nПериметр: P = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Квадрат: периметр = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 2: {
                    double result = square.area();

                    System.out.printf(Locale.US,
                            "\nПлощадь: S = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Квадрат: площадь = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 3: {
                    double result = square.diagonal();

                    System.out.printf(Locale.US,
                            "\nДиагональ: d = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Квадрат: диагональ = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 4: {
                    double result = square.circumscribedCircleRadius();

                    System.out.printf(Locale.US,
                            "\nРадиус описанной окружности: R = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Квадрат: радиус описанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 5: {
                    double result = square.circumscribedCircleLength();

                    System.out.printf(Locale.US,
                            "\nДлина описанной окружности: C = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Квадрат: длина описанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 6: {
                    double result = square.inscribedCircleRadius();

                    System.out.printf(Locale.US,
                            "\nРадиус вписанной окружности: r = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Квадрат: радиус вписанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 0:
                    System.out.println("\nВозвращение в главное меню.\n");
                    return;

                default:
                    System.out.println("\nОшибка: неизвестный пункт меню.");
            }
        }
    }

    private void triangleMenu() {

        System.out.println("\n========== ТРЕУГОЛЬНИК ==========\n");

        Triangle triangle;

        while (true) {

            double a = InputUtils.readPositiveDouble(
                    scanner, "Введите сторону треугольника a: ");

            double b = InputUtils.readPositiveDouble(
                    scanner, "Введите сторону треугольника b: ");

            double c = InputUtils.readPositiveDouble(
                    scanner, "Введите сторону треугольника c: ");

            try {
                triangle = new Triangle(a, b, c);
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("\nОшибка: " + e.getMessage());
                System.out.println("Повторите ввод сторон треугольника.\n");
            }
        }

        while (true) {

            System.out.printf(Locale.US,
                    "\nСтороны треугольника: a = %.2f, b = %.2f, c = %.2f%n",
                    triangle.getA(), triangle.getB(), triangle.getC());

            System.out.println("\nВыберите вычисление:");
            System.out.println("1. Периметр");
            System.out.println("2. Полупериметр");
            System.out.println("3. Площадь");
            System.out.println("4. Высоты треугольника");
            System.out.println("5. Радиус вписанной окружности");
            System.out.println("6. Радиус описанной окружности");
            System.out.println("7. Длина вписанной окружности");
            System.out.println("8. Длина описанной окружности");
            System.out.println("9. Ввести новые стороны");
            System.out.println("0. Вернуться в главное меню");

            int choice = InputUtils.readInt(scanner, "\nВаш выбор: ");

            switch (choice) {

                case 1: {
                    double result = triangle.perimeter();

                    System.out.printf(Locale.US,
                            "\nПериметр: P = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Треугольник: периметр = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 2: {
                    double result = triangle.semiperimeter();

                    System.out.printf(Locale.US,
                            "\nПолупериметр: p = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Треугольник: полупериметр = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 3: {
                    double result = triangle.area();

                    System.out.printf(Locale.US,
                            "\nПлощадь: S = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Треугольник: площадь = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 4: {
                    double[] heights = triangle.heights();

                    System.out.printf(Locale.US,
                            "\nВысота к стороне a: h(a) = %.2f%n",
                            heights[0]);

                    System.out.printf(Locale.US,
                            "Высота к стороне b: h(b) = %.2f%n",
                            heights[1]);

                    System.out.printf(Locale.US,
                            "Высота к стороне c: h(c) = %.2f%n",
                            heights[2]);

                    history.add(String.format(Locale.US,
                            "Треугольник: высоты h(a) = %.2f, h(b) = %.2f, h(c) = %.2f",
                            heights[0], heights[1], heights[2]));
                    InputUtils.pause(scanner);
                    break;
                }

                case 5: {
                    double result = triangle.inscribedCircleRadius();

                    System.out.printf(Locale.US,
                            "\nРадиус вписанной окружности: r = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Треугольник: радиус вписанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 6: {
                    double result = triangle.circumscribedCircleRadius();

                    System.out.printf(Locale.US,
                            "\nРадиус описанной окружности: R = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Треугольник: радиус описанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 7: {
                    double result = triangle.inscribedCircleLength();

                    System.out.printf(Locale.US,
                            "\nДлина вписанной окружности: C = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Треугольник: длина вписанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 8: {
                    double result = triangle.circumscribedCircleLength();

                    System.out.printf(Locale.US,
                            "\nДлина описанной окружности: C = %.2f%n",
                            result);

                    history.add(String.format(Locale.US,
                            "Треугольник: длина описанной окружности = %.2f",
                            result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 9: {

                    System.out.println("\nВвод новых сторон.");

                    while (true) {

                        double a = InputUtils.readPositiveDouble(
                                scanner, "Введите сторону a: ");

                        double b = InputUtils.readPositiveDouble(
                                scanner, "Введите сторону b: ");

                        double c = InputUtils.readPositiveDouble(
                                scanner, "Введите сторону c: ");

                        try {
                            triangle = new Triangle(a, b, c);
                            break;

                        } catch (IllegalArgumentException e) {
                            System.out.println("\nОшибка: " + e.getMessage());
                            System.out.println("Повторите ввод сторон.\n");
                        }
                    }
                    break;
                }

                case 0:
                    System.out.println("\nВозвращение в главное меню.\n");
                    return;

                default:
                    System.out.println("\nОшибка: неизвестный пункт меню.");
            }
        }
    }

    private void circleMenu() {

        System.out.println("\n========== КРУГ ==========\n");

        double radius = InputUtils.readPositiveDouble(
                scanner, "Введите радиус круга: ");

        Circle circle = new Circle(radius);

        while (true) {

            System.out.printf(Locale.US,
                    "\nРадиус круга: r = %.2f%n",
                    circle.getRadius());

            System.out.println("\nВыберите вычисление:");
            System.out.println("1. Диаметр");
            System.out.println("2. Площадь");
            System.out.println("3. Длина окружности");
            System.out.println("4. Ввести новый радиус");
            System.out.println("0. Вернуться в главное меню");

            int choice = InputUtils.readInt(scanner, "\nВаш выбор: ");

            switch (choice) {

                case 1: {
                    double result = circle.diameter();

                    System.out.printf(Locale.US,
                            "\nДиаметр: d = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Круг: диаметр = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 2: {
                    double result = circle.area();

                    System.out.printf(Locale.US,
                            "\nПлощадь: S = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Круг: площадь = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 3: {
                    double result = circle.circumference();

                    System.out.printf(Locale.US,
                            "\nДлина окружности: C = %.2f%n", result);

                    history.add(String.format(Locale.US,
                            "Круг: длина окружности = %.2f", result));
                    InputUtils.pause(scanner);
                    break;
                }

                case 4: {
                    radius = InputUtils.readPositiveDouble(
                            scanner, "\nВведите новый радиус: ");

                    circle = new Circle(radius);
                    break;
                }

                case 0:
                    System.out.println("\nВозвращение в главное меню.\n");
                    return;

                default:
                    System.out.println("\nОшибка: неизвестный пункт меню.");
            }
        }
    }

    private void showHistory() {

        System.out.println("\n========== ИСТОРИЯ ВЫЧИСЛЕНИЙ ==========");

        if (history.isEmpty()) {
            System.out.println("История пока пуста.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }

        System.out.println("========================================\n");
    }

    private void showHelp() {

        System.out.println("\n========== СПРАВКА ==========");

        System.out.println("1 — выбрать прямоугольник");
        System.out.println("2 — выбрать квадрат");
        System.out.println("3 — выбрать треугольник");
        System.out.println("4 — выбрать круг");

        System.out.println("history — показать историю вычислений");
        System.out.println("help — показать справку");
        System.out.println("about — информация о программе");
        System.out.println("clear — очистить консоль");
        System.out.println("0 — завершить программу");

        System.out.println("=============================\n");
    }

    private void showAbout() {

        System.out.println("\n========================================");
        System.out.println("       Geometric CLI Calculator");
        System.out.println("========================================");

        System.out.println("Консольный калькулятор геометрических фигур.");
        System.out.println("Программа позволяет выполнять геометрические вычисления.");
        System.out.println("\nПоддерживаемые фигуры:");
        System.out.println("Прямоугольник, квадрат, треугольник и круг.");

        System.out.println("========================================\n");
    }

    private void clearConsole() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}