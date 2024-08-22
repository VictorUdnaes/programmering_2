package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.linalg.Complex;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Complex class.
 */
class ComplexTest {

  /**
   * Test the add method of the Complex class. Compares the x and y values of the result of the
   * addition with the expected values.
   */
  @Test
  void testAdd() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    Complex c3 = c1.add(c2);
    assertEquals(4, c3.getX(), 0.0001);
    assertEquals(6, c3.getY(), 0.0001);
  }

  /**
   * Test the subtract method of the Complex class. Compares the x and y values of the result of the
   * subtraction with the expected values.
   */
  @Test
  void testSubtract() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    Complex c3 = c1.subtract(c2);
    assertEquals(-2, c3.getX(), 0.0001);
    assertEquals(-2, c3.getY(), 0.0001);
  }

  /**
   * Test the multiply method of the Complex class. Compares the x and y values of the result of the
   * multiplication with the expected values.
   */
  @Test
  void testMultiply() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    Complex c3 = c1.multiply(c2);
    assertEquals(-5, c3.getX(), 0.0001);
    assertEquals(10, c3.getY(), 0.0001);
  }

  /**
   * Test the sqrt method of the Complex class. Compares the x and y values of the result of the
   * square root with the expected values.
   */
  @Test
  void testSqrt() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = c1.sqrt();
    assertEquals(1.2720, c2.getX(), 0.0001);
    assertEquals(0.7861, c2.getY(), 0.0001);
  }

  /**
   * Test the scalarMultiplication method of the Complex class. Compares the x and y values of the
   * result of the scalar multiplication with the expected values.
   */
  @Test
  void testScalarMultiplication() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = c1.scalarMultiplication(2);
    assertEquals(2, c2.getX(), 0.0001);
    assertEquals(4, c2.getY(), 0.0001);
  }
}
