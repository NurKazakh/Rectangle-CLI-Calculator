package ru.oivt.rectanglecli.model;

public class Square {
	private final double side;

	public Square(double side) {
	    if (!Double.isFinite(side) || side <= 0) {
	        throw new IllegalArgumentException(
	                "Сторона квадрата должна быть конечным числом больше 0."
	        );
	    }
	    this.side = side;
	}

	public double getSide() {
	    return side;
	}

	public double perimeter() {
	    return 4 * side;
	}

	public double area() {
	    return side * side;
	}

	public double diagonal() {
	    return side * Math.sqrt(2);
	}

	public double circumscribedCircleRadius() {
	    return diagonal() / 2;
	}
	
	public double circumscribedCircleLength() {
	    return 2 * Math.PI * circumscribedCircleRadius();
	}
	
	public double inscribedCircleRadius() {
		return side / 2;
	}
}
