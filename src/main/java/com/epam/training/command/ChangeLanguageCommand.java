package com.epam.training.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Issue;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.IssueLogic;

/**
 * Class {@code ChangeLanguageCommand} allows to switch between Russian and
 * English languages. It is needed to support internationalization of the
 * application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ChangeLanguageCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);
	private static final String PARAM_LANGUAGE = "lang";
	private String url;
	
	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("welcome");
		IssueLogic issueLogic = new IssueLogic();
		List<Issue> recentIssuesList = new ArrayList<>();
		String language = request.getParameter(PARAM_LANGUAGE);

		try {
			recentIssuesList = issueLogic.recentIssuesList();
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		/* putting the list of latest Issues into request */
		request.setAttribute("issuesList", recentIssuesList);
		/* setting new locale */
		request.setAttribute("locale", language);
		return url;
	}
}
