package com.epam.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.training.bean.Entity;
import com.epam.training.exception.GeneralDAOException;

/**
 * Abstract class {@code AbstractDAO} is a generic class that contains methods
 * allowing to operate with a database or data source (extract information,
 * update or add data). The specific implementation of these methods depends
 * on a type of database/data source used (e.g. MySQL database, XML, etc.).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public abstract class AbstractDAO<T extends Entity> {
	private static final Logger LOG = Logger.getLogger(AbstractDAO.class);
	protected Connection connection;

	/**
	 * The constructor is used by sub-classes only
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Returns the list of type T Entity objects (T can be a Member, Project,
	 * Issue, etc.) available in the application. The information is extracted
	 * from a database/data source.
	 * 
	 * @return The list of type T Entity objects (Members, Projects, Builds,
	 *         etc.)
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public abstract List<T> findAll() throws GeneralDAOException;

	/**
	 * Returns an object of type T Entity (T can be a Member, Project, Issue,
	 * etc.) with the given ID. The information is extracted from a
	 * database/data source.
	 * 
	 * @param id
	 *            The id of the Entity (Member, Project, Build, etc.)
	 * @return Type T Entity object (Member, Project, Build, etc.)
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public abstract T findEntityByID(int id) throws GeneralDAOException;

	/**
	 * Adds new object of type T Entity (T can be a Member, Project, Issue,
	 * etc.) to the database/data source.
	 * 
	 * @param entity
	 *            new type T Entity object (Member, Project, Build, etc.)
	 * @return {@code true} if the object is successfully added and
	 *         {@code false} otherwise
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public abstract boolean addNewEntity(T entity) throws GeneralDAOException;

	/**
	 * Updates the data of type T Entity object (T can be a Member, Project,
	 * Issue, etc.) in the database/data source.
	 * 
	 * @param entity
	 *            new type T Entity object (Member, Project, Build, etc.)
	 * @param id
	 *            the ID of type T Entity object to be updated
	 * @return {@code true} if the object's data is successfully updated and
	 *         {@code false} otherwise
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public abstract boolean updateEntity(T entity, int id)
			throws GeneralDAOException;

	/**
	 * Closes the opened statement.
	 * 
	 * @param statement
	 *            java.sql.Statement
	 * @throws GeneralDAOException
	 *             If a there was an error while statement closing.
	 */
	public void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			LOG.error("Error. Unable to close statement!");
		}
	}
}
