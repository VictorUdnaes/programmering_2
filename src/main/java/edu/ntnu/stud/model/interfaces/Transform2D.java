package edu.ntnu.stud.model.interfaces;

import edu.ntnu.stud.model.linalg.vector.Vector2D;

/**
 * Interface for transforming a 2D vector.
 */
public interface Transform2D {
  String toString();
  Vector2D transform(Vector2D vector);
}
