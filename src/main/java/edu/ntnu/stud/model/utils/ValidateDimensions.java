package edu.ntnu.stud.model.utils;

import edu.ntnu.stud.Main;
import edu.ntnu.stud.utils.ErrorCode;
import edu.ntnu.stud.utils.FractalGenerationException;

public class ValidateDimensions {
    public static void validate(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new FractalGenerationException(ErrorCode.INVALID_FRACTAL_DIMENSIONS, "Julia fractal");
        } else if (width > Main.PANE_WIDTH || height > Main.PANE_HEIGHT) {
            throw new FractalGenerationException(ErrorCode.DIMENSIONS_OUT_OF_APPLICATION_BOUNDS, "Julia fractal");
        }
    }
}
