package ru.oivt.rectanglecli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculationHistory {

    private final List<String> history;

    public CalculationHistory() {
        history = new ArrayList<>();
    }

    public void add(CalculationResult calculationResult) {

        StringBuilder entry = new StringBuilder();

        entry.append(calculationResult.getShapeName()).append("\n");

        for (Map.Entry<String, Object> item :
                calculationResult.getValues().entrySet()) {

            entry.append(item.getKey())
                 .append(": ");

            Object value = item.getValue();

            if (value instanceof Number) {
                entry.append(String.format(
                        "%.4f",
                        ((Number) value).doubleValue()
                ));
            } else {
                entry.append(value);
            }

            entry.append("\n");
        }

        history.add(entry.toString());
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
                  .append("\n");
        }

        return result.toString();
    }

    public void clear() {
        history.clear();
    }
}