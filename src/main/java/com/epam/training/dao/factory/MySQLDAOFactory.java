package com.epam.training.dao.factory;

import java.sql.Connection;

import com.epam.training.bean.*;
import com.epam.training.dao.mysqldao.*;
import com.epam.training.dao.AbstractDAO;

/**
 * Class {@code MySQLDAOFactory} contains methods that create MySQLDAO entities
 * (such as MySQLMemberDAO, MySQLIssueDAO, MySQLProjectDAO, MySQLBuildDAO,
 * MySQLStatusDAO, MySQLTypeDAO, MySQLPriorityDAO, and MySQLResolutionDAO). I.e.
 * this is a specific implementation of AbstractDAOFactory that generates
 * classes that allow to work with local MySQL database.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.dao.factory.AbstractDAOFactory
 */
public class MySQLDAOFactory extends AbstractDAOFactory {
	private Connection connection;

	/**
	 * Constructs MySQLDAOFactory with a DB connection object as a parameter.
	 * 
	 * @param connection	java.sql.Connection
	 */
	public MySQLDAOFactory(Connection connection) {
		this.connection = connection;
	}

	@Override
	public AbstractDAO<Member> getMemberDAO() {
		return new MySQLMemberDAO(connection);
	}

	@Override
	public AbstractDAO<Issue> getIssueDAO() {
		return new MySQLIssueDAO(connection);
	}

	@Override
	public AbstractDAO<Project> getProjectDAO() {
		return new MySQLProjectDAO(connection);
	}

	@Override
	public AbstractDAO<Build> getBuildDAO() {
		return new MySQLBuildDAO(connection);
	}

	@Override
	public AbstractDAO<Feature> getStatusDAO() {
		return new MySQLStatusDAO(connection);
	}

	@Override
	public AbstractDAO<Feature> getTypeDAO() {
		return new MySQLTypeDAO(connection);
	}

	@Override
	public AbstractDAO<Feature> getPriorityDAO() {
		return new MySQLPriorityDAO(connection);
	}

	@Override
	public AbstractDAO<Feature> getResolutionDAO() {
		return new MySQLResolutionDAO(connection);
	}
}
