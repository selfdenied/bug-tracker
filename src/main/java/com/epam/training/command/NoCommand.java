package com.epam.training.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.IssueLogic;

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
	private static final String PARAM_LANGUAGE = "lang";
	private static final String DEFAULT_LANGUAGE = "rus";
	private String url;
	
	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("welcome");
		IssueLogic il = new IssueLogic();
		List<Issue> recentIssuesList = new ArrayList<>();
		String language = request.getParameter(PARAM_LANGUAGE);
		if (language == null) {
			language = DEFAULT_LANGUAGE;
		}
		try {
			recentIssuesList = il.recentIssuesList();
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		/* putting the list of latest Issues into request */
		request.setAttribute("issuesList", recentIssuesList);
		request.setAttribute("locale", language);
		return url;
	}
}
