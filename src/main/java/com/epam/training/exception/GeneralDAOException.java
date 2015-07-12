package com.epam.training.exception;

/**
 * Class {@code GeneralDAOException} represents a general DAO exception. It
 * should wrap any exception of the underlying code, such as SQLExceptions.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class GeneralDAOException extends Exception {
	private static final long serialVersionUID = 4343685078987633577L;

	/**
	 * Constructs a GeneralDAOException with the given details message.
	 * 
	 * @param message
	 *            The details message of GeneralDAOException.
	 */
	public GeneralDAOException(String message) {
		super(message);
	}

	/**
	 * Constructs a GeneralDAOException with the given root cause.
	 * 
	 * @param cause
	 *            The root cause of GeneralDAOException.
	 */
	public GeneralDAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a GeneralDAOException with the given details message and root
	 * cause.
	 * 
	 * @param message
	 *            The details message of GeneralDAOException.
	 * @param cause
	 *            The root cause of GeneralDAOException.
	 */
	public GeneralDAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
