package com.epam.training.command.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Build;
import com.epam.training.bean.Member;
import com.epam.training.bean.Project;
import com.epam.training.command.ICommand;
import com.epam.training.logic.LogicException;
import com.epam.training.logic.MemberLogic;
import com.epam.training.logic.ProjectLogic;
import com.epam.training.util.Validator;

/**
 * Class {@code EditProjectCommand} allows to edit the data of existing
 * Projects.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditProjectCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(EditProjectCommand.class);
	private static final String PARAM_PROJECT_ID = "projectID";
	private static final String PARAM_PROJECT_NAME = "projectName";
	private static final String PARAM_PROJECT_DESC = "projectDescription";
	private static final String PARAM_PROJECT_MANAGER = "projectManager";

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("edit_project");
		String projectID = request.getParameter(PARAM_PROJECT_ID);
		String projectName = request.getParameter(PARAM_PROJECT_NAME);
		String projectDesc = request.getParameter(PARAM_PROJECT_DESC);
		String projectManager = request.getParameter(PARAM_PROJECT_MANAGER);

		try {
			if (projectName != null) {
				Project project = new Project();
				Member manager = new Member();
				manager.setId(Integer.parseInt(projectManager));
				project.setProjectName(projectName);
				project.setProjectDescription(projectDesc);
				project.setManager(manager);
				request.setAttribute("projectDataUpdated", true);
				request.setAttribute("formNotFilled", false);
				updateProject(request, project, projectID);
			} else {
				setAttrsToRequest(request, projectID);
			}
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		return url;
	}

	/* method updates existing Project's data */
	private void updateProject(HttpServletRequest request, Project project,
			String projectID) throws LogicException {
		if (Validator.validateProject(project)) {
			ProjectLogic pl = new ProjectLogic();
			pl.updateProject(project, Integer.parseInt(projectID));
		} else {
			setAttrsToRequest(request, projectID);
			request.setAttribute("projectDataUpdated", false);
			request.setAttribute("projectUpdateError", true);
		}
	}

	/* method returns the list of all Members */
	private List<Member> membersList(HttpServletRequest request) 
			throws LogicException {
		List<Member> listOfMembers = new ArrayList<>();
		MemberLogic ml = new MemberLogic();
		listOfMembers = ml.membersList();
		return listOfMembers;
	}

	/* method returns the list of all Builds of the given Project */
	private List<Build> buildsList(HttpServletRequest request, 
			String projectID) throws LogicException {
		List<Build> listOfBuilds = new ArrayList<>();
		ProjectLogic pl = new ProjectLogic();
		listOfBuilds = pl.buildsList(Integer.parseInt(projectID));
		return listOfBuilds;
	}

	private void setAttrsToRequest(HttpServletRequest request, String projectID) 
			throws LogicException {
		ProjectLogic pl = new ProjectLogic();
		Project project = new Project();
		project = pl.receiveProject(Integer.parseInt(projectID));

		request.setAttribute("projectID", projectID);
		request.setAttribute("name", project.getProjectName());
		request.setAttribute("desc", project.getProjectDescription());
		request.setAttribute("buildsList", buildsList(request, projectID));
		request.setAttribute("managersList", membersList(request));
		request.setAttribute("formNotFilled", true);
	}
}
