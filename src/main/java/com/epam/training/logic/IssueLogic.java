package com.epam.training.logic;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.training.bean.*;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.dao.mysqldao.MySQLBuildDAO;
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
	 * Method returns the list of all Issues registered in the application.
	 * 
	 * @return The list of all Issues registered in the application
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Issue> allIssueList() throws GeneralLogicException {
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		List<Issue> issuesList = new ArrayList<>();

		try {
			issuesList = issueDAO.findAll();
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return issuesList;
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

	/**
	 * Method sets the lists of Types, Projects, Builds, etc. as the
	 * corresponding attributes. So they can be displayed as select options.
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public void setFieldsToRequest(HttpServletRequest request)
			throws GeneralLogicException {
		connection = pool.getConnection();

		try {
			connection.setAutoCommit(false);
			factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
			request.setAttribute("types", factory.getTypeDAO().findAll());
			request.setAttribute("priorities", factory.getPriorityDAO().findAll());
			request.setAttribute("resolutions", factory.getResolutionDAO().findAll());
			request.setAttribute("projects", factory.getProjectDAO().findAll());
			request.setAttribute("listOfBuilds", orderedBuildsList(factory));
			request.setAttribute("members", factory.getMemberDAO().findAll());
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException | GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
	}
	
	/**
	 * Method adds new Issue to the database.
	 * 
	 * @param Issue
	 *            new Issue
	 * 
	 * @return {@code true} when new Issue was successfully added and
	 *         {@code false} otherwise
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean addNewIssue(Issue issue) throws GeneralLogicException {
		boolean isAdded = false;
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		
		try {
			isAdded = issueDAO.addNewEntity(issue);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isAdded;
	}
	
	/**
	 * Method updates existing Issue data.
	 * 
	 * @param Issue
	 *            new Issue
	 * @param issueID
	 * 			  the ID of the Issue
	 * @return {@code true} when Issue data was successfully updated and
	 *         {@code false} otherwise
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean updateIssue(Issue issue, int issueID) 
			throws GeneralLogicException {
		boolean isUpdated = false;
		AbstractDAO<Issue> issueDAO = initDAOFactory().getIssueDAO();
		
		try {
			isUpdated = issueDAO.updateEntity(issue, issueID);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isUpdated;
	}

	/* supplementary method that initializes connection and DAO factory */
	private AbstractDAOFactory initDAOFactory() {
		connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		return factory;
	}

	/* method returns the list of all builds lists */
	/* each builds list corresponds to the given project */
	private List<? extends List<Build>> orderedBuildsList(
			AbstractDAOFactory factory) throws GeneralDAOException {
		MySQLBuildDAO buildDAO = (MySQLBuildDAO) factory.getBuildDAO();
		List<List<Build>> buildsList = new ArrayList<>();
		List<Project> projectsList = factory.getProjectDAO().findAll();

		for (Project pr : projectsList) {
			List<Build> projectBuilds = buildDAO.findBuildsOfProject(pr.getId());
			buildsList.add(projectBuilds);
		}
		return buildsList;
	}
}
