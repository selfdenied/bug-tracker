package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Member;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.exception.GeneralDAOException;

/**
 * Class {@code MySQLMemberDAO} contains methods allowing to extract information
 * about Members registered in the application, add and update data their data.
 * It also contains supplementary methods. For example, method that update
 * Member's password.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Member
 * @see com.epam.training.dao.AbstractDAO
 */
public class MySQLMemberDAO extends AbstractDAO<Member> {
	private static final String MEMBER_ID = "MEMBER_ID";
	private static final String LOGIN = "LOGIN";
	private static final String PASSWORD = "PASSWORD";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String ROLE = "ROLE";
	private static final String FIND_MEMBERS = "SELECT * FROM MEMBER";
	private static final String FIND_MEMBER_BY_ID = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
	private static final String FIND_MEMBER_BY_LOGIN = "SELECT * FROM MEMBER WHERE LOGIN = ?";
	private static final String ADD_MEMBER = "INSERT INTO MEMBER (LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, ROLE)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_MEMBER = "UPDATE MEMBER SET LOGIN = ?, FIRST_NAME = ?,"
			+ " LAST_NAME = ?, ROLE = ? WHERE MEMBER_ID = ?";
	private static final String UPDATE_MEMBER_PASS = "UPDATE MEMBER SET PASSWORD = ? WHERE MEMBER_ID = ?";

	/**
	 * Constructs MySQLMemberDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLMemberDAO(Connection connection) {
		super(connection);
	}

	/* finds all Members registered in the application */
	@Override
	public List<Member> findAll() throws GeneralDAOException {
		List<Member> membersList = new ArrayList<Member>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(FIND_MEMBERS);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Member and initializing its fields */
				Member member = new Member();
				member.setId(rs.getInt(MEMBER_ID));
				member.setLogin(rs.getString(LOGIN));
				member.setPassword(rs.getString(PASSWORD));
				member.setFirstName(rs.getString(FIRST_NAME));
				member.setLastName(rs.getString(LAST_NAME));
				member.setAdmin(rs.getBoolean(ROLE));
				membersList.add(member);
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return membersList;
	}

	/* returns Member with the given ID */
	@Override
	public Member findEntityByID(int id) throws GeneralDAOException {
		PreparedStatement prepStatement = null;
		Member member = null;

		try {
			prepStatement = connection.prepareStatement(FIND_MEMBER_BY_ID);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Member and initializing its fields */
				member = new Member();
				member.setId(rs.getInt(MEMBER_ID));
				member.setLogin(rs.getString(LOGIN));
				member.setPassword(rs.getString(PASSWORD));
				member.setFirstName(rs.getString(FIRST_NAME));
				member.setLastName(rs.getString(LAST_NAME));
				member.setAdmin(rs.getBoolean(ROLE));
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return member;
	}

	/**
	 * Returns Member with the given Login.
	 * 
	 * @param login
	 *            The Member's login
	 * @return Member with the given Login
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public Member findMemberByLogin(String login) throws GeneralDAOException {
		PreparedStatement prepStatement = null;
		Member member = null;

		try {
			prepStatement = connection.prepareStatement(FIND_MEMBER_BY_LOGIN);
			prepStatement.setString(1, login);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Member and initializing its fields */
				member = new Member();
				member.setId(rs.getInt(MEMBER_ID));
				member.setLogin(rs.getString(LOGIN));
				member.setPassword(rs.getString(PASSWORD));
				member.setFirstName(rs.getString(FIRST_NAME));
				member.setLastName(rs.getString(LAST_NAME));
				member.setAdmin(rs.getBoolean(ROLE));
			}
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return member;
	}

	/* adds new Member to the database */
	@Override
	public boolean addNewEntity(Member member) throws GeneralDAOException {
		boolean isAdded = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(ADD_MEMBER);
			prepStatement.setString(1, member.getLogin());
			prepStatement.setString(2, member.getPassword());
			prepStatement.setString(3, member.getFirstName());
			prepStatement.setString(4, member.getLastName());
			prepStatement.setBoolean(5, member.isAdmin());
			prepStatement.executeUpdate();
			isAdded = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return isAdded;
	}

	/* updates Member's data */
	@Override
	public boolean updateEntity(Member member, int id)
			throws GeneralDAOException {
		boolean isUpdated = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(UPDATE_MEMBER);
			prepStatement.setString(1, member.getLogin());
			prepStatement.setString(2, member.getFirstName());
			prepStatement.setString(3, member.getLastName());
			prepStatement.setBoolean(4, member.isAdmin());
			prepStatement.setInt(5, id);
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
	 * Updates Member's password.
	 * 
	 * @param newPassword	New password of the Member
	 * @param id	Member's ID
	 * @return {@code true} if password was successfully updated and
	 *         {@code false} otherwise
	 * @throws GeneralDAOException
	 *             If a database access/handling error occurs.
	 */
	public boolean updateMemberPass(String newPassword, int id)
			throws GeneralDAOException {
		boolean isUpdated = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(UPDATE_MEMBER_PASS);
			prepStatement.setString(1, newPassword);
			prepStatement.setInt(2, id);
			prepStatement.executeUpdate();
			isUpdated = true;
		} catch (SQLException ex) {
			throw new GeneralDAOException("Database access error", ex);
		} finally {
			close(prepStatement);
		}
		return isUpdated;
	}
}
