package com.epam.training.exception;

/**
 * Class {@code GeneralCommandException} represents a general Command exception.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class GeneralCommandException extends Exception {
	private static final long serialVersionUID = -2115115213686492866L;

	/**
	 * Constructs a GeneralCommandException with the given details message.
	 * 
	 * @param message
	 *            The details message of GeneralCommandException.
	 */
	public GeneralCommandException(String message) {
		super(message);
	}

	/**
	 * Constructs a GeneralCommandException with the given root cause.
	 * 
	 * @param cause
	 *            The root cause of GeneralCommandException.
	 */
	public GeneralCommandException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a GeneralCommandException with the given details message and
	 * root cause.
	 * 
	 * @param message
	 *            The details message of GeneralDAOException.
	 * @param cause
	 *            The root cause of GeneralDAOException.
	 */
	public GeneralCommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
