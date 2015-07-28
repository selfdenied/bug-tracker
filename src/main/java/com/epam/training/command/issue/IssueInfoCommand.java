package com.epam.training.command.issue;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.IssueLogic;

/**
 * Class {@code IssueInfoCommand} puts the information about the selected Issue
 * into request.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class IssueInfoCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(IssueInfoCommand.class);
	private static final String PARAM_ISSUE_ID = "issueID";
	private static final String PARAM_LANGUAGE = "lang";
	private String url;
	
	@Override
	public String execute(HttpServletRequest request) {
		url = BUNDLE.getString("issue_info");
		IssueLogic issueLogic = new IssueLogic();
		Issue issueToView = null;
		int issueID = Integer.parseInt(request.getParameter(PARAM_ISSUE_ID));
		String language = request.getParameter(PARAM_LANGUAGE);
		
		try {
			issueToView = issueLogic.issueToView(issueID);
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		/* putting the selected Issue into request */
		request.setAttribute("issueToView", issueToView);
		request.setAttribute("locale", language);
		return url;
	}
}
