package edu.ntnu.stud.model.linalg;

import edu.ntnu.stud.model.linalg.vector.Vector2D;

/**
 * Class for representing a 2x2 matrix.
 */
public class Matrix2x2 {

  private final double a00; // The element in the first row and first column.
  private final double a01; // The element in the first row and second column.
  private final double a10; // The element in the second row and first column.
  private final double a11; // The element in the second row and second column.

  /**
   * Constructor for the Matrix2x2 class.
   *
   * @param a00 The element in the first row and first column.
   * @param a01 The element in the first row and second column.
   * @param a10 The element in the second row and first column.
   * @param a11 The element in the second row and second column.
   */
  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * Multiplies the given 2D vector with this matrix by using the formula for matrix multiplication:
   * |a00 a01|   |x0|   |a00*x0 + a01*y0| |a10 a11| * |y0| = |a10*x0 + a11*y0|
   *
   * @param other The 2D vector to multiply with.
   * @return The result of the multiplication.
   */
  public Vector2D multiply(Vector2D other) {
    return new Vector2D(a00 * other.getX() + a01 * other.getY(),
        a10 * other.getX() + a11 * other.getY());
  }

  public double getA00() {
    return a00;
  }

  public double getA01() {
    return a01;
  }

  public double getA10() {
    return a10;
  }

  public double getA11() {
    return a11;
  }
}
