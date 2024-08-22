package edu.ntnu.stud.model.ChaosGame;

import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.model.utils.FractalPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FractalPersistenceTest {

  @Test
  @DisplayName("Generate a list of Vector3D points and save them to a JSON file, then load them back from the file.")
  void testFractalPersistenceMethods() throws IOException {
    List<Vector3D> originalPoints = Arrays.asList(new Vector3D(1, 2, 3), new Vector3D(4, 5, 6), new Vector3D(7, 8, 9));

    String filePath = "points.json";
    FractalPersistence.savePointsToJson(originalPoints, filePath);

    List<Vector3D> loadedPoints = FractalPersistence.loadPointsFromJson(filePath);

    assertEquals(originalPoints, loadedPoints);
  }
}