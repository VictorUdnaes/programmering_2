package edu.ntnu.stud.model.chaosGame;

import edu.ntnu.stud.model.interfaces.Transform2D;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.linalg.vector.Vector3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The ChaosGame class is used to generate fractal sets using the chaos game algorithm.
 * The class generates fractal sets based on the given parameters, such as the number of iterations and the starting point.
 * The fractal sets are generated using the fractal description and the transforms in the model.
 * The class also contains a method to update the viewport of the fractal set to allow the user to zoom in and out of the fractal.
 */
public class ChaosGame {
    private final ChaosGameDescription gameDescription;
    private int iterations;
    private int width;
    private int height;
    private Vector2D currentPoint;
    private ChaosCanvas offScreenCanvas; // The off-screen buffer
    private ChaosCanvas canvas; // The canvas
    private Viewport viewport; // The viewport

    /**
     * Creates a new ChaosGame object. The off-screen canvas is only generated once and is used to draw the fractal set.
     *
     * @param gameDescription The description of the chaos game.
     * @param startingPoint   The starting point of the chaos game.
     * @param width           The width of the chaos game.
     * @param height          The height of the chaos game.
     * @param viewport        The viewport of the chaos game.
     */
    public ChaosGame(ChaosGameDescription gameDescription, Vector2D startingPoint, int width, int height, Viewport viewport) {
        this.gameDescription = gameDescription;
        this.currentPoint = startingPoint;
        this.width = width;
        this.height = height;
        this.offScreenCanvas = new ChaosCanvas(3000, 4000, gameDescription.minCoords(), gameDescription.maxCoords(), 1);
        this.viewport = viewport;
        this.viewport.zoomIn(1);
    }

    public ChaosCanvas getCanvas() {
        return this.offScreenCanvas;
    }

    /**
     * Returns the current viewport coordinates.
     *
     * @return The current viewport coordinates.
     */
    public Vector2D getCurrentViewPortCoords() {
        return new Vector2D(viewport.getX(), viewport.getY());
    }

    /**
     * Generates a fractal set based on the given number of iterations and the fractal name.
     * The method generates a fractal set based on the given number of iterations and the fractal name.
     * The fractal set is generated using the chaos game algorithm and the transforms in the model.
     * The method returns a list of points in the fractal set. the type of fractal to be generated is specified by the fractal name,
     * and the Barnsley-Fern Fractal is generated using weighted probabilities for the different transformations to optimize the generation process.
     *
     * @param iterations  The number of iterations to generate the fractal set.
     * @param fractalName The name of the fractal to generate.
     * @return The list of points in the fractal set.
     */
    public List<Vector3D> generateFractalSet(int iterations, String fractalName) {
        this.iterations = iterations;
        if (fractalName.equals("Sierpinski Triangle")) {
            Random random = new Random();
            for (int i = 0; i < iterations; i++) {
                Transform2D transform = gameDescription.transforms().get(random.nextInt(gameDescription.transforms().size()));
                this.currentPoint = transform.transform(currentPoint);
                this.offScreenCanvas.putPixel(currentPoint);
            }
        } else if (fractalName.equals("Barnsley-Fern Fractal")) {
            double[] probabilities = {0.01, 0.85, 0.07, 0.07}; // Probabilities for the different transformations
            for (int i = 0; i < iterations; i++) {
                Transform2D transform = gameDescription.transforms().get(pickWeightedIndex(probabilities));
                this.currentPoint = transform.transform(currentPoint);
                this.offScreenCanvas.putPixel(currentPoint);
            }
        }
        this.canvas = viewport.getView(offScreenCanvas);
        return convertCanvasToFractalSet();
    }

    /**
     * Picks a weighted index based on the given probabilities.
     * The method generates a random value between 0 and 1 and finds the index using the cumulative probabilities.
     * The method returns the index of the chosen element.
     *
     * @param probabilities The probabilities to pick from.
     * @return The index of the chosen element.
     */
    public static int pickWeightedIndex(double[] probabilities) {
        if (probabilities.length == 0) {
            throw new IllegalArgumentException("Probabilities array must have at least one element");
        }

        // Calculate the cumulative probabilities
        double[] cumulativeProbabilities = new double[probabilities.length];
        cumulativeProbabilities[0] = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            cumulativeProbabilities[i] = cumulativeProbabilities[i - 1] + probabilities[i];
        }

        // Generate a random value between 0 and 1
        double randomValue = Math.random();

        // Find the index using cumulative probabilities
        for (int i = 0; i < probabilities.length; i++) {
            if (randomValue <= cumulativeProbabilities[i]) {
                return i;
            }
        }

        // If no element is chosen due to rounding errors, return the last index (safer)
        return probabilities.length - 1;
    }

    /**
     * Converts the chaos canvas to a list of points in the fractal set.
     * The method iterates over the canvas array and adds the points to the list if the pixel is set,
     * meaning the point is in the fractal set. The method returns the list of points in the fractal set.
     *
     * @return The list of points in the fractal set.
     */
    private List<Vector3D> convertCanvasToFractalSet() {
        List<Vector3D> fractalSet = new ArrayList<>();
        int[][] canvasArray = canvas.getCanvasArray();
        int columnNumber = 0;
        int rowNumber = 0;
        for (int[] row : canvasArray) {
            for (int pixel : row) {
                if (pixel >= 1) {
                    fractalSet.add(new Vector3D(columnNumber, rowNumber, pixel));
                }
                columnNumber++;
            }
            columnNumber = 0;
            rowNumber++;
        }
        return fractalSet;
    }

    /**
     * Updates the viewport of the fractal set.
     * The method updates the viewport of the fractal set based on the given zoom factor.
     * The method returns the list of points in the fractal set.
     *
     * @param viewport    The viewport of the fractal set.
     * @param zoom        The zoom factor for the fractal set.
     * @param fractalName The name of the fractal to generate.
     * @return The list of points in the fractal set.
     */
    public List<Vector3D> updateViewport(Viewport viewport, float zoom, String fractalName) {
        this.viewport = viewport;
        this.viewport.zoomIn(zoom);
        return generateFractalSet(this.iterations, fractalName);
    }
}
