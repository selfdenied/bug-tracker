package com.epam.training.dao;

/**
 * Class {@code DAOException} represents a general DAO exception. It should wrap
 * any exception of the underlying code, such as SQLExceptions.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = 4343685078987633577L;

	/**
	 * Constructs a DAOException with the given details message.
	 * 
	 * @param message
	 *            The details message of DAOException.
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Constructs a DAOException with the given root cause.
	 * 
	 * @param cause
	 *            The root cause of DAOException.
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a DAOException with the given details message and root cause.
	 * 
	 * @param message
	 *            The details message of DAOException.
	 * @param cause
	 *            The root cause of DAOException.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
