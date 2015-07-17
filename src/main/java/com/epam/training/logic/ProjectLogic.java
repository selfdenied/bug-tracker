package com.epam.training.logic;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Build;
import com.epam.training.bean.Project;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.dao.mysqldao.MySQLBuildDAO;
import com.epam.training.exception.GeneralDAOException;
import com.epam.training.exception.GeneralLogicException;

/**
 * Class {@code ProjectLogic} contains various methods that use DAO layer to
 * retrieve information about the Project(s) from a database, add or update
 * Project data, etc. These methods will be further used by Command layer.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class ProjectLogic {
	private ConnectionPool pool;
	private Connection connection;
	private AbstractDAOFactory factory;

	/**
	 * Constructs IssueLogic object.
	 */
	public ProjectLogic() {
		pool = ConnectionPool.getInstance();
	}

	/**
	 * Method returns the list of Projects registered in the application.
	 * 
	 * @return The list of Projects registered in the application
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Project> projectsList() throws GeneralLogicException {
		AbstractDAO<Project> projectDAO = initDAOFactory().getProjectDAO();
		List<Project> projectsList = new ArrayList<>();

		try {
			projectsList = projectDAO.findAll();
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return projectsList;
	}

	/**
	 * Method returns the list of Builds of the given Project.
	 * 
	 * @param projectID
	 *            the ID of the given Project
	 * @return The list of Builds
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Build> buildsList(int projectID) throws GeneralLogicException {
		MySQLBuildDAO buildDAO = (MySQLBuildDAO) initDAOFactory().getBuildDAO();
		List<Build> buildsList = new ArrayList<>();

		try {
			buildsList = buildDAO.findBuildsOfProject(projectID);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return buildsList;
	}

	/**
	 * Method adds new Build to the database.
	 * 
	 * @param Build
	 *            new Build
	 * 
	 * @return {@code true} when new Build was successfully added and
	 *         {@code false} otherwise
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean addNewBuild(Build build) throws GeneralLogicException {
		boolean isAdded = false;
		AbstractDAO<Build> buildDAO = initDAOFactory().getBuildDAO();

		try {
			isAdded = buildDAO.addNewEntity(build);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isAdded;
	}

	/**
	 * Method adds new Project to the database.
	 * 
	 * @param Project
	 *            new Project
	 * 
	 * @return {@code true} when new Project was successfully added and
	 *         {@code false} otherwise
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean addNewProject(Project project) throws GeneralLogicException {
		boolean isAdded = false;
		AbstractDAO<Project> projectDAO = initDAOFactory().getProjectDAO();

		try {
			isAdded = projectDAO.addNewEntity(project);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isAdded;
	}

	/**
	 * Method updates existing Project data.
	 * 
	 * @param Project
	 *            new Project
	 * @param projectID
	 *            the ID of the Project
	 * 
	 * @return {@code true} when new Project data was successfully updated and
	 *         {@code false} otherwise
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean updateProject(Project project, int projectID)
			throws GeneralLogicException {
		boolean isUpdated = false;
		AbstractDAO<Project> projectDAO = initDAOFactory().getProjectDAO();

		try {
			isUpdated = projectDAO.updateEntity(project, projectID);
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isUpdated;
	}

	/**
	 * Method find the Project's ID by its name.
	 * 
	 * @param projectName
	 *            the name of the Project
	 * 
	 * @return the Project's ID
	 * @throws GeneralLogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public int findProjectID(String projectName) throws GeneralLogicException {
		int projectID = 0;
		AbstractDAO<Project> projectDAO = initDAOFactory().getProjectDAO();
		List<Project> projectsList = new ArrayList<>();

		try {
			projectsList = projectDAO.findAll();
			for (Project pr : projectsList) {
				if (pr.getProjectName().equals(projectName)) {
					projectID = pr.getId();
				}
			}
		} catch (GeneralDAOException ex) {
			throw new GeneralLogicException("Database access error", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return projectID;
	}

	/* supplementary method that initializes connection and DAO factory */
	private AbstractDAOFactory initDAOFactory() {
		connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		return factory;
	}
}
