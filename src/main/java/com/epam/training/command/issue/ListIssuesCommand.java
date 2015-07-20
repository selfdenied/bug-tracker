package com.epam.training.command.issue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.IssueLogic;

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
	private static final String URL = "jsp/issue/listIssues.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		IssueLogic il = new IssueLogic();
		List<Issue> listOfIssues = new ArrayList<>();

		try {
			listOfIssues = il.allIssueList();
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		request.setAttribute("listOfIssues", listOfIssues);
		return URL;
	}
}
