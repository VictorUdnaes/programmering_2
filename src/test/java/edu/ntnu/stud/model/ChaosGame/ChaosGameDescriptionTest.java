package edu.ntnu.stud.model.ChaosGame;

import edu.ntnu.stud.model.chaosGame.ChaosGameDescription;
import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.AffineTransform2D;
import edu.ntnu.stud.model.fractals.JuliaTransform;
import edu.ntnu.stud.model.interfaces.Transform2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


/**
 * Class for testing the ChaosGameDescription class.
 */

class ChaosGameDescriptionTest {

  // Test the constructor of the ChaosGameDescription class.
  @Test
  void testConstructor() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        transforms);

    assertEquals(minCoords, chaosGameDescription.minCoords());
    assertEquals(maxCoords, chaosGameDescription.maxCoords());
    assertEquals(transforms, chaosGameDescription.transforms());
  }

  // Test the getMinCoords method of the ChaosGameDescription class.
  @Test
  void testGetMinCoords() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        null);

    assertEquals(minCoords, chaosGameDescription.minCoords());
  }

  // Test the getMaxCoords method of the ChaosGameDescription class.
  @Test
  void testGetMaxCoords() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        null);

    assertEquals(maxCoords, chaosGameDescription.maxCoords());
  }

  // Test the getTransforms method of the ChaosGameDescription class.
  @Test
  void testGetTransforms() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        transforms);

    assertEquals(transforms, chaosGameDescription.transforms());
  }

  // Test the different types of transformations in the ChaosGameDescription class.
  @Test
  void testTransforms() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    transforms.add(new JuliaTransform(new Complex(1, 1), 1));
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        transforms);

    assertEquals(2, chaosGameDescription.transforms().size());
    assertInstanceOf(AffineTransform2D.class, chaosGameDescription.transforms().get(0));
    assertInstanceOf(JuliaTransform.class, chaosGameDescription.transforms().get(1));
  }

  // Test ChaosGameDescription objects with different coordinates.
  @Test
  void testDifferentCoordinates() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords,
        null);

    assertEquals(0.0, chaosGameDescription.minCoords().getX());
    assertEquals(0.0, chaosGameDescription.minCoords().getY());
    assertEquals(1.0, chaosGameDescription.maxCoords().getX());
    assertEquals(1.0, chaosGameDescription.maxCoords().getY());

    Vector2D minCoords2 = new Vector2D(1, 1);
    Vector2D maxCoords2 = new Vector2D(2, 2);
    ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(minCoords2, maxCoords2,
        null);

    assertEquals(1.0, chaosGameDescription2.minCoords().getX());
    assertEquals(1.0, chaosGameDescription2.minCoords().getY());
    assertEquals(2.0, chaosGameDescription2.maxCoords().getX());
    assertEquals(2.0, chaosGameDescription2.maxCoords().getY());
  }
}
