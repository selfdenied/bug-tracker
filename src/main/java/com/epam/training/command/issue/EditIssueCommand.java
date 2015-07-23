package com.epam.training.command.issue;

import static com.epam.training.logic.featuretype.FeatureType.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.*;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.IssueLogic;

/**
 * Class {@code EditIssueCommand} allows to edit existing Issues data.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditIssueCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(EditIssueCommand.class);
	private static final String PARAM_ISSUE_ID = "issueID";
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
		url = resBundle.getString("edit_issue");
		String summary = request.getParameter(PARAM_SUMMARY);
		String issueID = request.getParameter(PARAM_ISSUE_ID);

		if (summary != null) {
			Issue issue = new Issue();
			try {
				issue = initIssue(request, issueID);
				updateIssue(request, issue, issueID);
			} catch (GeneralLogicException ex) {
				LOG.error(ex.getMessage());
			}
			request.setAttribute("issueUpdated", true);
			request.setAttribute("formNotFilled", false);
		} else {
			setFieldsToRequest(request);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method updates existing Issue data */
	private void updateIssue(HttpServletRequest request, Issue issue,
			String issueID) throws GeneralLogicException {
		boolean errorFree = false;
		IssueLogic il = new IssueLogic();
		errorFree = il.updateIssue(issue, Integer.parseInt(issueID));

		if (!errorFree) {
			setFieldsToRequest(request);
			request.setAttribute("issueUpdateError", true);
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
		String issueID = request.getParameter(PARAM_ISSUE_ID);

		try {
			il.setFieldsToRequest(request);
			Issue issue = il.issueToView(Integer.parseInt(issueID));
			if (issue.getStatus().getId() != 1) {
				statuses.add(fl.findFeature(STATUS, 3));
				statuses.add(fl.findFeature(STATUS, 2));
			} else {
				statuses.add(fl.findFeature(STATUS, 1));
				statuses.add(fl.findFeature(STATUS, 2));
			}
			request.setAttribute("issueID", issueID);
			request.setAttribute("statuses", statuses);
			setOtherIssueFields(request, issue);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
	}

	/* method initializes Issue fields */
	private Issue initIssue(HttpServletRequest req, String issueID)
			throws GeneralLogicException {
		FeatureLogic fl = new FeatureLogic();
		IssueLogic il = new IssueLogic();
		Project project = new Project();
		Build build = new Build();
		HttpSession session = req.getSession(false);
		Member modifiedBy = (Member) session.getAttribute("member");
		project.setId(Integer.parseInt(req.getParameter(PARAM_PROJECT)));
		build.setId(Integer.parseInt(req.getParameter(PARAM_BUILD)));

		Issue issue = il.issueToView(Integer.parseInt(issueID));
		issue.setModifiedBy(modifiedBy);
		issue.setSummary(req.getParameter(PARAM_SUMMARY));
		issue.setDescription(req.getParameter(PARAM_DESC));
		issue.setStatus(fl.findFeature(STATUS, Integer.parseInt(req.getParameter(PARAM_STATUS))));
		issue.setType(fl.findFeature(TYPE, Integer.parseInt(req.getParameter(PARAM_TYPE))));
		issue.setPriority(fl.findFeature(PRIORITY, Integer.parseInt(req.getParameter(PARAM_PRIORITY))));
		issue.setProject(project);
		issue.setBuild(build);
		if (req.getParameter(PARAM_ASSIGNEE) != null) {
			Member member = new Member();
			member.setId(Integer.parseInt(req.getParameter(PARAM_ASSIGNEE)));
			issue.setAssignee(member);
		}
		return issue;
	}
	
	/* method sets various Issue fields to request */
	private void setOtherIssueFields(HttpServletRequest req, Issue issue) {
		req.setAttribute("createdDate", issue.getCreatedDate());
		req.setAttribute("createdBy", issue.getCreatedBy());
		req.setAttribute("modifiedDate", issue.getModifiedDate());
		req.setAttribute("modifiedBy", issue.getModifiedBy());
		req.setAttribute("summary", issue.getSummary());
		req.setAttribute("desc", issue.getDescription());
	}
}
