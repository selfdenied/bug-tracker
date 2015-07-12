package com.epam.training.command;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.factory.AbstractDAOFactory;
import com.epam.training.exception.GeneralCommandException;
import com.epam.training.exception.GeneralDAOException;

/**
 * Class {@code ChangeLanguageCommand} allows to switch between Russian and
 * English languages. It is needed to support internationalization of the
 * application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ChangeLanguageCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);
	private static final String URL = "jsp/common/welcome.jsp";
	private static final int ISSUES_NUMBER = 10;
	private static final String PARAM_NAME_LANGUAGE = "lang";
	private AbstractDAOFactory factory;
	private ConnectionPool pool;

	@Override
	public String execute(HttpServletRequest request)
			throws GeneralCommandException {
		String language = request.getParameter(PARAM_NAME_LANGUAGE);
		/* setting the default Locale */
		Locale.setDefault(initNewLocale(language));
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

	/* supplementary method that selects the Locale depending on the language */
	private Locale initNewLocale(String language) {
		Locale newLocale;
		if ("eng".equals(language)) {
			newLocale = new Locale("en", "US");
		} else {
			newLocale = new Locale("ru", "RU");
		}
		return newLocale;
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
