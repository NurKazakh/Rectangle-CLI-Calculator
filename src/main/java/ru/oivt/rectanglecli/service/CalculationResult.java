package ru.oivt.rectanglecli.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculationResult {

    private final String shapeName;
    private final Map<String, Object> values;

    public CalculationResult(String shapeName) {
        this.shapeName = shapeName;
        this.values = new LinkedHashMap<>();
    }

    public String getShapeName() {
        return shapeName;
    }

    public void add(String name, Object value) {
        values.put(name, value);
    }

    public Map<String, Object> getValues() {
        return Collections.unmodifiableMap(values);
    }
}