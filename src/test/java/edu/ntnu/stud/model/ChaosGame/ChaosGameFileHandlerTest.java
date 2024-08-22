package edu.ntnu.stud.model.ChaosGame;

import edu.ntnu.stud.model.chaosGame.ChaosGameDescription;
import edu.ntnu.stud.model.chaosGame.ChaosGameFileHandler;
import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.AffineTransform2D;
import edu.ntnu.stud.model.fractals.JuliaTransform;
import edu.ntnu.stud.model.interfaces.Transform2D;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ChaosGameFileHandlerTest {

  private final ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();

  @Test
  void testWriteToFileAffine() throws FileNotFoundException {
    String filePath = "src/test/resources/testWriteFileAffine.txt";
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        transforms);

    ChaosGameFileHandler.writeToFile(chaosGameDescription, filePath);

    try (Scanner scanner = new Scanner(new File(filePath))) {
      assertEquals("Affine2D # Type of transform", scanner.nextLine());
      assertEquals("0.0,0.0 # Lower left", scanner.nextLine());
      assertEquals("1.0,1.0 # Upper right", scanner.nextLine());
      assertEquals("1.0,0.0,0.0,1.0,0.0,0.0 # transform nr: 1 (a00, a01, a10, a11, b0, b1)",
          scanner.nextLine());
      assertEquals("1.0,0.0,0.0,1.0,0.0,0.0 # transform nr: 2 (a00, a01, a10, a11, b0, b1)",
          scanner.nextLine());
      assertEquals("1.0,0.0,0.0,1.0,0.0,0.0 # transform nr: 3 (a00, a01, a10, a11, b0, b1)",
          scanner.nextLine());
    }
  }

  @Test
  void testWriteToFileJulia() throws FileNotFoundException {
    String filePath = "src/test/resources/testWriteFileJulia.txt";
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new JuliaTransform(new Complex(1, 1), 1));
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        transforms);

    ChaosGameFileHandler.writeToFile(chaosGameDescription, filePath);

    try (Scanner scanner = new Scanner(new File(filePath))) {
      assertEquals("Julia # Type of transform", scanner.nextLine());
      assertEquals("0.0,0.0 # Lower left", scanner.nextLine());
      assertEquals("1.0,1.0 # Upper right", scanner.nextLine());
      assertEquals("1.0,1.0 # Real and imaginary parts of the constant c", scanner.nextLine());
    }
  }

  @Test
  void testReadFromFileJulia() throws FileNotFoundException {
    String filePath = "src/test/resources/testWriteFileJulia.txt";

    ChaosGameDescription chaosGameDescription = ChaosGameFileHandler.readFromFile(filePath);

    assertEquals(0.0, chaosGameDescription.minCoords().getX());
    assertEquals(0.0, chaosGameDescription.minCoords().getY());
    assertEquals(1.0, chaosGameDescription.maxCoords().getX());
    assertEquals(1.0, chaosGameDescription.maxCoords().getY());
    assertEquals(1, chaosGameDescription.transforms().size());
    assertInstanceOf(JuliaTransform.class, chaosGameDescription.transforms().get(0));
  }

  @Test
  void testReadFromFileAffine2D() throws FileNotFoundException {
    String filePath = "src/test/resources/testWriteFileAffine.txt";

    ChaosGameDescription chaosGameDescription = ChaosGameFileHandler.readFromFile(filePath);

    assertEquals(0.0, chaosGameDescription.minCoords().getX());
    assertEquals(0.0, chaosGameDescription.minCoords().getY());
    assertEquals(1.0, chaosGameDescription.maxCoords().getX());
    assertEquals(1.0, chaosGameDescription.maxCoords().getY());
    assertEquals(3, chaosGameDescription.transforms().size());
    assertInstanceOf(AffineTransform2D.class, chaosGameDescription.transforms().get(0));
  }
}