package edu.ntnu.stud.model.linalg.vector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Class for representing a 2D vector.
 */
public class Vector2D implements Vector {

  private double x; // The x-coordinate of the vector.
  private double y; // The y-coordinate of the vector.

  /**
   * Constructor for the Vector2D class.
   *
   * @param x The x-coordinate of the vector.
   * @param y The y-coordinate of the vector.
   */

  @JsonCreator
  public Vector2D(@JsonProperty("x") double x, @JsonProperty("y") double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Adds the given vector to this vector.
   *
   * @param other The vector to add.
   * @return The result of the addition.
   */
  public Vector2D add(Vector2D other) {
    return new Vector2D(x + other.x, y + other.y);
  }

  /**
   * Subtracts the given vector from this vector.
   *
   * @param other The vector to subtract.
   * @return The result of the subtraction.
   */
  public Vector2D subtract(Vector2D other) {
    return new Vector2D(x - other.x, y - other.y);
  }

  /**
   * Calculates the dot product of this vector and the given vector.
   *
   * @param other The vector to calculate the dot product with.
   * @return The result of the dot product.
   */
  public double dotProduct(Vector2D other) {
    return x * other.x + y * other.y;
  }

  /**
   * Calculates the cross product of this vector and the given vector.
   *
   * @param other The vector to calculate the cross product with.
   * @return The result of the cross product.
   */
  public double crossProduct(Vector2D other) {
    return x * other.y - y * other.x;
  }

  /**
   * Calculates the magnitude (length) of the vector.
   *
   * @return The magnitude of the vector.
   */
  public double magnitude() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Normalizes the vector (makes its magnitude 1).
   */
  public void normalize() {
    double mag = magnitude();
    if (mag != 0) {
      x /= mag;
      y /= mag;
    }
  }

  public void square() {
    double x_temp = x * x - y * y;
    double y_temp = 2 * x * y;
    x = x_temp;
    y = y_temp;
  }

  public void scale(double factor) {
    x *= factor;
    y *= factor;
  }

  // Getters for the x and y coordinates of the vector.
  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }
  public void setX(double x) {this.x = x;}
  public void setY(double y) {this.y = y;}
}
