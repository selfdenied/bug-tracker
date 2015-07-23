package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Build;
import com.epam.training.bean.Project;

import com.epam.training.dao.AbstractDAO;
import com.epam.training.exception.GeneralDAOException;

/**
 * Class {@code MySQLBuildDAO} contains methods allowing to extract information
 * about Builds of the Projects found in the application, and add new Builds.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Build
 * @see com.epam.training.dao.AbstractDAO
 */
public class MySQLBuildDAO extends AbstractDAO<Build> {
	private static final String BUILD_ID = "BUILD_ID";
	private static final String BUILD_NAME = "BUILD_NAME";
	private static final String BUILD_PROJECT = "PROJECT_ID";
	private static final String FIND_BUILDS = "SELECT * FROM BUILD";
	private static final String FIND_BUILDS_BY_PROJECT_ID = "SELECT * FROM BUILD WHERE PROJECT_ID = ?";
	private static final String FIND_BUILD_BY_ID = "SELECT * FROM BUILD WHERE BUILD_ID = ?";
	private static final String ADD_BUILD = "INSERT INTO BUILD (BUILD_NAME, PROJECT_ID) VALUES (?, ?)";

	/**
	 * Constructs MySQLBuildDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLBuildDAO(Connection connection) {
		super(connection);
	}

	/* finds all Builds in the application */
	@Override
	public List<Build> findAll() throws GeneralDAOException {
		AbstractDAO<Project> projectDAO = new MySQLProjectDAO(connection);
		List<Build> buildsList = new ArrayList<Build>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_BUILDS);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Build and initializing its fields */
				Build build = new Build();
				build.setId(rs.getInt(BUILD_ID));
				build.setBuildName(rs.getString(BUILD_NAME));
				build.setProject(projectDAO.findEntityByID(rs.getInt(BUILD_PROJECT)));
				buildsList.add(build);
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return buildsList;
	}

	/* returns Build with the given ID */
	@Override
	public Build findEntityByID(int id) throws GeneralDAOException {
		AbstractDAO<Project> projectDAO = new MySQLProjectDAO(connection);
		Build build = null;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_BUILD_BY_ID);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Build and initializing its fields */
				build = new Build();
				build.setId(rs.getInt(BUILD_ID));
				build.setBuildName(rs.getString(BUILD_NAME));
				build.setProject(projectDAO.findEntityByID(rs.getInt(BUILD_PROJECT)));
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return build;
	}
	
	/**
	 * Returns the list of Builds for a given Project.
	 * 
	 * @param id
	 *            The ID of the Project
	 * @return The list of Build for a given Project
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public List<Build> findBuildsOfProject(int id) throws GeneralDAOException {
		AbstractDAO<Project> projectDAO = new MySQLProjectDAO(connection);
		List<Build> buildsList = new ArrayList<Build>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_BUILDS_BY_PROJECT_ID);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Build and initializing its fields */
				Build build = new Build();
				build.setId(rs.getInt(BUILD_ID));
				build.setBuildName(rs.getString(BUILD_NAME));
				build.setProject(projectDAO.findEntityByID(rs.getInt(BUILD_PROJECT)));
				buildsList.add(build);
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return buildsList;
	}

	/* adds new Build to the database */
	@Override
	public boolean addNewEntity(Build build) throws GeneralDAOException {
		boolean isAdded = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(ADD_BUILD);
			prepStatement.setString(1, build.getBuildName());
			prepStatement.setInt(2, build.getProject().getId());
			prepStatement.executeUpdate();
			isAdded = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}		
		return isAdded;
	}

	/* there is no need in such method in the application */
	@Override
	public boolean updateEntity(Build build, int id) {
		throw new UnsupportedOperationException("Error. This operation is not supported!");
	}
}
