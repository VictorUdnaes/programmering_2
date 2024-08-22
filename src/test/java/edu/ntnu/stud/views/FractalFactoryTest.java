package edu.ntnu.stud.views;

import edu.ntnu.stud.controller.FractalControllerTest;
import edu.ntnu.stud.utils.FractalGenerationException;
import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.views.views.FractalFactory;
import edu.ntnu.stud.controller.FractalController;
import edu.ntnu.stud.model.fractals.mandelbulb.FractalPoint;
import edu.ntnu.stud.model.linalg.vector.Vector3D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for the FractalFactory class.
 * Asserts that the FractalFactory class can generate the different fractals without throwing an exception.
 */
public class FractalFactoryTest {

    /**
     * Test class for the FractalFactory class.
     * Asserts that the FractalFactory class can generate the different fractals without throwing an exception.
     */
    @Nested
    class PositiveTests {
        @Test
        void testCreateMandelbulbFractal() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            FractalController fractalController = mock(FractalController.class);
            List<FractalPoint> mandelBulbSet = new ArrayList<>();
            mandelBulbSet.add(new FractalPoint(new Vector3D(1, 2, 3), 1));
            when(fractalController.getMandelBulbSet()).thenReturn(mandelBulbSet);
            assertDoesNotThrow(fractalFactory::createMandelbulbSmartGroup);
        }

        /**
         * Test that the createMandelbrotFractal method does not throw an exception.
         */
        @Test
        void testCreateMandelbrotFractal() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            FractalController fractalController = mock(FractalController.class);
            ParameterComponent pc = mock(ParameterComponent.class);
            List<Vector3D> mandelbrotSet = new ArrayList<>();
            mandelbrotSet.add(new Vector3D(1, 2, 3));
            when(fractalController.getMandelbrotSet(pc)).thenReturn(mandelbrotSet);
            assertDoesNotThrow(() -> fractalFactory.createMandelbrotFractal(pc));
        }

        /**
         * Test that the createCoordinateJuliaFractal method does not throw an exception.
         */
        @Test
        void testCreateCoordinateJuliaFractal() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            FractalController fractalController = mock(FractalController.class);
            ParameterComponent pc = mock(ParameterComponent.class);
            List<Vector3D> juliaSet = new ArrayList<>();
            juliaSet.add(new Vector3D(1, 2, 3));
            when(fractalController.getJuliaSet(pc)).thenReturn(juliaSet);
            assertDoesNotThrow(() -> fractalFactory.createCoordinateJuliaFractal(pc));
        }

        /**
         * Test that the createAffineFractal method does not throw an exception.
         */
        @Test
        void testCreateAffineFractal() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            FractalController fractalController = mock(FractalController.class);
            ParameterComponent pc = mock(ParameterComponent.class);
            List<Vector3D> affineSet = new ArrayList<>();
            affineSet.add(new Vector3D(1, 2, 3));
            when(fractalController.getAffineTransformationSet("Sierpinski Triangle", pc, null)).thenReturn(affineSet);
            assertDoesNotThrow(() -> fractalFactory.createAffineFractal("Sierpinski Triangle", pc, null));
        }

        /**
         * Test that the createCanvas method does not throw an exception.
         */
        @Test
        void testCreateCanvas() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            List<Vector3D> points = new ArrayList<>();
            points.add(new Vector3D(1, 2, 3));
            assertDoesNotThrow(() -> fractalFactory.createCanvas(points, "Mandelbrot", "100"));
        }

        /**
         * Test that the getColorForFractalPoint method does not throw an exception.
         */
        @Test
        void testUpdateViewport() {
            FractalFactory fractalFactory = mock(FractalFactory.class);
            FractalController fractalController = mock(FractalController.class);
            List<Vector3D> points = new ArrayList<>();
            points.add(new Vector3D(1, 2, 3));
            when(fractalController.updateViewport()).thenReturn(points);
            assertDoesNotThrow(() -> fractalFactory.updateViewport("Mandelbrot"));
        }
    }
    /**
     * Test class for the FractalFactory class.
     * Asserts that the FractalFactory class throws an exception when trying to generate a fractal with null parameters.
     */
    @Nested
    class NegativeTests {
        /**
         * Test that the createMandelbulbFractal method throws an exception when trying to generate a Mandelbulb fractal with null parameters.
         */
        @Test
        void testCreateMandelbrotFractal() {
            FractalFactory fractalFactory = new FractalFactory(100, 100, new FractalController());
            ParameterComponent pc = mock(ParameterComponent.class);
            assertThrows(FractalGenerationException.class, () -> fractalFactory.createMandelbrotFractal(null));
        }

        /**
         * Test that the createCoordinateJuliaFractal method throws an exception when trying to generate a Julia fractal with null parameters.
         */
        @Test
        void testCreateCoordinateJuliaFractal() {
            FractalFactory fractalFactory = new FractalFactory(100, 100, new FractalController());
            assertThrows(FractalGenerationException.class, () -> fractalFactory.createCoordinateJuliaFractal(null));
        }

        /**
         * Test that the createAffineFractal method throws an exception when trying to generate an affine fractal with null parameters.
         */
        @Test
        void testCreateAffineFractal() {
            FractalFactory fractalFactory = new FractalFactory(100, 100, new FractalController());
            ParameterComponent pc = mock(ParameterComponent.class);
            assertThrows(FractalGenerationException.class, () -> fractalFactory.createAffineFractal(null, pc, null));
        }

        /**
         * Test that the createCanvas method throws an exception when trying to generate a canvas with null parameters.
         */
        @Test
        void testCreateCanvas() {
            FractalFactory fractalFactory = new FractalFactory(100, 100, new FractalController());
            assertThrows(FractalGenerationException.class, () -> fractalFactory.createCanvas(null, "Mandelbrot", "100"));
        }

        /**
         * Test that the updateViewport method throws an exception when trying to update the viewport with null parameters.
         */
        @Test
        void testUpdateViewport() {
            FractalFactory fractalFactory = new FractalFactory(100, 100, new FractalController());
            assertThrows(FractalGenerationException.class, () -> fractalFactory.updateViewport(null));
        }
    }
}
