package com.epam.training.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.IssueLogic;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code AuthCommand} is invoked when a Guest tries to perform
 * authentication. In case the authentication is successful, the Command
 * redirects the request to a proper page, starts the Session, etc.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class AuthCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(AuthCommand.class);
	private static final String DEFAULT_URL = "jsp/common/welcome.jsp";
	private static final String USER_URL = "jsp/common/user.jsp";
	private static final String ADMIN_URL = "jsp/common/admin.jsp";
	private static final String PARAM_LANGUAGE = "lang";
	private static final String PARAM_LOGIN = "login";
	private static final String PARAM_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String url = DEFAULT_URL;
		MemberLogic memberLogic = new MemberLogic();
		IssueLogic il = new IssueLogic();
		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		String language = request.getParameter(PARAM_LANGUAGE);

		try {
			if (memberLogic.checkMemberData(login, password)) {
				Member member = memberLogic.findMemberByLogin(login);
				HttpSession session = request.getSession();
				session.setAttribute("locale", language);
				session.setAttribute("member", member);
				session.setAttribute("assignedIssuesList",
						il.assignedIssues(member.getId()));
				url = findProperURL(member.isAdmin());
			} else {
				request.setAttribute("authFailed", true);
				request.setAttribute("locale", language);
				request.setAttribute("issuesList", il.recentIssuesList());
			}
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return url;
	}

	/* supplementary method that redirects the request to a proper JSP */
	private String findProperURL(boolean isAdmin) {
		String url;

		if (isAdmin) {
			url = ADMIN_URL;
		} else {
			url = USER_URL;
		}
		return url;
	}
}
