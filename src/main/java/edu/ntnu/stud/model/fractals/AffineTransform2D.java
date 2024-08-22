package edu.ntnu.stud.model.fractals;

import edu.ntnu.stud.model.interfaces.Transform2D;
import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;

/**
 * Class for representing a 2D affine transformation. The transformation is defined by a 2x2 matrix
 * and a 2D vector. The transformation is defined as: f(v) = M * v + t where v is the input 2D
 * vector, M is the 2x2 matrix, and t is the 2D vector.
 */
public class AffineTransform2D implements Transform2D {

  private final Matrix2x2 matrix; // The 2x2 matrix defining the transformation.
  private final Vector2D vector; // The 2D vector for updating the transformation.

  /**
   * Constructor for the AffineTransform2D class.
   *
   * @param matrix The 2x2 matrix defining the transformation.
   * @param vector The 2D vector for updating the transformation.
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    this.matrix = matrix;
    this.vector = vector;
  }

  /**
   * Transforms the given 2D vector using the affine transformation by multiplying the matrix with
   * the parameter vector and adding the vector field.
   *
   * @param vector The 2D vector to transform.
   * @return The result of the transformation.
   */
  public Vector2D transform(Vector2D vector) {
    return matrix.multiply(vector).add(this.vector);
  }

  public Matrix2x2 getMatrix() {
    return matrix;
  }

  public Vector2D getVector() {
    return vector;
  }
}
