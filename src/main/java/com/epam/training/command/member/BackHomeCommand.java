package com.epam.training.command.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
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
	private static final String PARAM_MEMBER = "member";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		IssueLogic il = new IssueLogic();
		List<Issue> issuesList = new ArrayList<>();
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(PARAM_MEMBER);
		url = findProperURL(member.isAdmin());
				
		try {
			issuesList = il.assignedIssues(member.getId());
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		request.setAttribute("assignedIssuesList", issuesList);
		return url;
	}

	/* supplementary method that redirects the request to a proper JSP */
	private String findProperURL(boolean isAdmin) {
		String url;

		if (isAdmin) {
			url = BUNDLE.getString("admin");
		} else {
			url = BUNDLE.getString("user");
		}
		return url;
	}
}
