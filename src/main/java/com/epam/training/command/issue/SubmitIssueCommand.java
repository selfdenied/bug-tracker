package com.epam.training.command.issue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.*;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.IssueLogic;
import com.epam.training.util.Validator;

import static com.epam.training.logic.featuretype.FeatureType.*;

/**
 * Class {@code SubmitIssueCommand} allows to report new Issues.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class SubmitIssueCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(SubmitIssueCommand.class);
	private static final String PARAM_SUMMARY = "summary";
	private static final String PARAM_DESC = "desc";
	private static final String PARAM_STATUS = "status";
	private static final String PARAM_TYPE = "type";
	private static final String PARAM_PRIORITY = "priority";
	private static final String PARAM_PROJECT = "project";
	private static final String PARAM_BUILD = "build";
	private static final String PARAM_ASSIGNEE = "assignee";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = BUNDLE.getString("add_issue");
		String summary = request.getParameter(PARAM_SUMMARY);

		if (summary != null) {
			Issue issue = new Issue();
			request.setAttribute("newIssueAdded", true);
			request.setAttribute("formNotFilled", false);
			try {
				issue = initIssue(request);
				addIssue(request, issue);
			} catch (LogicException ex) {
				LOG.error(ex.getMessage());
				request.setAttribute("exception", ex);
				url = BUNDLE.getString(ERROR);
			}
		} else {
			setFieldsToRequest(request);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new Issue to the database */
	private void addIssue(HttpServletRequest request, Issue issue)
			throws LogicException {
		if (Validator.validateIssue(issue)) {
			IssueLogic il = new IssueLogic();
			il.addNewIssue(issue);
		} else {
			setFieldsToRequest(request);
			request.setAttribute("newIssueAdded", false);
			request.setAttribute("issueAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/*
	 * method sets the lists of Types, Statuses, Builds, etc. as the
	 * corresponding attributes to request
	 */
	private void setFieldsToRequest(HttpServletRequest request) {
		IssueLogic il = new IssueLogic();
		FeatureLogic fl = new FeatureLogic();
		List<Feature> statuses = new ArrayList<>();

		try {
			il.setFieldsToRequest(request);
			statuses.add(fl.findFeature(STATUS, 1));
			statuses.add(fl.findFeature(STATUS, 2));
			request.setAttribute("statuses", statuses);
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
	}

	/* method initializes Issue fields */
	private Issue initIssue(HttpServletRequest req)
			throws LogicException {
		FeatureLogic fl = new FeatureLogic();
		Project project = new Project();
		Build build = new Build();
		HttpSession session = req.getSession(false);
		String assignee = req.getParameter(PARAM_ASSIGNEE);
		Member createdBy = (Member) session.getAttribute("member");
		project.setId(Integer.parseInt(req.getParameter(PARAM_PROJECT)));
		build.setId(Integer.parseInt(req.getParameter(PARAM_BUILD)));

		Issue issue = new Issue();
		issue.setCreatedBy(createdBy);
		issue.setSummary(req.getParameter(PARAM_SUMMARY));
		issue.setDescription(req.getParameter(PARAM_DESC));
		issue.setStatus(fl.findFeature(STATUS, Integer.parseInt(req.getParameter(PARAM_STATUS))));
		issue.setType(fl.findFeature(TYPE, Integer.parseInt(req.getParameter(PARAM_TYPE))));
		issue.setPriority(fl.findFeature(PRIORITY, Integer.parseInt(req.getParameter(PARAM_PRIORITY))));
		issue.setProject(project);
		issue.setBuild(build);
		if (assignee != null) {
			Member member = new Member();
			member.setId(Integer.parseInt(assignee));
			issue.setAssignee(member);
		}
		return issue;
	}
}
