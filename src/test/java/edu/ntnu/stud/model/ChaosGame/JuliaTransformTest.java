package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.JuliaTransform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the JuliaTransform class.
 */
class JuliaTransformTest {

  JuliaTransform juliaTransform;

  /**
   * Set up the test class. OBS! if the complex number or sign is changed, the
   * testComplexTransformation will fail.
   */
  @BeforeEach
  public void setUp() {
    juliaTransform = new JuliaTransform(new Complex(2, 2), 1);
  }

  /**
   * Test the transformation of the JuliaTransform class. Compares the x and y values of the result
   * of the julia transformation with the expected values.
   */
  @Test
  void testComplexTransformation() {
    Vector2D result = juliaTransform.transform(new Vector2D(1, 1));
    assertEquals(1.09868411346781, result.getX(), 0.000000000001);
    assertEquals(0.4550898605622274, result.getY(), 0.000000000001);
  }
}
