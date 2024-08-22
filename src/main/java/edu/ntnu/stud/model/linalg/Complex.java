package edu.ntnu.stud.model.linalg;

import edu.ntnu.stud.model.linalg.vector.Vector2D;

/**
 * Class for representing complex numbers and performing basic operations on them. The class extends
 * the Vector2D class, and uses the x and y values to represent the real and imaginary parts of the
 * complex number.
 *
 * @see Vector2D
 */
public class Complex extends Vector2D {

  /**
   * Constructor for the Complex class. Calls the constructor of the Vector2D class with the given x
   * and y values.
   *
   * @param x0 The real part of the complex number.
   * @param y0 The imaginary part of the complex number.
   */
  public Complex(double x0, double y0) {
    super(x0, y0);
  }

  /**
   * Adds the given complex number to this complex number by adding the x and y values.
   *
   * @param other The complex number to add.
   * @return The result of the addition.
   */
  public Complex add(Complex other) {
    return new Complex(getX() + other.getX(), getY() + other.getY());
  }

  /**
   * Subtracts the given complex number from this complex number by subtracting the x and y values.
   *
   * @param other The complex number to subtract.
   * @return The result of the subtraction.
   */
  public Complex subtract(Complex other) {
    return new Complex(getX() - other.getX(), getY() - other.getY());
  }

  /**
   * Multiplies the given complex number with this complex number by using the formula for
   * multiplication of complex numbers: (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
   *
   * @param other The complex number to multiply with.
   * @return The result of the multiplication.
   */
  public Complex multiply(Complex other) {
    return new Complex(getX() * other.getX() - getY() * other.getY(),
        getX() * other.getY() + getY() * other.getX());
  }

  /**
   * Calculates the square root of this complex number by using the formula for the square root of a
   * complex number: sqrt(a + bi) = sqrt(0.5 * (sqrt(a^2 + b^2) + a)) + sqrt(0.5 * (sqrt(a^2 + b^2)
   * - a))i
   *
   * @return The square root of the complex number.
   */
  public Complex sqrt() {
    double real = Math.sqrt(
        0.5 * (Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)) + getX()));
    double imaginary = Math.sqrt(
        0.5 * (Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)) - getX()));
    return new Complex(real, imaginary);
  }

  /**
   * Multiplies this complex number with a scalar value by multiplying the x and y values with the
   * scalar.
   *
   * @param scalar The scalar value to multiply with.
   * @return The result of the multiplication.
   */
  public Complex scalarMultiplication(double scalar) {
    return new Complex(getX() * scalar, getY() * scalar);
  }
}
