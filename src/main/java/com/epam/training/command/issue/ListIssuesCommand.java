package com.epam.training.command.issue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.command.ICommand;
import com.epam.training.logic.IssueLogic;
import com.epam.training.logic.LogicException;

/**
 * Class {@code ListIssuesCommand} allows to view the list of Issues available
 * in the application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ListIssuesCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ListIssuesCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("list_issues");
		IssueLogic il = new IssueLogic();
		List<Issue> listOfIssues = new ArrayList<>();

		try {
			listOfIssues = il.allIssueList();
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		request.setAttribute("listOfIssues", listOfIssues);
		return url;
	}
}
