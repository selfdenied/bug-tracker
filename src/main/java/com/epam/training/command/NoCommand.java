package com.epam.training.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.exception.GeneralCommandException;
import com.epam.training.exception.GeneralDAOException;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

/**
 * Class {@code NoCommand} is invoked when no 'action' parameter is specified in
 * the request object. It redirects the request to default (Welcome) page and
 * puts the list of latest issues in the request.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class NoCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(NoCommand.class);
	private static final String URL = "jsp/common/welcome.jsp";
	private static final int ISSUES_NUMBER = 10;
	private AbstractDAOFactory factory;
	private ConnectionPool pool;

	@Override
	public String execute(HttpServletRequest request)
			throws GeneralCommandException {
		/* initializing the pool */
		pool = ConnectionPool.getInstance();
		/* getting connection and DAOFactory */
		Connection connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		/* putting the list of latest Issues into request */
		request.setAttribute("issuesList", issuesList());
		pool.releaseConnection(connection);
		return URL;
	}

	/* supplementary method that returns the list of Issues */
	private List<Issue> issuesList() throws GeneralCommandException {
		AbstractDAO<Issue> issueDAO = factory.getIssueDAO();
		List<Issue> issuesList = new ArrayList<>();
		try {
			issuesList = issueDAO.findAll();
			if (issuesList.size() > ISSUES_NUMBER) {
				issuesList = issuesList.subList(0, ISSUES_NUMBER);
			}
		} catch (GeneralDAOException ex) {
			LOG.error("Error. Database problem!");
			throw new GeneralCommandException("Database access error", ex);
		}
		return issuesList;
	}
}
