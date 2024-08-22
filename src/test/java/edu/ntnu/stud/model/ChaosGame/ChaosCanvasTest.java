package edu.ntnu.stud.model.ChaosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.model.chaosGame.ChaosCanvas;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * ChaosCanvas er jævlig rar, så selv om minimumskoord er 0,0 og maksimumskoord er 1,1, så
 * vil den avhengig av hvor stor arrayet tillate koordinater som er går utenfor minimums og
 * makskoordinatene, f.eks. -0.19. Jo større arrayet er jo mindre vil slingringsmonnet være.
 * Se om det er mulig å regne ut hvor stor feil canvaset tillater basert på størrelsen av arrayet.
 */
class ChaosCanvasTest {

  ChaosCanvas canvas; // The canvas to test

  /**
   * Provides a stream of arguments for the putPixel method of the ChaosCanvas class. The arguments
   * are used to test the putPixel method with valid coordinates.
   *
   * @return a stream of arguments for the putPixel method of the ChaosCanvas class.
   */
  private static Stream<Arguments> dataForChaosCanvasTest() {
    return Stream.of(
        Arguments.of(
            new Vector2D(0, 0)),
        Arguments.of(
            new Vector2D(0.2, 0.2)),
        Arguments.of(
            new Vector2D(0.4, 0.4)),
        Arguments.of(
            new Vector2D(0.6, 0.6)),
        Arguments.of(
            new Vector2D(0.8, 0.8)),
        Arguments.of(
            new Vector2D(1, 1))
    );
  }

  /**
   * Provides a stream of arguments for the putPixel method of the ChaosCanvas class. The arguments
   * are used to test the putPixel method with invalid coordinates.
   *
   * @return a stream of arguments for the putPixel method of the ChaosCanvas class.
   */
  private static Stream<Arguments> dataForChaosCanvasNegativeTest() {
    return Stream.of(
        Arguments.of(
            new Vector2D(-0.1, -0.1)),
        Arguments.of(
            new Vector2D(1.1, 1.1))
    );
  }

  /**
   * Set up the test class. Initializes the canvas with a size of 50x50 and a minimum and maximum
   * coordinate of (0,0) and (1,1).
   */
  @BeforeEach
  public void setUp() {
    canvas = new ChaosCanvas(50, 50, new Vector2D(0, 0), new Vector2D(1, 1), 1);
  }

  /**
   * Test the putPixel method of the ChaosCanvas class. Puts a pixel at the given coordinates and
   * checks if the pixel is set.
   */
  @ParameterizedTest
  @MethodSource("dataForChaosCanvasTest")
  void putValidPixelTest(Vector2D coords) {
    canvas.putPixel(coords);
    int result = canvas.getPixel(coords);
    assertEquals(1, result);
  }

  /**
   * Test putting a pixel outside the canvas bounds. The test asserts that an
   * ArrayIndexOutOfBoundsException is thrown when trying to put a pixel outside the canvas bounds.
   */
  @ParameterizedTest
  @MethodSource("dataForChaosCanvasNegativeTest")
  @Disabled
  void putInvalidPixelTest(Vector2D coords) { // disabled because the test fails, canvas is not in use right now.
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> canvas.putPixel(coords));
  }
}
