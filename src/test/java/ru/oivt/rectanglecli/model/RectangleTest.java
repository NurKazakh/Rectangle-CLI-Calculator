package ru.oivt.rectanglecli.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

    private static final double DELTA = 1e-9;

    @Test
    void shouldCalculatePerimeter() {

        Rectangle rectangle = new Rectangle(10, 5);

        assertEquals(30.0, rectangle.perimeter(), DELTA);
    }

    @Test
    void shouldCalculateArea() {

        Rectangle rectangle = new Rectangle(10, 5);

        assertEquals(50.0, rectangle.area(), DELTA);
    }

    @Test
    void shouldCalculateDiagonal() {

        Rectangle rectangle = new Rectangle(3, 4);

        assertEquals(5.0, rectangle.diagonal(), DELTA);
    }

    @Test
    void shouldRecognizeSquare() {

        Rectangle rectangle = new Rectangle(5, 5);

        assertTrue(rectangle.isSquare());
    }

    @Test
    void shouldRecognizeNonSquare() {

        Rectangle rectangle = new Rectangle(10, 5);

        assertFalse(rectangle.isSquare());
    }

    @Test
    void shouldCalculateInscribedCircleRadius() {

        Rectangle rectangle = new Rectangle(6, 6);

        assertEquals(3.0, rectangle.inscribedCircleRadius(), DELTA);
    }

    @Test
    void shouldThrowExceptionForNonSquare() {

        Rectangle rectangle = new Rectangle(10, 5);

        assertThrows(
                IllegalStateException.class,
                () -> rectangle.inscribedCircleRadius()
        );
    }

    @Test
    void shouldThrowExceptionForZeroLength() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(0, 5)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeWidth() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(10, -5)
        );
    }
}