package edu.ntnu.stud.model.interfaces;

import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.linalg.vector.Vector3D;

import java.util.List;

public interface Fractal2D {
    List<Vector3D> generateFractalSet();
}
