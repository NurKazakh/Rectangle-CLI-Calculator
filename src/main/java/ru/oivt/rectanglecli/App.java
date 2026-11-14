package ru.oivt.rectanglecli;

import java.io.IOException;
import java.util.Scanner;

import ru.oivt.rectanglecli.ui.ConsoleUI;

public class App {

    public static void main(String[] args) throws IOException {
    	new ProcessBuilder("cmd", "/c", "title", "Geometric CLI Calculator")
        .inheritIO()
        .start();

        Scanner scanner = new Scanner(System.in);

        ConsoleUI consoleUI = new ConsoleUI(scanner);

        consoleUI.run();
    }
}