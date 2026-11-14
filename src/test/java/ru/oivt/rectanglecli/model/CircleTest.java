package ru.oivt.rectanglecli.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleTest {

    private static final double DELTA = 1e-9;

    @Test
    void shouldCalculateDiameter() {
        Circle circle = new Circle(5);

        assertEquals(10.0, circle.diameter(), DELTA);
    }

    @Test
    void shouldCalculateArea() {
        Circle circle = new Circle(5);

        assertEquals(25 * Math.PI, circle.area(), DELTA);
    }

    @Test
    void shouldCalculateCircumference() {
        Circle circle = new Circle(5);

        assertEquals(10 * Math.PI, circle.circumference(), DELTA);
    }

    @Test
    void shouldReturnRadius() {
        Circle circle = new Circle(5);

        assertEquals(5.0, circle.getRadius(), DELTA);
    }

    @Test
    void shouldThrowExceptionForZeroRadius() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(0)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeRadius() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(-5)
        );
    }

    @Test
    void shouldThrowExceptionForInfiniteRadius() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(Double.POSITIVE_INFINITY)
        );
    }

    @Test
    void shouldThrowExceptionForNaNRadius() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(Double.NaN)
        );
    }
}