package ru.oivt.rectanglecli.service;

import java.util.ArrayList;
import java.util.List;

public class CalculationHistory {

    private final List<String> history;

    public CalculationHistory() {
        history = new ArrayList<>();
    }

    public void add(String shape, double area, double perimeter) {

        history.add(String.format(
                "%s | Площадь: %.4f | Периметр: %.4f",
                shape,
                area,
                perimeter
        ));
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public String getFormattedHistory() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < history.size(); i++) {

            result.append(i + 1)
                  .append(". ")
                  .append(history.get(i))
                  .append("\n\n");
        }

        return result.toString();
    }

    public void clear() {
        history.clear();
    }
}