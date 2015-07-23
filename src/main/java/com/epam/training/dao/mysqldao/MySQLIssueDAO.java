package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.INTEGER;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.training.bean.Issue;
import com.epam.training.bean.Member;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.exception.GeneralDAOException;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

/**
 * Class {@code MySQLIssueDAO} contains methods allowing to extract information
 * about Issues reported in the application, add new Issues, update existent
 * Issues, and delete them.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Issue
 * @see com.epam.training.dao.AbstractDAO
 */
public class MySQLIssueDAO extends AbstractDAO<Issue> {
	private static final String ISSUE_ID = "ISSUE_ID";
	private static final String ISSUE_CREATED_DATE = "CREATED_DATE";
	private static final String ISSUE_CREATED_BY = "CREATED_BY";
	private static final String ISSUE_MOD_DATE = "MOD_DATE";
	private static final String ISSUE_MOD_BY = "MOD_BY";
	private static final String ISSUE_SUMMARY = "SUMMARY";
	private static final String ISSUE_DESCRIPTION = "DESCRIPTION";
	private static final String ISSUE_STATUS = "STATUS_ID";
	private static final String ISSUE_RESOLUTION = "RESOLUTION_ID";
	private static final String ISSUE_TYPE = "TYPE_ID";
	private static final String ISSUE_PRIORITY = "PRIORITY_ID";
	private static final String ISSUE_PROJECT = "PROJECT_ID";
	private static final String ISSUE_BUILD = "BUILD_ID";
	private static final String ISSUE_ASSIGNEE = "ASSIGNEE";
	private static final String FIND_ISSUES = "SELECT * FROM ISSUE";
	private static final String FIND_ISSUE_BY_ID = "SELECT * FROM ISSUE WHERE ISSUE_ID = ?";
	private static final String ADD_ISSUE = "INSERT INTO ISSUE"
			+ " (CREATED_DATE, CREATED_BY, SUMMARY, DESCRIPTION, STATUS_ID,"
			+ " TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ISSUE = "UPDATE ISSUE"
			+ " SET MOD_DATE = ?, MOD_BY = ?, SUMMARY = ?, DESCRIPTION = ?, STATUS_ID = ?,"
			+ " RESOLUTION_ID = ?, TYPE_ID = ?, PRIORITY_ID = ?, PROJECT_ID = ?, BUILD_ID = ?,"
			+ " ASSIGNEE = ? WHERE ISSUE_ID = ?";
	private static final String DELETE_ISSUE = "DELETE FROM ISSUE WHERE ISSUE_ID = ?";

	/**
	 * Constructs MySQLIssueDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLIssueDAO(Connection connection) {
		super(connection);
	}

	/* finds all Issues in the application */
	@Override
	public List<Issue> findAll() throws GeneralDAOException {
		AbstractDAOFactory ft = AbstractDAOFactory.getDAOFactory(connection,
				MYSQL);
		List<Issue> issuesList = new ArrayList<Issue>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_ISSUES);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Issue and initializing its fields */
				Issue issue = new Issue();
				initializeIssue(issue, rs, ft);
				issuesList.add(issue);
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return issuesList;
	}

	/* returns Issue with the given ID */
	@Override
	public Issue findEntityByID(int id) throws GeneralDAOException {
		AbstractDAOFactory ft = AbstractDAOFactory.getDAOFactory(connection,
				MYSQL);
		Issue issue = null;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_ISSUE_BY_ID);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Issue and initializing its fields */
				issue = new Issue();
				initializeIssue(issue, rs, ft);
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return issue;
	}

	/* adds new Issue to the database */
	@Override
	public boolean addNewEntity(Issue issue) throws GeneralDAOException {
		boolean isAdded = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(ADD_ISSUE);
			prepStatement.setString(1, getCurrentDate());
			prepStatement.setInt(2, issue.getCreatedBy().getId());
			prepStatement.setString(3, issue.getSummary());
			prepStatement.setString(4, issue.getDescription());
			prepStatement.setInt(5, issue.getStatus().getId());
			prepStatement.setInt(6, issue.getType().getId());
			prepStatement.setInt(7, issue.getPriority().getId());
			prepStatement.setInt(8, issue.getProject().getId());
			prepStatement.setInt(9, issue.getBuild().getId());
			if (issue.getAssignee() != null) {
				prepStatement.setInt(10, issue.getAssignee().getId());
			} else {
				prepStatement.setNull(10, INTEGER);
			}
			prepStatement.executeUpdate();
			isAdded = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return isAdded;
	}

	/* updates Issue's data */
	@Override
	public boolean updateEntity(Issue issue, int id) throws GeneralDAOException {
		boolean isUpdated = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(UPDATE_ISSUE);
			prepStatement.setString(1, getCurrentDate());
			prepStatement.setInt(2, issue.getModifiedBy().getId());
			prepStatement.setString(3, issue.getSummary());
			prepStatement.setString(4, issue.getDescription());
			prepStatement.setInt(5, issue.getStatus().getId());
			if (issue.getResolution() != null) {
				prepStatement.setInt(6, issue.getResolution().getId());
			} else {
				prepStatement.setNull(6, INTEGER);
			}
			prepStatement.setInt(7, issue.getType().getId());
			prepStatement.setInt(8, issue.getPriority().getId());
			prepStatement.setInt(9, issue.getProject().getId());
			prepStatement.setInt(10, issue.getBuild().getId());
			if (issue.getAssignee() != null) {
				prepStatement.setInt(11, issue.getAssignee().getId());
			} else {
				prepStatement.setNull(11, INTEGER);
			}
			prepStatement.setInt(12, id);
			prepStatement.executeUpdate();
			isUpdated = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return isUpdated;
	}

	/**
	 * Deletes the selected Issue.
	 * 
	 * @param issueID
	 *            The ID of the Issue to be deleted
	 * @return {@code true} if the selected Issue was successfully deleted and
	 *         {@code false} otherwise
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public boolean deleteIssue(int issueID) throws GeneralDAOException {
		boolean isDeleted = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(DELETE_ISSUE);
			prepStatement.setInt(1, issueID);
			prepStatement.executeUpdate();
			isDeleted = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return isDeleted;
	}

	/* supplementary method that initializes Issue's fields */
	private void initializeIssue(Issue issue, ResultSet rs,
			AbstractDAOFactory ft) throws SQLException, GeneralDAOException {
		AbstractDAO<Member> md = ft.getMemberDAO();

		issue.setId(rs.getInt(ISSUE_ID));
		issue.setCreatedDate(rs.getString(ISSUE_CREATED_DATE));
		issue.setCreatedBy(md.findEntityByID(rs.getInt(ISSUE_CREATED_BY)));
		issue.setModifiedDate(rs.getString(ISSUE_MOD_DATE));
		issue.setModifiedBy(md.findEntityByID(rs.getInt(ISSUE_MOD_BY)));
		issue.setSummary(rs.getString(ISSUE_SUMMARY));
		issue.setDescription(rs.getString(ISSUE_DESCRIPTION));
		issue.setStatus(ft.getStatusDAO().findEntityByID(rs.getInt(ISSUE_STATUS)));
		issue.setType(ft.getTypeDAO().findEntityByID(rs.getInt(ISSUE_TYPE)));
		issue.setPriority(ft.getPriorityDAO().findEntityByID(rs.getInt(ISSUE_PRIORITY)));
		issue.setResolution(ft.getResolutionDAO().findEntityByID(rs.getInt(ISSUE_RESOLUTION)));
		issue.setProject(ft.getProjectDAO().findEntityByID(rs.getInt(ISSUE_PROJECT)));
		issue.setBuild(ft.getBuildDAO().findEntityByID(rs.getInt(ISSUE_BUILD)));
		issue.setAssignee(md.findEntityByID(rs.getInt(ISSUE_ASSIGNEE)));
	}

	/* supplementary method that returns the current date */
	private String getCurrentDate() {
		String formatDate = "dd.MM.yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
		return dateFormat.format(new Date());
	}
}
