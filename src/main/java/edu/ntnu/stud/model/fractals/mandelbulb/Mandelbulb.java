package edu.ntnu.stud.model.fractals.mandelbulb;

import edu.ntnu.stud.model.linalg.vector.Vector3D;
import edu.ntnu.stud.model.utils.Mapper;
import java.util.List;

import java.util.ArrayList;

/**
 * The Mandelbulb class is responsible for generating the Mandelbulb fractal.
 */
public class Mandelbulb {
    private static final int DIM = 160;
    private static final int MAX_ITERATIONS = 20;
    private static final int SPACING = 15;

    /**
     * <p>Generates a set of points that represent the Mandelbulb fractal.</p>
     *
     * The method iterates over a 3D grid of points, each point is transformed into a complex number and then
     * iteratively transformed according to the 3D mandelbrot fractal formula z^n + c. The iteration stops if the magnitude
     * of the complex number exceeds 2 (indicating that the point is not part of the Mandelbulb set) or if the
     * maximum number of iterations is reached.
     *
     * If a point is determined to be on the edge of the Mandelbulb set (i.e., it was still within the set after
     * the maximum number of iterations), it is added to the list of points to be returned.
     *
     * @return A list of FractalPoint objects representing points on the edge of the Mandelbulb set. Each FractalPoint
     *         contains a 3D position (x, y, z) and a radius value.
     */
    public List<FractalPoint> generateFractalSet() {
        List<FractalPoint> points = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                boolean edge = false;
                for (int k = 0; k < DIM; k++) {
                    float x = Mapper.map(i, 0, DIM, -1, 1);
                    float y = Mapper.map(j, 0, DIM, -1, 1);
                    float z = Mapper.map(k, 0, DIM, -1, 1);
                    Vector3D zeta = new Vector3D(0, 0, 0);

                    int n = 8;
                    int iterations = 0;

                    while (true) {
                        Spherical spherical_z = cartesianToSpherical(zeta.getX(), zeta.getY(), zeta.getZ());

                        double new_x = Math.pow(spherical_z.r(), n) * Math.sin(spherical_z.theta() * n) * Math.cos(spherical_z.phi() * n);
                        double new_y = Math.pow(spherical_z.r(), n) * Math.sin(spherical_z.theta() * n) * Math.sin(spherical_z.phi() * n);
                        double new_z = Math.pow(spherical_z.r(), n) * Math.cos(spherical_z.theta() * n);

                        zeta.setX(new_x + x);
                        zeta.setY(new_y + y);
                        zeta.setZ(new_z + z);
                        iterations++;

                        if (spherical_z.r() > 2) {
                            if (edge) {
                                edge = false;
                            }
                            break;
                        }
                        if (iterations > MAX_ITERATIONS) {
                            if (!edge) {
                                edge = true;
                                points.add(new FractalPoint(
                                    new Vector3D(x*SPACING, y*SPACING, z*SPACING),
                                    spherical_z.r()
                                ));
                            }
                            break;
                        }
                    }
                }
            }
        }
        return points;
    }

    /**
     * Converts cartesian coordinates to spherical coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param z The z-coordinate.
     * @return The spherical coordinates.
     */
    public Spherical cartesianToSpherical(double x, double y, double z) {
        double r = Math.sqrt(x * x + y * y + z * z);
        double theta = Math.atan2(Math.sqrt(x*x + y*y), z);
        double phi = Math.atan2(y, x);
        return new Spherical(r, theta, phi);
    }
}
