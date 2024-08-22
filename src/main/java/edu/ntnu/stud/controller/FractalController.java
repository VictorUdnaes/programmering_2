package edu.ntnu.stud.controller;

import edu.ntnu.stud.utils.ErrorCode;
import edu.ntnu.stud.utils.FractalGenerationException;
import edu.ntnu.stud.model.chaosGame.ChaosGame;
import edu.ntnu.stud.model.chaosGame.ChaosGameDescription;
import edu.ntnu.stud.model.chaosGame.ChaosGameFileHandler;
import edu.ntnu.stud.model.chaosGame.Viewport;
import edu.ntnu.stud.model.fractals.CoordinateJuliaTransform;
import edu.ntnu.stud.model.fractals.MandelbrotTransformation;
import edu.ntnu.stud.model.fractals.mandelbulb.FractalPoint;
import edu.ntnu.stud.model.fractals.mandelbulb.Mandelbulb;
import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.vector.Vector2D;
import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The FractalController class is responsible for creating the fractals.
 * The class creates the Mandelbrot, Julia, and affine fractal sets based on the given parameters.
 * The sets are returned as a list of points in the fractal set using the Vector2D class.
 */
public class FractalController {
    private ChaosGame chaosGame;
    private ParameterComponent parameterComponent;
    private Viewport viewport;

    public FractalController() {
    }

    /**
     * Creates a Mandelbrot fractal based on the given parameters.
     * The method creates a fractal based on the given parameters, such as the width, height, and max iterations.
     * The parameters are extracted the parameter component, which is built up of Parameter objects which contain the user input Textfields.
     *
     * @param parameterComponent The parameter component containing the parameters for the fractal.
     * @return The canvas with the fractal drawn on it.
     */
    public List<Vector3D> getMandelbrotSet(ParameterComponent parameterComponent) {
        this.parameterComponent = parameterComponent;
        int width = extractIntParameter(parameterComponent, "WIDTH");
        int height = extractIntParameter(parameterComponent, "HEIGHT");
        int maxIterations = extractIntParameter(parameterComponent, "MAX ITERATIONS");

        MandelbrotTransformation mandelbrot = new MandelbrotTransformation(width, height, maxIterations);
        return mandelbrot.generateFractalSet();
    }

    /**
     * Creates a coordinate Julia fractal based on the given parameters.
     * The method creates a fractal based on the given parameters, such as the width, height, max iterations,
     * and the real and imaginary part of c. The parameters are extracted the parameter component,
     * which is built up of Parameter objects which contain the user input Textfields.
     *
     * @param parameterComponent The parameter component containing the parameters for the fractal.
     * @return The canvas with the fractal drawn on it.
     */
    public List<Vector3D> getJuliaSet(ParameterComponent parameterComponent) {
        this.parameterComponent = parameterComponent;
        int width = extractIntParameter(parameterComponent, "WIDTH");
        int height = extractIntParameter(parameterComponent, "HEIGHT");
        int maxIterations = extractIntParameter(parameterComponent, "MAX ITERATIONS");
        double realPart = extractDoubleParameter(parameterComponent, "REAL PART OF C");
        double imaginaryPart = extractDoubleParameter(parameterComponent, "IMAGINARY PART OF C");
        Complex c = new Complex(realPart, imaginaryPart);

        CoordinateJuliaTransform julia = new CoordinateJuliaTransform(c, width, height, maxIterations);
        return julia.generateFractalSet();
    }

    /**
     * Creates an affine fractal based on the given parameters.
     * The method reads the transformation descriptions from a file and creates a vector for each point in the fractal set.
     *
     * @param fractalType        The type of fractal to create.
     * @param parameterComponent The parameter component containing the parameters for the fractal.
     * @return The canvas with the fractal drawn on it.
     * @throws FractalGenerationException If the file containing the transformation descriptions cannot be found.
     */
    public List<Vector3D> getAffineTransformationSet(String fractalType, ParameterComponent parameterComponent, Viewport viewport) {
        try {
            this.parameterComponent = parameterComponent;
            int width = extractIntParameter(parameterComponent, "WIDTH");
            int height = extractIntParameter(parameterComponent, "HEIGHT");
            int iterations = extractIntParameter(parameterComponent, "ITERATIONS");
            int startX = extractIntParameter(parameterComponent, "STARTING POINT X");
            int startY = extractIntParameter(parameterComponent, "STARTING POINT Y");
            Vector2D start = new Vector2D(startX, startY);
            ChaosGameDescription fractalDescription;
            if (fractalType.equals("Sierpinski Triangle")) {
                fractalDescription = ChaosGameFileHandler.readFromFile(
                        "src/main/resources/transformationFiles/sierpinski.txt");
            } else {
                fractalDescription = ChaosGameFileHandler.readFromFile(
                        "src/main/resources/transformationFiles/barnsley-fern.txt");
            }
            this.viewport = viewport;
            this.viewport.setHeight(height);
            this.viewport.setWidth(width);
            this.chaosGame = new ChaosGame(fractalDescription, start, width, height, viewport);
            return chaosGame.generateFractalSet(iterations, fractalType);
            } catch (FileNotFoundException e) {
                throw new FractalGenerationException(ErrorCode.FAILED_TO_READ_FILE, fractalType, e);
            }
    }


    public List<FractalPoint> getMandelBulbSet() {
        Mandelbulb mandelbulb = new Mandelbulb();
        return mandelbulb.generateFractalSet();
    }

    /**
     * Extracts an integer parameter from the parameter component.
     *
     * @param parameterComponent The parameter component to extract the parameter from.
     * @param parameterName      The name of the parameter to extract.
     * @return The extracted integer parameter.
     */
    private int extractIntParameter(ParameterComponent parameterComponent, String parameterName) {
        try {
            return Integer.parseInt(parameterComponent.getParameterValue(parameterName));
        } catch (NumberFormatException e) {
            // Log error, means the validation of the parameter failed.
            throw new FractalGenerationException(ErrorCode.INVALID_PARAMETER, parameterName, e);
        }
    }

    /**
     * Extracts a double parameter from the parameter component.
     *
     * @param parameterComponent The parameter component to extract the parameter from.
     * @param parameterName      The name of the parameter to extract.
     * @return The extracted double parameter.
     */
    private Double extractDoubleParameter(ParameterComponent parameterComponent, String parameterName) {
        try {
            return Double.parseDouble(parameterComponent.getParameterValue(parameterName));
        } catch (NumberFormatException e) {
            // Log error, means the validation of the parameter failed.
            throw new FractalGenerationException(ErrorCode.INVALID_PARAMETER, parameterName, e);
        }
    }

    public List<Vector3D> updateViewport() {
        return switch (viewport.getFractalName()) {
            case "Mandelbrot Fractal" -> getAffineTransformationSet("Mandelbrot Fractal", parameterComponent, viewport);
            case "Julia Set" -> getAffineTransformationSet("Julia Set", parameterComponent, viewport);
            case "Sierpinski Triangle" ->
                    getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
            case "Barnsley-Fern Fractal" ->
                    getAffineTransformationSet("Barnsley-Fern Fractal", parameterComponent, viewport);
            default -> throw new FractalGenerationException(ErrorCode.INVALID_FRACTAL_NAME, viewport.getFractalName());
        };
    }
}
