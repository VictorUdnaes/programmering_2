package edu.ntnu.stud.model.chaosGame;

import edu.ntnu.stud.model.interfaces.ChaosGameObserver;
import edu.ntnu.stud.model.linalg.vector.Vector3D;

import java.util.List;

/**
 * The Viewport class is used to represent a viewport of a fractal.
 * The fractals are first drawn on an off-screen canvas, and then the viewport is used to display the fractal on the screen.
 * the users view of the fractal is represented by the viewport and navigation is done by zooming in and out and changing
 * the base coordinates of the viewport.
 */
public class Viewport implements ChaosGameObserver {
    private double x;
    private double y;
    private int width;
    private int height;
    private String fractalName;

    /**
     * Creates a new Viewport object.
     *
     * @param x      the x-coordinate of the viewport
     * @param y      the y-coordinate of the viewport
     * @param width  the width of the viewport
     * @param height the height of the viewport
     */
    public Viewport(double x, double y, int width, int height, String fractalName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fractalName = fractalName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Zooms in on the viewport by a given factor.
     *
     * @param factor the factor to zoom in by
     */
    public void zoomIn(double factor) {
        width /= factor;
        height /= factor;
    }

    /**
     * Zooms out on the viewport by a given factor.
     *
     * @param factor the factor to zoom out by
     */
    public void zoomOut(double factor) {
        width *= factor;
        height *= factor;
    }

    /**
     * Builds a viewport canvas og the fractal using the offScreenCanvas.
     * The method creates a new ChaosCanvas with the dimensions of the fractal pane.
     * The method then rescales the pixels from the offScreenCanvas to fit the viewCanvas.
     * The resolution of the viewCanvas is based on the width and height of the viewport.
     *
     * @param offScreenCanvas
     * @return
     */
    public ChaosCanvas getView(ChaosCanvas offScreenCanvas) {
        // Create a new ChaosCanvas with the dimensions of the fractal pane
        ChaosCanvas viewCanvas = new ChaosCanvas(width, height, offScreenCanvas.getMinCoords(), offScreenCanvas.getMaxCoords(), 1);

        // Get the pixel arrays of the offScreenCanvas and the viewCanvas
        int[][] offScreenPixels = offScreenCanvas.getCanvasArray();
        int[][] viewPixels = viewCanvas.getCanvasArray();

        // Calculate the scaling factors
        double scaleX = (double) offScreenCanvas.getWidth() / width;
        double scaleY = (double) offScreenCanvas.getHeight() / height;

        // Rescale the pixels from the offScreenCanvas to fit the viewCanvas
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int offScreenX = (int) ((i + x) * scaleX);
                int offScreenY = (int) ((j + y) * scaleY);
                if (offScreenX >= 0 && offScreenX < offScreenCanvas.getWidth() && offScreenY >= 0 && offScreenY < offScreenCanvas.getHeight()) {
                    viewPixels[i][j] = offScreenPixels[offScreenX][offScreenY];
                }
            }
        }
        return viewCanvas;
    }

    public String getFractalName() {
        return fractalName;
    }

    /**
     * Updates the viewport with the given parameters.
     * The method updates the viewport with the given parameters.
     *
     * @param x     the change of the x-coordinate of the viewport
     * @param y     the change of the y-coordinate of the viewport
     * @param width the width of the viewport
     * @param height the height of the viewport
     * @param zoom  the zoom factor for the viewport
     */
    @Override
    public void update(int x, int y, float zoom) {
        this.x += x;
        this.y += y;
        zoomIn(zoom);
    }
}
