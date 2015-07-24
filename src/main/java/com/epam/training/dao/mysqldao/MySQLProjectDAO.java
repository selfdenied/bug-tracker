package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Member;
import com.epam.training.bean.Project;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.exception.DAOException;

/**
 * Class {@code MySQLProjectDAO} contains methods allowing to extract
 * information about Projects found in the application, add and update data
 * their data.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Project
 * @see com.epam.training.dao.AbstractDAO
 */
public class MySQLProjectDAO extends AbstractDAO<Project> {
	private static final String PROJECT_ID = "PROJECT_ID";
	private static final String PROJECT_NAME = "PROJECT_NAME";
	private static final String PROJECT_DESCRIPTION = "PROJECT_DESCRIPTION";
	private static final String PROJECT_MANAGER = "PROJECT_MANAGER";
	private static final String FIND_PROJECTS = "SELECT * FROM PROJECT";
	private static final String FIND_PROJECT_BY_ID = "SELECT * FROM PROJECT WHERE PROJECT_ID = ?";
	private static final String ADD_PROJECT = "INSERT INTO PROJECT (PROJECT_NAME,"
			+ " PROJECT_DESCRIPTION, PROJECT_MANAGER) VALUES (?, ?, ?)";
	private static final String UPDATE_PROJECT = "UPDATE PROJECT SET PROJECT_NAME = ?,"
			+ " PROJECT_DESCRIPTION = ?, PROJECT_MANAGER = ? WHERE PROJECT_ID = ?";

	/**
	 * Constructs MySQLProjectDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLProjectDAO(Connection connection) {
		super(connection);
	}

	/* finds all Projects in the application */
	@Override
	public List<Project> findAll() throws DAOException {
		AbstractDAO<Member> memberDAO = new MySQLMemberDAO(connection);
		List<Project> projectsList = new ArrayList<Project>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_PROJECTS);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Project and initializing its fields */
				Project project = new Project();
				project.setId(rs.getInt(PROJECT_ID));
				project.setProjectName(rs.getString(PROJECT_NAME));
				project.setProjectDescription(rs.getString(PROJECT_DESCRIPTION));
				project.setManager(memberDAO.findEntityByID(rs.getInt(PROJECT_MANAGER)));
				projectsList.add(project);
			}
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return projectsList;
	}

	/* returns Project with the given ID */
	@Override
	public Project findEntityByID(int id) throws DAOException {
		AbstractDAO<Member> memberDAO = new MySQLMemberDAO(connection);
		Project project = null;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_PROJECT_BY_ID);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Project and initializing its fields */
				project = new Project();
				project.setId(rs.getInt(PROJECT_ID));
				project.setProjectName(rs.getString(PROJECT_NAME));
				project.setProjectDescription(rs.getString(PROJECT_DESCRIPTION));
				project.setManager(memberDAO.findEntityByID(rs.getInt(PROJECT_MANAGER)));
			}
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return project;
	}

	/* adds new Project to the database */
	@Override
	public boolean addNewEntity(Project project) throws DAOException {
		boolean isAdded = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(ADD_PROJECT);
			prepStatement.setString(1, project.getProjectName());
			prepStatement.setString(2, project.getProjectDescription());
			prepStatement.setInt(3, project.getManager().getId());
			prepStatement.executeUpdate();
			isAdded = true;
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return isAdded;
	}

	/* updates Project's data */
	@Override
	public boolean updateEntity(Project project, int id) throws DAOException {
		boolean isUpdated = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(UPDATE_PROJECT);
			prepStatement.setString(1, project.getProjectName());
			prepStatement.setString(2, project.getProjectDescription());
			prepStatement.setInt(3, project.getManager().getId());
			prepStatement.setInt(4, id);
			prepStatement.executeUpdate();
			isUpdated = true;
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return isUpdated;
	}
}
