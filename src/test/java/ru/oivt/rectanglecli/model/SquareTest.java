package ru.oivt.rectanglecli.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {

    private static final double DELTA = 1e-9;

    @Test
    void shouldCalculatePerimeter() {
        Square square = new Square(5);

        assertEquals(20.0, square.perimeter(), DELTA);
    }

    @Test
    void shouldCalculateArea() {
        Square square = new Square(5);

        assertEquals(25.0, square.area(), DELTA);
    }

    @Test
    void shouldCalculateDiagonal() {
        Square square = new Square(5);

        assertEquals(5 * Math.sqrt(2), square.diagonal(), DELTA);
    }

    @Test
    void shouldCalculateCircumscribedCircleRadius() {
        Square square = new Square(4);

        assertEquals(2 * Math.sqrt(2), square.circumscribedCircleRadius(), DELTA);
    }

    @Test
    void shouldCalculateCircumscribedCircleLength() {
        Square square = new Square(4);

        assertEquals(4 * Math.PI * Math.sqrt(2),
                square.circumscribedCircleLength(), DELTA);
    }

    @Test
    void shouldCalculateInscribedCircleRadius() {
        Square square = new Square(6);

        assertEquals(3.0, square.inscribedCircleRadius(), DELTA);
    }

    @Test
    void shouldThrowExceptionForZeroSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Square(0)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Square(-5)
        );
    }

    @Test
    void shouldThrowExceptionForInfiniteSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Square(Double.POSITIVE_INFINITY)
        );
    }
}