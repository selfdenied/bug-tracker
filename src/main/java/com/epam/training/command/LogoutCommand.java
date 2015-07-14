package com.epam.training.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.IssueLogic;

/**
 * Class {@code LogoutCommand} is invoked when a Member clicks on Logout button.
 * It invalidates the session and redirects the request to the index page.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class LogoutCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
	private static final String URL = "jsp/common/welcome.jsp";
	private static final String PARAM_LOCALE = "locale";

	@Override
	public String execute(HttpServletRequest request) {
		IssueLogic il = new IssueLogic();
		List<Issue> recentIssuesList = new ArrayList<>();
		HttpSession session = request.getSession(false);
		String language = (String) session.getAttribute(PARAM_LOCALE);
		session.invalidate();
		
		try {
			recentIssuesList = il.recentIssuesList();
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		/* putting the list of latest Issues into request */
		request.setAttribute("issuesList", recentIssuesList);
		request.setAttribute("locale", language);
		return URL;
	}
}