package edu.ntnu.stud.model.chaosGame;

import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.AffineTransform2D;
import edu.ntnu.stud.model.fractals.JuliaTransform;
import edu.ntnu.stud.model.interfaces.Transform2D;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for handling the reading and writing of chaos game files.
 *
 * @author Snake727
 * @version 0.1.0
 */

public class ChaosGameFileHandler {

  /**
   * Reads a chaos game description from a file. The method can read two types of transformations:
   * Affine2D and Julia.
   *
   * @param filePath The path to the file to read from.
   * @return ChaosGameDescription
   * @throws FileNotFoundException If the file is not found.
   */

  public static ChaosGameDescription readFromFile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    String transformationType = scanner.nextLine().split(" ")[0];
    String[] minCoordsParts = scanner.nextLine().split("#")[0].split(",");
    double minX = Double.parseDouble(minCoordsParts[0]);
    double minY = Double.parseDouble(minCoordsParts[1]);

    String[] maxCoordsParts = scanner.nextLine().split("#")[0].split(",");
    double maxX = Double.parseDouble(maxCoordsParts[0]);
    double maxY = Double.parseDouble(maxCoordsParts[1]);

    Vector2D minCoords = new Vector2D(minX, minY);
    Vector2D maxCoords = new Vector2D(maxX, maxY);

    List<Transform2D> transforms = new ArrayList<>();
    while (scanner.hasNext()) {
      String[] line = scanner.nextLine().split("#")[0].split(",");
      if (transformationType.equals("Affine2D")) {
        double a00 = Double.parseDouble(line[0]);
        double a01 = Double.parseDouble(line[1]);
        double a10 = Double.parseDouble(line[2]);
        double a11 = Double.parseDouble(line[3]);
        double b0 = Double.parseDouble(line[4]);
        double b1 = Double.parseDouble(line[5]);

        Matrix2x2 matrix = new Matrix2x2(a00, a01, a10, a11);
        Vector2D vector = new Vector2D(b0, b1);

        transforms.add(new AffineTransform2D(matrix, vector));
      } else if (transformationType.equals("Julia")) {
        double real = Double.parseDouble(line[0]);
        double imaginary = Double.parseDouble(line[1]);

        Complex point = new Complex(real, imaginary);

        transforms.add(new JuliaTransform(point, 1));
      }
    }

    scanner.close();

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Writes a chaos game description to a file. The method can write two types of transformations:
   * Affine2D and Julia.
   *
   * @param chaosGameDescription the chaos game description to write to the file.
   * @param filePath             the path to the file to write to.
   * @throws FileNotFoundException if the file is not found.
   */
  public static void writeToFile(ChaosGameDescription chaosGameDescription,
      String filePath) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter((filePath));

    if (!chaosGameDescription.transforms().isEmpty()) {
      Transform2D firstTransform = chaosGameDescription.transforms().get(0);
      if (firstTransform instanceof AffineTransform2D) {
        writer.println("Affine2D # Type of transform");
      } else if (firstTransform instanceof JuliaTransform) {
        writer.println("Julia # Type of transform");
      }
    }

    writer.println(chaosGameDescription.minCoords().getX()
        + "," + chaosGameDescription.minCoords().getY() + " # Lower left");
    writer.println(chaosGameDescription.maxCoords().getX()
        + "," + chaosGameDescription.maxCoords().getY() + " # Upper right");

    int affineCount = 1;
    for (Transform2D transform : chaosGameDescription.transforms()) {
      if (transform instanceof AffineTransform2D affine) {
        Matrix2x2 matrix = affine.getMatrix();
        Vector2D vector = affine.getVector();
        writer.println(matrix.getA00() + "," + matrix.getA01()
            + "," + matrix.getA10() + "," + matrix.getA11()
            + "," + vector.getX() + "," + vector.getY()
            + " # " + "transform nr: " + affineCount + " (a00, a01, a10, a11, b0, b1)");
        affineCount++;
      } else if (transform instanceof JuliaTransform julia) {
        Complex point = julia.getPoint();
        writer.println(point.getX() + "," + point.getY()
            + " # Real and imaginary parts of the constant c");
      }
    }

    writer.close();
  }
}
