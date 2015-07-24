package com.epam.training.logic;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Member;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.dao.mysqldao.MySQLMemberDAO;
import com.epam.training.exception.DAOException;
import com.epam.training.exception.LogicException;

/**
 * Class {@code MemberLogic} contains various methods that use DAO layer to
 * retrieve information about the Member(s) from a database, add or update
 * Member data, check login/password correctness, etc. These methods will be
 * further used by Command layer.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class MemberLogic {
	private ConnectionPool pool;
	private Connection connection;
	private AbstractDAOFactory factory;

	/**
	 * Constructs MemberLogic object.
	 */
	public MemberLogic() {
		pool = ConnectionPool.getInstance();
	}

	/**
	 * Method returns the list of Members registered in the application.
	 * 
	 * @return The list of recent Members registered in the application
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Member> membersList() throws LogicException {
		AbstractDAO<Member> memberDAO = initDAOFactory().getMemberDAO();
		List<Member> membersList = new ArrayList<>();
		
		try {
			membersList = memberDAO.findAll();
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to retrieve the list of Members!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return membersList;
	}

	/**
	 * Method checks the correctness of Member security data (login/password).
	 * 
	 * @param login
	 *            Member's login
	 * @param password
	 *            Member's password
	 * @return {@code true} when login and password are correct and
	 *         {@code false} otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean checkMemberData(String login, String password)
			throws LogicException {
		boolean dataCorrect = false;
		Member member = null;
		MySQLMemberDAO memberDAO = (MySQLMemberDAO) initDAOFactory().getMemberDAO();

		try {
			member = memberDAO.findMemberByLogin(login);
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to retrieve a Member!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		if (member != null && member.getPassword().equals(password)) {
			dataCorrect = true;
		}
		return dataCorrect;
	}

	/**
	 * Method returns a Member with the given login
	 * 
	 * @param login
	 *            Member's login
	 * @return Member with the given login
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public Member findMemberByLogin(String login) throws LogicException {
		Member member = null;
		MySQLMemberDAO memberDAO = (MySQLMemberDAO) initDAOFactory().getMemberDAO();

		try {
			member = memberDAO.findMemberByLogin(login);
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to retrieve a Member!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return member;
	}

	/**
	 * Method updates Member's password
	 * 
	 * @param password
	 *            Member's new password
	 * @param memberID
	 *            Member's ID
	 * @return {@code true} when password was successfully updated and
	 *         {@code false} otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean updateMemberPass(String password, int memberID)
			throws LogicException {
		boolean isUpdated = false;
		MySQLMemberDAO memberDAO = (MySQLMemberDAO) initDAOFactory().getMemberDAO();

		try {
			isUpdated = memberDAO.updateMemberPass(password, memberID);
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to update Member's password!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isUpdated;
	}

	/**
	 * Method updates Member's data
	 * 
	 * @param Member
	 *            com.epam.training.bean.Member
	 * @param memberID
	 *            Member's ID
	 * @return {@code true} when data was successfully updated and {@code false}
	 *         otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean updateMemberData(Member member, int memberID)
			throws LogicException {
		boolean isUpdated = false;
		AbstractDAO<Member> memberDAO = initDAOFactory().getMemberDAO();

		try {
			isUpdated = memberDAO.updateEntity(member, memberID);
		} catch (DAOException ex) {
			throw new LogicException("Error. Unable to update Member's data!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isUpdated;
	}
	
	/**
	 * Method adds new Member to the database
	 * 
	 * @param Member
	 *            com.epam.training.bean.Member
	 *            
	 * @return {@code true} when Member was successfully added and {@code false}
	 *         otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean addNewMember(Member member) throws LogicException {
		boolean isAdded = false;
		AbstractDAO<Member> memberDAO = initDAOFactory().getMemberDAO();

		try {
			isAdded = memberDAO.addNewEntity(member);
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to add Member to the database!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isAdded;
	}

	/* supplementary method that initializes connection and DAO factory */
	private AbstractDAOFactory initDAOFactory() {
		connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		return factory;
	}
}
