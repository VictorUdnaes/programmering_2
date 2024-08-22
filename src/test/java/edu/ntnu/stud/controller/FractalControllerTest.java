package edu.ntnu.stud.controller;

import edu.ntnu.stud.model.chaosGame.Viewport;
import edu.ntnu.stud.views.components.parameterComponents.ParameterComponent;
import edu.ntnu.stud.utils.FractalGenerationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FractalControllerTest {
    private FractalController fractalController;
    private ParameterComponent parameterComponent;


    /**
     * Setup the test environment by creating mock objects.
     */
    @BeforeEach
    public void setup() {
        fractalController = new FractalController();
    }

    /**
     * Positive test cases for the FractalController class.
     * The class
     */
    @Nested
    class positiveTests {
        /**
         * Setup the test environment by creating mock objects.
         * The mock objects are used to test the positive test cases.
         */
        @BeforeEach
        public void setup() {
            parameterComponent = mock(ParameterComponent.class);
            when(parameterComponent.getParameterValue("WIDTH")).thenReturn("100");
            when(parameterComponent.getParameterValue("HEIGHT")).thenReturn("100");
            when(parameterComponent.getParameterValue("MAX ITERATIONS")).thenReturn("100");
            when(parameterComponent.getParameterValue("REAL PART OF C")).thenReturn("0.1");
            when(parameterComponent.getParameterValue("IMAGINARY PART OF C")).thenReturn("0.1");
            when(parameterComponent.getParameterValue("ITERATIONS")).thenReturn("1000000");
            when(parameterComponent.getParameterValue("STARTING POINT X")).thenReturn("0");
            when(parameterComponent.getParameterValue("STARTING POINT Y")).thenReturn("0");
        }

        /**
         * Test the width parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the width parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"100", "200", "300"})
        public void testHeight(String value) {
            when(parameterComponent.getParameterValue("HEIGHT")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
                fractalController.getMandelbrotSet(parameterComponent);
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the height parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the height parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"100", "200", "300"})
        public void testMaxIterations(String value) {
            when(parameterComponent.getParameterValue("MAX ITERATIONS")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
                fractalController.getMandelbrotSet(parameterComponent);
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the real part of c parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the real part of c parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"0.1", "0.2", "0.3"})
        public void testRealPartOfC(String value) {
            when(parameterComponent.getParameterValue("REAL PART OF C")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
            });
        }

        /**
         * Test the imaginary part of c parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the imaginary part of c parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"0.1", "0.2", "0.3"})
        public void testImaginaryPartOfC(String value) {
            when(parameterComponent.getParameterValue("IMAGINARY PART OF C")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
            });
        }

        /**
         * Test the iterations parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the iterations parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"1000000", "2000000", "3000000"})
        public void testIterations(String value) {
            when(parameterComponent.getParameterValue("ITERATIONS")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the starting point x parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the starting point x parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"0", "1", "2"})
        public void testStartingPointX(String value) {
            when(parameterComponent.getParameterValue("STARTING POINT X")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the starting point y parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods do not throw an exception when the starting point y parameter is valid.
         */
        @ParameterizedTest
        @ValueSource(strings = {"0", "1", "2"})
        public void testStartingPointY(String value) {
            when(parameterComponent.getParameterValue("STARTING POINT Y")).thenReturn(value);
            assertDoesNotThrow(() -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Tests the mandelbulb method of the FractalController class.
         * Asserts that the mandelbulb method does not throw an exception.
         */
        @Test
        public void testMandelbulb() {
            assertDoesNotThrow(() -> {
                fractalController.getMandelBulbSet();
            });
        }
    }

    /**
     * Negative test cases for the FractalController class.
     * The class tests the negative test cases for the FractalController class.
     */
    @Nested
    class NegativeTests {
        /**
         * Setup the test environment by creating mock objects.
         * The mock objects are used to test the negative test cases.
         */
        @BeforeEach
        public void setup() {
            parameterComponent = mock(ParameterComponent.class);
            when(parameterComponent.getParameterValue("WIDTH")).thenReturn("100");
            when(parameterComponent.getParameterValue("HEIGHT")).thenReturn("100");
            when(parameterComponent.getParameterValue("MAX ITERATIONS")).thenReturn("100");
            when(parameterComponent.getParameterValue("REAL PART OF C")).thenReturn("0.1");
            when(parameterComponent.getParameterValue("IMAGINARY PART OF C")).thenReturn("0.1");
            when(parameterComponent.getParameterValue("ITERATIONS")).thenReturn("1000000");
            when(parameterComponent.getParameterValue("STARTING POINT X")).thenReturn("0");
            when(parameterComponent.getParameterValue("STARTING POINT Y")).thenReturn("0");
            fractalController = new FractalController();
        }

        /**
         * Test the width parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the width parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testWidth(String value) {
            when(parameterComponent.getParameterValue("WIDTH")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
                fractalController.getMandelbrotSet(parameterComponent);
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the height parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the height parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testHeight(String value) {
            when(parameterComponent.getParameterValue("HEIGHT")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
                fractalController.getMandelbrotSet(parameterComponent);
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the max iterations parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the max iterations parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testMaxIterations(String value) {
            when(parameterComponent.getParameterValue("MAX ITERATIONS")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getJuliaSet(parameterComponent);
                fractalController.getMandelbrotSet(parameterComponent);
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the real part of c parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the real part of c parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "....", "abc"})
        public void testRealPartOfC(String value) {
            when(parameterComponent.getParameterValue("REAL PART OF C")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                fractalController.getJuliaSet(parameterComponent);
            });
        }

        /**
         * Test the imaginary part of c parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the imaginary part of c parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "....", "abc"})
        public void testImaginaryPartOfC(String value) {
            when(parameterComponent.getParameterValue("IMAGINARY PART OF C")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                fractalController.getJuliaSet(parameterComponent);
            });
        }

        /**
         * Test the iterations parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the iterations parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testIterations(String value) {
            when(parameterComponent.getParameterValue("ITERATIONS")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the starting point x parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the starting point x parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testStartingPointX(String value) {
            when(parameterComponent.getParameterValue("STARTING POINT X")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }

        /**
         * Test the starting point y parameter of the fractal for each of the getFractalSet methods.
         * Asserts that the getFractalSet methods throw an exception when the starting point y parameter is invalid.
         */
        @ParameterizedTest
        @ValueSource(strings = {" ", "0.5", "abc"})
        public void testStartingPointY(String value) {
            when(parameterComponent.getParameterValue("STARTING POINT Y")).thenReturn(value);
            assertThrows(FractalGenerationException.class, () -> {
                Viewport viewport = new Viewport(0, 0, 1000, 1000, "FractalName");
                fractalController.getAffineTransformationSet("Sierpinski Triangle", parameterComponent, viewport);
                fractalController.getAffineTransformationSet("Barnsley Fern", parameterComponent, viewport);
            });
        }
    }
}
