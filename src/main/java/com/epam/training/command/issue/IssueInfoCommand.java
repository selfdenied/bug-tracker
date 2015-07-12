package com.epam.training.command.issue;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.command.ICommand;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.exception.GeneralCommandException;
import com.epam.training.exception.GeneralDAOException;

/**
 * Class {@code IssueInfoCommand} puts the information about the selected Issue
 * into request.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class IssueInfoCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(IssueInfoCommand.class);
	private static final String URL = "jsp/issue/issueInfo.jsp";
	private static final String PARAM_NAME_ISSUE_ID = "issueID";
	private AbstractDAOFactory factory;
	private ConnectionPool pool;

	@Override
	public String execute(HttpServletRequest request)
			throws GeneralCommandException {
		int issueID = Integer.parseInt(request.getParameter(PARAM_NAME_ISSUE_ID));
		/* initializing the pool */
		pool = ConnectionPool.getInstance();
		/* getting connection and DAOFactory */
		Connection connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		/* putting the selected Issue into request */
		
		request.setAttribute("issueToView", issueToView(issueID));
		pool.releaseConnection(connection);
		return URL;
	}

	/* supplementary method that returns the selected Issue (by ID) */
	private Issue issueToView(int issueID) throws GeneralCommandException {
		AbstractDAO<Issue> issueDAO = factory.getIssueDAO();
		Issue issueToView = null;
		try {
			issueToView = issueDAO.findEntityByID(issueID);
		} catch (GeneralDAOException ex) {
			LOG.error("Error. Database problem!");
			throw new GeneralCommandException("Database access error", ex);
		}
		return issueToView;
	}
}
