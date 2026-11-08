package ru.oivt.rectanglecli;

import java.util.Scanner;

import ru.oivt.rectanglecli.ui.ConsoleUI;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ConsoleUI consoleUI = new ConsoleUI(scanner);

        consoleUI.run();
    }
}