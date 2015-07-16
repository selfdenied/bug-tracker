package com.epam.training.logic;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Issue;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.exception.GeneralDAOException;
import com.epam.training.exception.GeneralLogicException;

/**
 * Class {@code IssueLogic} contains various methods that use DAO layer to
 * retrieve information about the Issue(s) from a database, add or update Issue
 * data, etc. These methods will be further used by Command layer.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class IssueLogic {
	private static final int ISSUES_NUMBER = 10;
	private ConnectionPool pool;
	private Connection connection;
	private AbstractDAOFactory factory;

	/**
	 * Constructs IssueLogic object.
	 */
	public IssueLogic() {
		pool = ConnectionPool.getInstance();
	}

	/**
	 * Method returns the list of recent (up to 10) Issues registered in the
	 * application.
	 * 
	 * @return The list of recent (up to 10) Issues registered in the
	 *         application
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Issue> recentIssuesList() throws GeneralLogicException {
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		List<Issue> issuesList = new ArrayList<>();
		
		try {
			issuesList = issueDAO.findAll();
			if (issuesList.size() > ISSUES_NUMBER) {
				issuesList = issuesList.subList(0, ISSUES_NUMBER);
			}
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return issuesList;
	}

	/**
	 * Method returns the Issue with the given ID.
	 * 
	 * @param issueID
	 *            the ID of the Issue
	 * @return The Issue with the given ID
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public Issue issueToView(int issueID) throws GeneralLogicException {
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		Issue issueToView = null;
		
		try {
			issueToView = issueDAO.findEntityByID(issueID);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return issueToView;
	}

	/**
	 * Method returns the list of Issues assigned to a Member with the given ID.
	 * 
	 * @param id
	 *            Member's ID
	 * @return The list of Issues assigned to a given Member
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Issue> assignedIssues(int id) throws GeneralLogicException {
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		List<Issue> issuesList = new ArrayList<>();

		try {
			List<Issue> tempList = issueDAO.findAll();
			for (Issue issue : tempList) {
				if (issue.getAssignee() != null
						&& issue.getAssignee().getId() == id) {
					issuesList.add(issue);
				}
			}
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return issuesList;
	}

	/* supplementary method that initializes connection and DAO factory */
	private AbstractDAOFactory initDAOFactory() {
		connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		return factory;
	}
}
