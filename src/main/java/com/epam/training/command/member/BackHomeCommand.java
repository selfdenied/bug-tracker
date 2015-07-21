package com.epam.training.command.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.IssueLogic;

/**
 * Class {@code BackHomeCommand} redirects the request back to the start of the
 * Member page.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class BackHomeCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(BackHomeCommand.class);
	private static final String USER_URL = "jsp/common/user.jsp";
	private static final String ADMIN_URL = "jsp/common/admin.jsp";
	private static final String PARAM_MEMBER = "member";

	@Override
	public String execute(HttpServletRequest request) {
		IssueLogic il = new IssueLogic();
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(PARAM_MEMBER);
		List<Issue> issuesList = new ArrayList<>();
		
		try {
			issuesList = il.assignedIssues(member.getId());
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		request.setAttribute("assignedIssuesList", issuesList);
		return findProperURL(member.isAdmin());
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
