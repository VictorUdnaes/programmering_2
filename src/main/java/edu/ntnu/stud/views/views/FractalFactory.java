package edu.ntnu.stud.views.views;

import edu.ntnu.stud.controller.FractalController;
import edu.ntnu.stud.utils.ErrorCode;
import edu.ntnu.stud.utils.FractalGenerationException;
import edu.ntnu.stud.model.chaosGame.Viewport;
import edu.ntnu.stud.model.fractals.mandelbulb.FractalPoint;
import edu.ntnu.stud.model.fractals.mandelbulb.SmartGroup;
import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.model.utils.Mapper;
import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.List;

/**
 * The ViewFactory class is responsible for creating the views of the application.
 * The class creates the views of the Mandelbrot, Julia, and affine fractals.
 */
public class FractalFactory {
    private final int fractalPaneWidth;
    private final int fractalPaneHeight;
    private final FractalController fractalController;

    public FractalFactory(int fractalPaneWidth, int fractalPaneHeight, FractalController fractalController) {
        this.fractalPaneWidth = fractalPaneWidth;
        this.fractalPaneHeight = fractalPaneHeight;
        this.fractalController = fractalController;
    }

    /**
     * Creates a Mandelbrot fractal with the given parameter component.
     *
     * @param pc The parameter component to create the Mandelbrot fractal with.
     * @return The canvas with the Mandelbrot fractal drawn on it.
     */
    public Canvas createMandelbrotFractal(ParameterComponent pc) {
        try {
            return createCanvas(fractalController.getMandelbrotSet(pc), "Mandelbrot", pc.getParameterPane().getParameterValue("MAX ITERATIONS"));
        } catch (NullPointerException e) {
            throw new FractalGenerationException(ErrorCode.PARAMETER_COMPONENT_NULL, "Parameter component is null", e);
        }
    }

    /**
     * Creates a Julia fractal with the given parameter component.
     *
     * @param pc The parameter component to create the Julia fractal with.
     * @return The canvas with the Julia fractal drawn on it.
     */
    public Canvas createCoordinateJuliaFractal(ParameterComponent pc) {

        try {
            return createCanvas(fractalController.getJuliaSet(pc), "Julia", pc.getParameterPane().getParameterValue("MAX ITERATIONS"));
        } catch (NullPointerException e) {
            throw new FractalGenerationException(ErrorCode.PARAMETER_COMPONENT_NULL, "Parameter component is null", e);
        }
    }

    /**
     * Creates an affine fractal with the given parameter component.
     *
     * @param fractalType The type of the affine fractal to create.
     * @param pc          The parameter component to create the affine fractal with.
     * @return The canvas with the affine fractal drawn on it.
     */
    public Canvas createAffineFractal(String fractalType, ParameterComponent pc, Viewport viewport) {
        try {
            return createCanvas(fractalController.getAffineTransformationSet(fractalType, pc, viewport), fractalType);
        } catch (NullPointerException e) {
            throw new FractalGenerationException(ErrorCode.PARAMETER_COMPONENT_NULL, "Parameter component is null", e);
        }
    }

    /**
     * Creates a Mandelbulb fractal SmartGroup.
     *
     * @return The canvas with the Mandelbulb fractal drawn on it.
     */
    public SmartGroup createMandelbulbSmartGroup() {
        SmartGroup group = new SmartGroup();
        List<FractalPoint> points = fractalController.getMandelBulbSet();
        for (FractalPoint fractalPoint : points) {
            Sphere sphere = new Sphere(0.03);
            sphere.setTranslateX(fractalPoint.point().getX());
            sphere.setTranslateY(fractalPoint.point().getY());
            sphere.setTranslateZ(fractalPoint.point().getZ());
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(getColorForFractalPoint((float) fractalPoint.r()));
            sphere.setMaterial(material);
            group.getChildren().add(sphere);
        }
        return group;
    }

    private Color getColorForFractalPoint(float r) {
        double hue = Mapper.map(r, 0, 2, 0, 360);
        return Color.hsb(hue, 1.0, 1.0); // Create color using
    }

    /**
     * Creates a canvas with the given points.
     * The method iterates over the list of points and draws them on the canvas.
     * The points are drawn on the canvas using a pixel writer.
     *
     * @param points The points to draw on the canvas.
     * @return The canvas with the points drawn on it.
     */
    public Canvas createCanvas(List<Vector3D> points, String fractalType, String... maxIterations) {
        try {
            int maxIterationsValue;
            if (maxIterations.length > 0) {
                maxIterationsValue = Integer.parseInt(maxIterations[0]);
            } else {
                maxIterationsValue = 0;
            }
            Canvas onScreenCanvas = new Canvas(fractalPaneWidth, fractalPaneHeight);
            GraphicsContext onScreenGC = onScreenCanvas.getGraphicsContext2D();
            onScreenGC.setFill(Color.BLACK);
            onScreenGC.fillRect(0, 0, fractalPaneWidth, fractalPaneHeight);

            // Create the off-screen canvas (buffer)
            Canvas offScreenCanvas = new Canvas(fractalPaneWidth, fractalPaneHeight);
            GraphicsContext offScreenGC = offScreenCanvas.getGraphicsContext2D();
            offScreenGC.setFill(Color.BLACK);
            offScreenGC.fillRect(0, 0, fractalPaneWidth, fractalPaneHeight);

            WritableImage writableImage = new WritableImage(fractalPaneWidth, fractalPaneHeight);
            PixelWriter pixelWriter = writableImage.getPixelWriter();
            points.forEach(point -> writePixel(point, fractalType, maxIterationsValue, pixelWriter));

            // Draw the image on the off-screen canvas
            offScreenGC.drawImage(writableImage, 0, 0);
            onScreenGC.drawImage(offScreenCanvas.snapshot(null, null), 0, 0);
            return onScreenGC.getCanvas();
        } catch (NullPointerException e) {
            throw new FractalGenerationException(ErrorCode.PARAMETER_COMPONENT_NULL, "Points are null", e);
        }
    }

    /**
     * Writes a pixel to the canvas.
     * The method writes a pixel to the canvas at the given point.
     * The color of the pixel is determined based on the fractal type and the value set in the z-coordinate of the vector.
     *
     * @param point              The point to write the pixel at.
     * @param fractalType        The type of the fractal.
     * @param maxIterationsValue The maximum number of iterations.
     * @param pixelWriter        The pixel writer to write the pixel with.
     */
    private void writePixel(Vector3D point, String fractalType, int maxIterationsValue, PixelWriter pixelWriter) {
        try {
            switch (fractalType) {
                case "Mandelbrot", "Julia":
                    pixelWriter.setColor((int) point.getX(), (int) point.getY(), getCoordinateFractalColor(point, maxIterationsValue));
                    break;
                case "Sierpinski Triangle", "Barnsley-Fern Fractal":
                    pixelWriter.setColor((int) point.getX(), (int) point.getY(), getChaosFractalColor(point));
                    break;
                default:
                    pixelWriter.setColor((int) point.getX(), (int) point.getY(), Color.BLACK);
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new FractalGenerationException(ErrorCode.CANVAS_OUT_OF_BOUNDS, "Point: " + point.getX() + ", " + point.getY(), e);
        }
    }

    /**
     * Gets the color for a coordinate fractal point.
     * The method determines the color of the point based on the iteration count of the point.
     *
     * @param point         The point to get the color for.
     * @param maxIterations The maximum number of iterations.
     * @return The color of the point.
     */
    private Color getCoordinateFractalColor(Vector3D point, int maxIterations) {
        // Normalize the iteration count to a value between 0 and 1
        double normalizedIteration = Math.min(Math.max(point.getZ() / maxIterations, 0), 1);

        // Use the normalized iteration count to determine the hue (color)
        // For blue color, the hue is around 240 in the HSB color model
        double hue = 240.0 * (1 - normalizedIteration);

        // Set the saturation and brightness based on the normalized iteration count
        double saturation = 1 * (1 - normalizedIteration);
        double brightness = 1;

        // If the point is in the set (iteration count is max), color it black
        if (point.getZ() == maxIterations) {
            saturation = 0;
            brightness = 0;
        }

        // Create a color using the HSB color model
        return Color.hsb(hue, saturation, brightness);
    }

    /**
     * Gets the color for a chaos fractal point.
     * The method determines the color of the point based on the z-coordinate of the point.
     *
     * @param point The point to get the color for.
     * @return The color of the point.
     */
    private Color getChaosFractalColor(Vector3D point) {
        double normalizedZ = point.getZ() / (10); // Normalize z to the range 0-1 based on the average z-value
        normalizedZ = 360.0 * normalizedZ;
        // Use the normalized z-value to determine the hue (color)
        double saturation = 1;
        double brightness = 1;

        // Create a color using the HSB color model
        return Color.hsb(normalizedZ, saturation, brightness);
    }

    /**
     * Updates the viewport of the fractal.
     *
     * @param fractalName The name of the fractal to update the viewport for.
     * @return The canvas with the updated viewport.
     */
    public Canvas updateViewport(String fractalName) {
        try {
            return createCanvas(fractalController.updateViewport(), fractalName);
        } catch (NullPointerException e) {
            throw new FractalGenerationException(ErrorCode.PARAMETER_COMPONENT_NULL, "Parameter component is null", e);
        }
    }
}