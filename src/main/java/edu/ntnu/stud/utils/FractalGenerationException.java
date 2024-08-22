package edu.ntnu.stud.utils;

/**
 * A specialized runtime exception that handles errors related to service operations.
 * It incorporates an ErrorCode to specify the nature of the error,
 * and optionally includes an associated object's name or ID and an underlying exception
 * for detailed context.
 */
public class FractalGenerationException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Exception exception;
    private final String objectName;
    private final Long id;

    /**
     * Constructs a ServiceException for operations associated with an object's name.
     * This constructor is typically used when the error is related to operations where object names are more relevant.
     *
     * @param errorCode The specific ErrorCode associated with this exception.
     * @param objectName The name of the object involved in the exception.
     * @param exception The underlying exception (optional). If not provided, a default Exception is created.
     */

    public FractalGenerationException(ErrorCode errorCode, String objectName, Exception... exception) {
        this.errorCode = errorCode;
        this.objectName = objectName;
        this.id = 0L;
        this.exception = exception.length > 0 ? exception[0] : new Exception();
        print();
    }

    /**
     * Constructs a ServiceException for operations associated with an object's ID.
     * This constructor is suitable for errors related to specific database entries
     * identified by an ID.
     *
     * @param errorCode The specific ErrorCode associated with this exception.
     * @param id The ID of the object involved in the exception.
     * @param exception The underlying exception (optional). If not provided, a default Exception is created.
     */
    public FractalGenerationException(ErrorCode errorCode, Long id, Exception... exception) {
        this.errorCode = errorCode;
        this.id = id;
        this.objectName = "";
        this.exception = exception.length > 0 ? exception[0] : new Exception();
        print();
    }

    /**
     * Prints the error code, message, and associated details to the console.
     */

    private void print() {
        System.out.println("[ERROR] - " + errorCode.getError() + " - id: " + errorCode.getId() + " - " + errorCode.getUserMessage());
        System.out.println(exception.getMessage() != null? "Exception: " + exception.getMessage(): "No internal exception found.");
        System.out.println(id > 0 ? "Id: " + id : "ObjectName: " + objectName);
    }

    /**
     * Returns the error code's unique identifier.
     *
     * @return The ID of the error code.
     */
    public int getErrorCodeId() {
        return errorCode.getId();
    }

    /**
     * Returns the message associated with the error code.
     *
     * @return The user message of the error code.
     */
    public String getErrorCodeMessage() {
        return errorCode.getError();
    }

    /**
     * Returns the user-friendly message associated with the error code.
     *
     * @return The user message of the error code.
     */
    public String getUserMessage() {
        return errorCode.getUserMessage();
    }

    /**
     * Returns the name of the object associated with this exception.
     *
     * @return The name of the object, if any; otherwise, an empty string.
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Returns the ID of the object associated with this exception.
     *
     * @return The ID of the object, if any; otherwise, 0.
     */
    public Long getId() {
        return id;
    }
}

