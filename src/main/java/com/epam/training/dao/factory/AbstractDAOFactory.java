package com.epam.training.dao.factory;

import java.sql.Connection;

import com.epam.training.bean.*;
import com.epam.training.dao.AbstractDAO;

/**
 * Abstract class {@code AbstractDAOFactory} contains abstract methods that
 * should create DAO entities (such as MemberDAO, IssueDAO, ProjectDAO,
 * BuildDAO, etc.). Specific implementations (i.e. specific variants of DAO
 * objects that allow to work with a given database or data source) are
 * presented by the sub-classes. The class also contains method that returns
 * different types of DAOFactory sub-classes.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public abstract class AbstractDAOFactory {

	/**
	 * Returns the instance of DAOFactory depending on a type of database or
	 * data source used in the application.
	 * 
	 * @param factoryType
	 *            the type of DAOFactory
	 * @param connection
	 *            java.sql.Connection
	 * @return The instance of DAOFactory (a sub-class of AbstractDAOFactory)
	 */
	public static AbstractDAOFactory getDAOFactory(Connection connection,
			DAOFactoryType factoryType) {
		switch (factoryType) {
		case MYSQL:
			return new MySQLDAOFactory(connection);
		default:
			throw new EnumConstantNotPresentException(DAOFactoryType.class,
					factoryType.name());
		}
	}

	/**
	 * Returns the MemberDAO instance. The specific implementation of MemberDAO
	 * class depends on the type of DAOFactory
	 * 
	 * @return The MemberDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Member> getMemberDAO();

	/**
	 * Returns the IssueDAO instance. The specific implementation of IssueDAO
	 * class depends on the type of DAOFactory
	 * 
	 * @return The IssueDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Issue> getIssueDAO();

	/**
	 * Returns the ProjectDAO instance. The specific implementation of
	 * ProjectDAO class depends on the type of DAOFactory
	 * 
	 * @return The ProjectDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Project> getProjectDAO();

	/**
	 * Returns the BuildDAO instance. The specific implementation of BuildDAO
	 * class depends on the type of DAOFactory
	 * 
	 * @return The BuildDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Build> getBuildDAO();

	/**
	 * Returns the StatusDAO instance. The specific implementation of StatusDAO
	 * class depends on the type of DAOFactory
	 * 
	 * @return The StatusDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Feature> getStatusDAO();

	/**
	 * Returns the TypeDAO instance. The specific implementation of TypeDAO
	 * class depends on the type of DAOFactory
	 * 
	 * @return The TypeDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Feature> getTypeDAO();

	/**
	 * Returns the PriorityDAO instance. The specific implementation of
	 * PriorityDAO class depends on the type of DAOFactory
	 * 
	 * @return The PriorityDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Feature> getPriorityDAO();

	/**
	 * Returns the ResolutionDAO instance. The specific implementation of
	 * ResolutionDAO class depends on the type of DAOFactory
	 * 
	 * @return The ResolutionDAO instance (a sub-class of AbstractDAO)
	 */
	public abstract AbstractDAO<Feature> getResolutionDAO();
}
