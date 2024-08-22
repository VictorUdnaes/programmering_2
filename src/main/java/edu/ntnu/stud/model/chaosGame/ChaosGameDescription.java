package edu.ntnu.stud.model.chaosGame;

import edu.ntnu.stud.model.interfaces.Transform2D;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import java.util.List;

/**
 * This record is responsible for describing everything that is necessary to calculate the actual
 * fractal.
 * Parameters:<br>
 * - minCoords: The minimum coordinates of the fractal.<br>
 * - maxCoords: The maximum coordinates of the fractal.<br>
 * - transforms: A list of transformations that will be applied to the fractal.
 *
 * @author Snake727
 * @version 0.1.0
 */
public record ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords, List<Transform2D> transforms) {
}
