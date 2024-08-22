package edu.ntnu.stud.views.components.parameterComponents;

/**
 * Class for validating the input of the parameters in the application.
 */
public class ParameterValidator {
    private static final int MAX_NUMBER_LENGTH = 1000000;
    private static final int MIN_ITERATIONS = 0;
    private static final int MAX_ITERATIONS = 100000000;
    private static final int MAX_POINT_ITERATIONS = 100;

    /**
     * Validates the input of the double parameter. Limits the input to numbers and a certain range.
     *
     * @param parameter the parameter to validate
     */
    public static void validateDouble(Parameter parameter) {
        String input = parameter.getTextField().getText();
        try {
            double value = Double.parseDouble(input);
            if (value > MAX_NUMBER_LENGTH || value < -MAX_NUMBER_LENGTH) {
                displayInvalidParameter(parameter, "Warning: input number is outside of valid range.");
            } else {
                displayValidParameter(parameter);
            }
            displayValidParameter(parameter);
        } catch (NumberFormatException e) {
            displayInvalidParameter(parameter, "Invalid input: only numbers are allowed.");
        }
    }

    /**
     * Validates the input of the width and height parameters. Limits the width and height to positive numbers and equal values.
     * If the width and height are not equal, the fractal will be distorted.
     *
     * @param parameter the parameter to validate
     */
    public static void validateWidthAndHeight(Parameter parameter) {

        String width = parameter.getParameterPane().getParameterValue("WIDTH");
        String height = parameter.getParameterPane().getParameterValue("HEIGHT");
        if (width.isEmpty() || height.isEmpty()) {
            displayInvalidParameter(parameter, "Input cannot be empty.");
            return;
        }
        try {
            int widthValue = Integer.parseInt(width);
            int heightValue = Integer.parseInt(height);
            if (widthValue < 0 || heightValue < 0) {
                displayInvalidParameter(parameter, "Invalid input: width and height must be positive numbers.");
            } else if(widthValue > 10000 || heightValue > 10000) {
                displayInvalidParameter(parameter, "Warning: high resolution may cause performance issues.");
            } else if (widthValue > heightValue + 400
                    || heightValue > widthValue + 400
                    || widthValue < heightValue - 400
                    || heightValue < widthValue - 400)
            {
                displayInvalidParameter(parameter, "Warning: fractal will look distorted.");
            } else {
                parameter.getParameterPane().getParameter("HEIGHT").getTextField().setStyle("-fx-text-fill: black;");
                parameter.getParameterPane().getParameter("HEIGHT").setNotificationLabel("", "-fx-text-fill: black;");
                parameter.getParameterPane().getParameter("WIDTH").getTextField().setStyle("-fx-text-fill: black;");
                parameter.getParameterPane().getParameter("WIDTH").setNotificationLabel("", "-fx-text-fill: black;");
            }
        } catch (NumberFormatException e) {
            displayInvalidParameter(parameter, "Invalid input: only numbers are allowed.");
        }
    }

    /**
     * Validates the input of the iteration parameter. Limits the number of iterations so the application does not freeze.
     *
     * @param parameter the parameter to validate
     */
    public static void validateIterations(Parameter parameter) {
        String value = parameter.getTextField().getText();
        if (value.isEmpty()) {
            displayInvalidParameter(parameter, "Input cannot be empty.");
            return;
        }
        try {
            int iterations = Integer.parseInt(value);
            if (iterations < MIN_ITERATIONS) {
                displayInvalidParameter(parameter, "Invalid input: iterations must be a positive number.");
            } else if (iterations > MAX_ITERATIONS) {
                displayInvalidParameter(parameter, "Warning: high number of iterations may cause performance issues.");
            } else {
                displayValidParameter(parameter);
            }
        } catch (NumberFormatException e) {
            displayInvalidParameter(parameter, "Invalid input: only numbers are allowed.");
        }
    }

    /**
     * Validates the input of the Starting Point parameter.
     *
     * @param parameter the parameter to validate
     * @param dimension the dimension of the application window
     */
    public static void validateStartingPoint(Parameter parameter, int dimension) {
        String value = parameter.getTextField().getText();
        if (value.isEmpty()) {
            displayInvalidParameter(parameter, "Input cannot be empty.");
            return;
        }
        try {
            int point = Integer.parseInt(value);
            if (point > dimension) {
                displayInvalidParameter(parameter, "Point will be outside application window");
            } else if (point < 0) {
                displayInvalidParameter(parameter, "Point cannot be negative");
            } else {
                displayValidParameter(parameter);
            }
        } catch (NumberFormatException e) {
            displayInvalidParameter(parameter, "Invalid input: only numbers are allowed.");
        }
    }

    /**
     * Validates the input of the Max Iterations parameter.
     *
     * @param parameter the parameter to validate
     */
    public static void validateMaxIterations(Parameter parameter) {
        String value = parameter.getTextField().getText();
        if (value.isEmpty()) {
            displayInvalidParameter(parameter, "Input cannot be empty.");
            return;
        }
        try {
            int pointIterations = Integer.parseInt(value);
            if (pointIterations < MIN_ITERATIONS) {
                displayInvalidParameter(parameter, "Invalid input: iterations must be a positive number.");
            } else if (pointIterations > MAX_POINT_ITERATIONS) {
                displayInvalidParameter(parameter, "Warning: high number of iterations decreases the number of pixels added to the fractal set");
            } else {
                displayValidParameter(parameter);
            }
        } catch (NumberFormatException e) {
            displayInvalidParameter(parameter, "Invalid input: only numbers are allowed.");
        }
    }

    /**
     * Displays the parameter as valid by changing the text color to black and removing the notification label.
     *
     * @param parameter the parameter to display as valid
     */
    private static void displayValidParameter(Parameter parameter) {
        parameter.getTextField().setStyle("-fx-text-fill: black;");
        parameter.setNotificationLabel("", "-fx-text-fill: black;");
    }

    /**
     * Displays the parameter as invalid by changing the text color to red and displaying a notification label.
     *
     * @param parameter the parameter to display as invalid
     * @param message   the message to display in the notification label
     */
    private static void displayInvalidParameter(Parameter parameter, String message) {
        parameter.getTextField().setStyle("-fx-text-fill: red;");
        parameter.setNotificationLabel(message, "-fx-text-fill: red;");
    }
}
