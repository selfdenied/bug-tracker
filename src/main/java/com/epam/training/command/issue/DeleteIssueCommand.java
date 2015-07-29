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
 * Class {@code DeleteIssueCommand} allows to delete Issues submitted to the
 * application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class DeleteIssueCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(DeleteIssueCommand.class);
	private static final String PARAM_ISSUE_ID = "issueID";

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("delete_issue");
		String issueID = request.getParameter(PARAM_ISSUE_ID);
		
		try {
			if (issueID != null) {
				request.setAttribute("issueDeleted", true);
				request.setAttribute("formNotFilled", false);
				deleteIssue(request, issueID);
			} else {
				request.setAttribute("listOfIssues", listOfIssues(request));
				request.setAttribute("formNotFilled", true);
			}
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		return url;
	}
	
	/* method deletes existing Issue from the database */
	private void deleteIssue(HttpServletRequest request, String issueID) 
			throws LogicException {
		IssueLogic il = new IssueLogic();
		il.deleteIssue(Integer.parseInt(issueID));
	}
	
	/* method returns the list of Issues submitted to the app */
	private List<Issue> listOfIssues(HttpServletRequest request) 
			throws LogicException {
		IssueLogic il = new IssueLogic();
		List<Issue> listOfIssues = new ArrayList<>();
		listOfIssues = il.allIssueList();
		return listOfIssues;
	}
}
