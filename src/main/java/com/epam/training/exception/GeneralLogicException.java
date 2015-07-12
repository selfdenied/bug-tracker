package com.epam.training.exception;

/**
 * Class {@code GeneralLogicException} represents a general Logic exception.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class GeneralLogicException extends Exception {
	private static final long serialVersionUID = -139960481813207041L;

	/**
	 * Constructs a GeneralLogicException with the given details message.
	 * 
	 * @param message
	 *            The details message of GeneralLogicException.
	 */
	public GeneralLogicException(String message) {
		super(message);
	}

	/**
	 * Constructs a GeneralLogicException with the given root cause.
	 * 
	 * @param cause
	 *            The root cause of GeneralLogicException.
	 */
	public GeneralLogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a GeneralLogicException with the given details message and
	 * root cause.
	 * 
	 * @param message
	 *            The details message of GeneralLogicException.
	 * @param cause
	 *            The root cause of GeneralLogicException.
	 */
	public GeneralLogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
