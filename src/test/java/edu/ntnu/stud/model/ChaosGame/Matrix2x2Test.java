package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Matrix2x2 class.
 */
class Matrix2x2Test {

  /**
   * Test the multiply method of the Matrix2x2 class. Multiplies the given 2D vector with this
   * matrix. Compares the x and y values of the result of the multiplication with the expected
   * values.
   */
  @Test
  void multiplyByValidVectorTest() {
    Matrix2x2 m = new Matrix2x2(1, 2, 3, 4);
    Vector2D v = new Vector2D(1, 2);
    Vector2D result = m.multiply(v);
    assertEquals(5, result.getX(), 0.0001);
  }
}
