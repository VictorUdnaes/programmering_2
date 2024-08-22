package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.AffineTransform2D;
import org.junit.jupiter.api.Test;

/**
 * Test class for the AffineTransformation class.
 */
class AffineTransformationTest {

  /**
   * Test the transform method of the AffineTransform class. Creates a new AffineTransform2D object
   * with a matrix and a vector. Compares the x and y values of the result of the transformation
   * with the expected values.
   */
  @Test
  void test() {
    Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
    Vector2D vector = new Vector2D(5, 6);
    AffineTransform2D transform = new AffineTransform2D(matrix, vector);
    Vector2D v = new Vector2D(1, 2);
    Vector2D result = transform.transform(v);
    assertEquals(10, result.getX(), 0.0001);
    assertEquals(17, result.getY(), 0.0001);
  }
}
