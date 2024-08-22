package edu.ntnu.stud.model.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.stud.model.linalg.vector.Vector3D;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FractalPersistence {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Saves the list of Vector3D points to a JSON file.
   *
   * @param points the list of Vector3D points to save
   * @param filePath the path to the JSON file
   * @throws IOException if an I/O error occurs
   */
  public static void savePointsToJson(List<Vector3D> points, String filePath) throws IOException {
    objectMapper.writeValue(new File(filePath), points);
  }

  /**
   * Loads the list of Vector3D points from a JSON file.
   *
   * @param filePath the path to the JSON file
   * @return the list of Vector3D points
   * @throws IOException if an I/O error occurs
   */
  public static List<Vector3D> loadPointsFromJson(String filePath) throws IOException {
    return objectMapper.readValue(new File(filePath), new TypeReference<List<Vector3D>>() {});
  }
}
