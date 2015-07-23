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
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("delete_issue");
		String issueID = request.getParameter(PARAM_ISSUE_ID);
		
		if (issueID != null) {
			deleteIssue(request, issueID);
			request.setAttribute("issueDeleted", true);
			request.setAttribute("formNotFilled", false);
		} else {
			request.setAttribute("listOfIssues", listOfIssues());
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}
	
	/* method deletes existing Issue from the database */
	private void deleteIssue(HttpServletRequest request, String issueID) {
		boolean errorFree = false;
		IssueLogic il = new IssueLogic();
		
		try {
			errorFree = il.deleteIssue(Integer.parseInt(issueID));
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			request.setAttribute("listOfIssues", listOfIssues());
			request.setAttribute("issueDeleteError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
	
	/* method returns the list of Issues submitted to the app */
	private List<Issue> listOfIssues() {
		IssueLogic il = new IssueLogic();
		List<Issue> listOfIssues = new ArrayList<>();
		
		try {
			listOfIssues = il.allIssueList();
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return listOfIssues;
	}
}
