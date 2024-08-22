package edu.ntnu.stud.model.fractals;

import edu.ntnu.stud.model.interfaces.Fractal2D;
import edu.ntnu.stud.model.linalg.vector.Vector2D;

import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.model.utils.Mapper;
import edu.ntnu.stud.model.utils.ValidateDimensions;

import java.util.ArrayList;
import java.util.List;

public class MandelbrotTransformation implements Fractal2D {
    private int width;
    private int height;
    private static final double ESCAPE_RADIUS = 2;
    private int maxIterations;

    public MandelbrotTransformation(int width, int height, int maxIterations) {
        ValidateDimensions.validate(width, height);
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
    }

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

    private int getIteration(double x, double y) {
        double scaledX = Mapper.map((float) x, 0, width, -2.5f, 1f);
        double scaledY = Mapper.map((float) y, 0, height, -1f, 1f);

        double zx = 0;
        double zy = 0;

        int iteration = 0;
        while (zx * zx + zy * zy < ESCAPE_RADIUS * ESCAPE_RADIUS && iteration < maxIterations)  {
            double xtemp = zx * zx - zy * zy + scaledX;
            zy = 2 * zx * zy + scaledY;
            zx = xtemp;
            iteration++;
        }
        return iteration;
    }
}
