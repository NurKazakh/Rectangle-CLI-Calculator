package ru.oivt.rectanglecli.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TriangleTest {

    private static final double DELTA = 1e-9;

    @Test
    void shouldCalculatePerimeter() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(12.0, triangle.perimeter(), DELTA);
    }

    @Test
    void shouldCalculateSemiperimeter() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(6.0, triangle.semiperimeter(), DELTA);
    }

    @Test
    void shouldCalculateArea() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(6.0, triangle.area(), DELTA);
    }

    @Test
    void shouldCalculateHeights() {
        Triangle triangle = new Triangle(3, 4, 5);

        double[] heights = triangle.heights();

        assertEquals(4.0, heights[0], DELTA);
        assertEquals(3.0, heights[1], DELTA);
        assertEquals(2.4, heights[2], DELTA);
    }

    @Test
    void shouldCalculateInscribedCircleRadius() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(1.0, triangle.inscribedCircleRadius(), DELTA);
    }

    @Test
    void shouldCalculateCircumscribedCircleRadius() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(2.5, triangle.circumscribedCircleRadius(), DELTA);
    }

    @Test
    void shouldCalculateInscribedCircleLength() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(2 * Math.PI, triangle.inscribedCircleLength(), DELTA);
    }

    @Test
    void shouldCalculateCircumscribedCircleLength() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(5 * Math.PI, triangle.circumscribedCircleLength(), DELTA);
    }

    @Test
    void shouldThrowExceptionForZeroSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(0, 4, 5)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(3, -4, 5)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidTriangle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(1, 2, 5)
        );
    }

    @Test
    void shouldThrowExceptionForInfiniteSide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(3, 4, Double.POSITIVE_INFINITY)
        );
    }
}