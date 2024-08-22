package edu.ntnu.stud.utils;

public enum ErrorCode {
    CANVAS_OUT_OF_BOUNDS(
            100,
            "CANVAS_OUT_OF_BOUNDS",
            ""
    ),
    FAILED_TO_READ_FILE(
            101,
            "FAILED_TO_READ_FILE",
            "Unexpected error occurred while generating fractal. Try to change the parameters or restart the application."
    ),
    INVALID_PARAMETER(
            102,
            "INVALID_PARAMETER",
            "Invalid parameter value, please enter a valid number."
    ),
    INVALID_FRACTAL_NAME(
            103,
            "INVALID_FRACTAL_NAME",
            "Unexpected error occurred while generating fractal. Try to change the parameters or restart the application."
    ),
    INVALID_FRACTAL_DIMENSIONS(
            103,
            "INVALID_FRACTAL_DIMENSIONS",
            "Invalid fractal dimensions, please enter a valid width and height."
    ),
    DIMENSIONS_OUT_OF_APPLICATION_BOUNDS(
            104,
            "DIMENSIONS_OUT_OF_BOUNDS",
            "The generated fractal will end up outside the application bounds. Please enter a smaller width/height value."
    ),
    PARAMETER_COMPONENT_NULL(
            105,
            "PARAMETER_COMPONENT_NULL",
            "Unexpected error occurred while generating fractal. Try to change the parameters or restart the application."
    );

    private final int id;
    private final String error;
    private final String userMessage;

    /**
     * Constructs an ErrorCode enum with an identifier, error key, and a user-friendly message.
     *
     * @param id The unique identifier for the error.
     * @param error The error key used for internal tracking and logging.
     * @param userMessage The message intended to inform users about the nature of the error.
     */


    ErrorCode(int id, String error, String userMessage) {
        this.id = id;
        this.error = error;
        this.userMessage = userMessage;
    }
    /**
     * Retrieves the identifier of the error.
     *
     * @return The identifier of the error.
     */

    public int getId() {
        return id;
    }

    /**
     * Retrieves the error key associated with this error code.
     *
     * @return The error key.
     */
    public String getError() {
        return error;
    }

    /**
     * Retrieves the user-friendly message associated with this error code.
     *
     * @return The message.
     */
    public String getUserMessage() {
        return userMessage;
    }

}
