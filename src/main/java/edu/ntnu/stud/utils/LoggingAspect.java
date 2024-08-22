package edu.ntnu.stud.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Aspect for logging Excetions in Application.
 * The aspect intercepts all public methods if they throw an exception and logs the exception message in the console.
 */
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut for all public methods in the stud package.
     * Specifies that the aspect should intercept all public methods in the services package.
     */
    @Pointcut("execution(public * edu.ntnu.stud..*.*(..))")
    private void publicMethodsFromLoggingPackage() {
    }

    /**
     * Advice for logging exceptions.
     * Logs the exception message.
     *
     * @param exception the exception
     */
    @AfterThrowing(value = "publicMethodsFromLoggingPackage()", throwing = "exception")
    public void logException(Exception exception) {
        try {
            if (exception instanceof FractalGenerationException) {
                FractalGenerationException fractalGenerationException = (FractalGenerationException) exception;
                logger.error("FractalGenerationException thrown: {}", fractalGenerationException.getErrorCodeMessage() + "\n"
                        + " - ID: " + fractalGenerationException.getErrorCodeId() + "\n"
                        + " - OBJECT_NAME: " + fractalGenerationException.getObjectName() + "\n"
                        + " - EXCEPTION: " + fractalGenerationException.getMessage()
                );
            } else {
                logger.error("Exception thrown: {}", exception.toString());
            }
            logger.error("Exception thrown: {}", exception.toString());
        } catch (Exception e) {
            System.out.println("Logging aspect failed to log exception: " + e.getMessage());
        }
    }
}


