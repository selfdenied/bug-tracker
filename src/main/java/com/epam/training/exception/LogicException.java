package com.epam.training.exception;

/**
 * Class {@code LogicException} represents a general Logic exception.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class LogicException extends Exception {
	private static final long serialVersionUID = -139960481813207041L;

	/**
	 * Constructs a LogicException with the given details message.
	 * 
	 * @param message
	 *            The details message of LogicException.
	 */
	public LogicException(String message) {
		super(message);
	}

	/**
	 * Constructs a LogicException with the given root cause.
	 * 
	 * @param cause
	 *            The root cause of LogicException.
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a LogicException with the given details message and root
	 * cause.
	 * 
	 * @param message
	 *            The details message of LogicException.
	 * @param cause
	 *            The root cause of LogicException.
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
