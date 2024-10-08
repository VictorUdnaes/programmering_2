package edu.ntnu.stud.model.fractals;

import edu.ntnu.stud.model.interfaces.Fractal2D;
import edu.ntnu.stud.model.linalg.Complex;
import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.model.utils.Mapper;
import edu.ntnu.stud.model.utils.ValidateDimensions;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class for generating a fractal set using the Julia transformation.</p>
 * The Julia transformation is a fractal set generated by iterating through each pixel on the screen
 * and calculating the number of iterations before the point falls outside the escape-radius.
 * the transformation is given by the equation z = z^2 + c, where z is the point and c is the complex number.
 * The class generates a set of vectors representing points in the fractal set within the width/height bounds.
 *
 * @see Fractal2D
 */
public class CoordinateJuliaTransform implements Fractal2D {
    private int width;
    private int height;
    private static final double ESCAPE_RADIUS = 2.0;
    private final int maxIterations;
    private final Complex c; // The complex number to transform with.

    /**
     * <p>Constructor for the CoordinateJuliaTransform class.</p>
     *
     * @param c The complex number to transform with.
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param maxIterations The maximum number of iterations before a point is considered to be in the set.
     */
    public CoordinateJuliaTransform(Complex c, int width, int height, int maxIterations) {
        this.c = c;
        ValidateDimensions.validate(width,height);
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
    }

    /**
     * <p>Generate a fractal set using the Julia transformation.</p>
     * The fractal set is generated by iterating through each pixel on the screen and calculating the number of iterations
     * before the point falls outside the escape-radius. if the point doesn't diverge after maxIterations,
     * it is considered to be in the set.
     *
     * @return A list of points in the fractal set.
     */
    public List<Vector3D> generateFractalSet() {
        ArrayList<Vector3D> points = new ArrayList<>();
        // Loop through each pixel on the screen
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int iteration = getIteration(x, y);
                if (iteration >= 20) { // If the iteration is equal to the max iterations, the point is in the set
                    points.add(new Vector3D(x,y,iteration));
                }
            }
        }
        return points;
    }

    /**
     * <p>Calculate the number of transformation iterations for a given point before it falls outside the escape-radius.</p>
     * The transformation is given by the equation z = z^2 + c, where z is the point and c is the complex number.
     * The point is transformed until it falls outside the escape-radius or the number of iterations reaches the maximum.
     *
     * @param zx The x-coordinate of the point.
     * @param zy The y-coordinate of the point.
     * @return The number of iterations before the point falls outside the escape-radius.
     */
    private int getIteration(double zx, double zy) {
        zx = Mapper.map((float) zx, 0, width, (float) -ESCAPE_RADIUS, (float) ESCAPE_RADIUS);
        zy = Mapper.map((float) zy, 0, height, (float) -ESCAPE_RADIUS, (float) ESCAPE_RADIUS) / 2;

        int iteration = 0;
        while (zx * zx + zy * zy < ESCAPE_RADIUS * ESCAPE_RADIUS && iteration < maxIterations) {
            double xtemp = zx * zx - zy * zy;
            zy = 2 * zx * zy + c.getY();
            zx = xtemp + c.getX();

            iteration++;
        }
        return iteration;
    }
}
