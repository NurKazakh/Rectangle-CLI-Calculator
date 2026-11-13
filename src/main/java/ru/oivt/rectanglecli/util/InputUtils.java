package ru.oivt.rectanglecli.util;

import java.util.Scanner;

public final class InputUtils {
	
    private InputUtils() {}

    public static double readPositiveDouble(Scanner sc, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                double value = Double.parseDouble(input.replace(',', '.'));

                if (!Double.isFinite(value)) {
                    System.out.println(
                            "Ошибка: необходимо ввести конечное число. Повторите ввод."
                    );
                    continue;
                }

                if (value <= 0) {
                    System.out.println(
                            "Ошибка: значение должно быть больше 0. Повторите ввод."
                    );
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: нужно ввести число (например 10 или 3.5). Повторите ввод."
                );
            }
        }
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
        	
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
                
            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: необходимо ввести целое число. Повторите ввод."
                );
            }
        }
    }

    public static void pause(Scanner sc) {

        System.out.println("\nНажмите Enter, чтобы продолжить...");

        sc.nextLine();
    }
}