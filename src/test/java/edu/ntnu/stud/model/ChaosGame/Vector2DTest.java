package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.linalg.vector.Vector2D;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Vector2D class.
 */
class Vector2DTest {

  /**
   * Test the add method of the Vector2D class. Compares the x and y values of the result of the
   * addition with the expected values.
   */
  @Test
  void testAdd() {
    Vector2D v1 = new Vector2D(1, 2);
    Vector2D v2 = new Vector2D(3, 4);
    Vector2D v3 = v1.add(v2);
    assertEquals(4, v3.getX(), 0.0001);
    assertEquals(6, v3.getY(), 0.0001);
  }

  /**
   * Test the subtract method of the Vector2D class. Compares the x and y values of the result of
   * the subtraction with the expected values.
   */
  @Test
  void testSubtract() {
    Vector2D v1 = new Vector2D(1, 2);
    Vector2D v2 = new Vector2D(3, 4);
    Vector2D v3 = v1.subtract(v2);
    assertEquals(-2, v3.getX(), 0.0001);
    assertEquals(-2, v3.getY(), 0.0001);
  }
}