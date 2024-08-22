package edu.ntnu.stud.model.fractals;

import edu.ntnu.stud.model.interfaces.Transform2D;
import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.vector.Vector2D;

/**
 * Class for transforming a 2D vector using a Julia transformation. The transformation is defined by
 * a complex number and a sign. The transformation is defined as: f(z) = sqrt(sign * (z - c)) where
 * z is the input complex number, c is the complex number defining the transformation, and sign is
 * the sign of the transformation.
 */
public class JuliaTransform implements Transform2D {

  private final Complex point; // The complex number to transform with.
  private final int sign; // The sign of the transformation, can be 1 or -1.

  /**
   * Constructor for the JuliaTransform class.
   *
   * @param point The complex number to transform with.
   * @param sign  The sign of the transformation, can be 1 or -1.
   */
  public JuliaTransform(Complex point, int sign) {
    this.point = point;
    this.sign = sign;
  }

  /**
   * Transforms the given 2D vector using the Julia transformation by making a complex number from
   * the vector and calling the complexTransformation method. The result of the transformation is
   * then converted back to a 2D vector.
   *
   * @param vector The 2D vector to transform.
   * @return The result of the transformation.
   */
  public Vector2D transform(Vector2D vector) {
    Complex transformed = complexTransformation(
        new Complex(vector.getX(), vector.getY()));
    return new Vector2D(transformed.getX(), transformed.getY());
  }

  /**
   * Transforms the given complex number using the Julia transformation: f(z) = sqrt(sign * (z -
   * c))
   *
   * @param complex The complex number to transform.
   * @return The result of the transformation.
   */
  private Complex complexTransformation(Complex complex) {
    return this.point.subtract(complex).sqrt().scalarMultiplication(this.sign);
  }

  public Complex getPoint() {
    return point;
  }
}

