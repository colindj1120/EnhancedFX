package io.github.colindj1120.enhancedfx.utils.exceptions;

import java.io.Serial;

/**
 * Custom exception class for handling errors specifically related to {@code RippleEffect} operations. This class extends {@link RuntimeException} and provides constructors that support various ways
 * of creating an exception instance, including with error messages, causes, and flags for suppression and stack trace writability. It is designed to encapsulate errors that occur within the context
 * of using ripple effects in a UI, allowing for more precise exception handling and debugging.
 *
 * <p>
 * {@code RippleEffectException} can be thrown in scenarios where ripple effect creation, application, or runtime behavior encounters issues. Examples include invalid configuration parameters, failure
 * to apply the effect to a UI component, or runtime conflicts with other UI effects or operations.
 * </p>
 *
 * <p>
 * Usage of this exception class allows for better diagnosis and handling of errors related to ripple effects, facilitating easier debugging and error reporting processes.
 * </p>
 *
 * <p>
 * Example Usage:
 * <pre>
 * try {
 *     applyRippleEffect(targetComponent);
 * } catch (RippleEffectException e) {
 *     log.error("Failed to apply ripple effect", e);
 * }
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RuntimeException
 */
public class RippleEffectException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2972645068075507712L;

    /**
     * Constructs a new ripple effect exception with {@code null} as its detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public RippleEffectException() {
        super();
    }

    /**
     * Constructs a new ripple effect exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public RippleEffectException(String message) {
        super(message);
    }

    /**
     * Constructs a new ripple effect exception with the specified detail message and cause.
     * <p>Note that the detail message associated with {@code cause} is not automatically
     * incorporated into this exception's detail message.</p>
     *
     * @param message
     *         the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RippleEffectException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ripple effect exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message
     * of {@code cause}). This constructor is useful for runtime exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RippleEffectException(Throwable cause) {
        super(cause);
    }
}

