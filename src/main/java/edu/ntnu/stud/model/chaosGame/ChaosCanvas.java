package edu.ntnu.stud.model.chaosGame;

import edu.ntnu.stud.model.linalg.Matrix2x2;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.fractals.AffineTransform2D;
import edu.ntnu.stud.model.utils.ValidateDimensions;

/**
 * Class for representing a 2D canvas for drawing points. The canvas is represented as a 2D integer
 * array, where each element is a pixel. The canvas has a width and a height, and is defined by
 * minimum and maximum coordinates. The canvas also has an AffineTransform2D object for transforming
 * coordinates to indices in the 2D array.
 */
public class ChaosCanvas {

    private final int width;
    private final int height;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private final AffineTransform2D transformCoordsToIndices;
    private final int[][] canvas;

    /**
     * Constructor for the ChaosCanvas class. Initializes the canvas with the given width and height,
     * and sets the minimum and maximum coordinates. Also initializes the AffineTransform2D object
     * used for coordinate transformation.
     *
     * @param width     The width of the canvas.
     * @param height    The height of the canvas.
     * @param minCoords The minimum coordinates of the canvas.
     * @param maxCoords The maximum coordinates of the canvas.
     */
    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords, float zoom) {
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        minCoords.scale(zoom);
        maxCoords.scale(zoom);
        this.canvas = new int[width][height];
        clearCanvas();
        Matrix2x2 A = new Matrix2x2(
                0,
                (height - 1) / (minCoords.getY() - maxCoords.getY()),
                (width - 1) / (maxCoords.getX()) - minCoords.getX(),
                0
        );
        Vector2D b = new Vector2D(
                ((height - 1) * maxCoords.getY()) / (maxCoords.getY() - minCoords.getY()),
                ((width - 1) * minCoords.getX()) / (minCoords.getX() - maxCoords.getX())
        );
        this.transformCoordsToIndices = new AffineTransform2D(A, b);
    }

    /**
     * Clears the canvas by setting all pixel values in the 2D array to 0.
     */
    public void clearCanvas() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.canvas[i][j] = 0;
            }
        }
    }

    /**
     * Returns the pixel value at the given point. The point is transformed to indices in the 2D array
     * using the AffineTransform2D object.
     */
    public int getPixel(Vector2D point) {
        Vector2D coordinates = transformCoordsToIndices.transform(point);
        return canvas[(int) coordinates.getX()][(int) coordinates.getY()];
    }

    /**
     * Sets the pixel value at the given point to 1. The point is transformed to indices in the 2D
     * array using the AffineTransform2D object.
     */
    public void putPixel(Vector2D point) {
        Vector2D coordinates = transformCoordsToIndices.transform(point);
        int x = (int) coordinates.getX();
        int y = (int) coordinates.getY();
        if (x >= 0 && x < width && y >= 0 && y < height) {
            canvas[x][y] += 1;
        }
    }

    /**
     * Returns the 2D integer array representing the pixels on the canvas.
     */
    public int[][] getCanvasArray() {
        return this.canvas;
    }

    public void scaleMinCoords(float zoom) {
        this.minCoords.scale(zoom);
    }

    public void scaleMaxCoords(float zoom) {
        this.maxCoords.scale(zoom);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2D getMinCoords() {
        return minCoords;
    }
    public Vector2D getMaxCoords() {
        return maxCoords;
    }
}
