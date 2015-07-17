package com.epam.training.command.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Build;
import com.epam.training.bean.Member;
import com.epam.training.bean.Project;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.MemberLogic;
import com.epam.training.logic.ProjectLogic;

/**
 * Class {@code EditProjectCommand} allows to edit the data of existing
 * Projects.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditProjectCommand implements ICommand {
	private static final Logger LOG = Logger
			.getLogger(EditProjectCommand.class);
	private static final String URL = "jsp/project/editProject.jsp";
	private static final String PARAM_PROJECT_ID = "projectID";
	private static final String PARAM_PROJECT_NAME = "projectName";
	private static final String PARAM_PROJECT_DESC = "projectDescription";
	private static final String PARAM_PROJECT_MANAGER = "projectManager";

	@Override
	public String execute(HttpServletRequest request) {
		String projectID = request.getParameter(PARAM_PROJECT_ID);
		String projectName = request.getParameter(PARAM_PROJECT_NAME);
		String projectDesc = request.getParameter(PARAM_PROJECT_DESC);
		String projectManager = request.getParameter(PARAM_PROJECT_MANAGER);

		if (projectName != null) {
			Project project = new Project();
			Member manager = new Member();
			manager.setId(Integer.parseInt(projectManager));
			project.setProjectName(projectName);
			project.setProjectDescription(projectDesc);
			project.setManager(manager);
			updateProject(request, project);
			request.setAttribute("projectDataUpdated", true);
			request.setAttribute("formNotFilled", false);
		} else {
			request.setAttribute("projectID", projectID);
			request.setAttribute("buildsList", buildsList(projectID));
			request.setAttribute("managersList", membersList());
			request.setAttribute("formNotFilled", true);
		}
		return URL;
	}

	/* method updates existing Project's data */
	private void updateProject(HttpServletRequest request, Project project) {
		boolean errorFree = false;
		ProjectLogic pl = new ProjectLogic();
		String projectID = request.getParameter(PARAM_PROJECT_ID);
		
		try {
			errorFree = pl.updateProject(project, Integer.parseInt(projectID));
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			request.setAttribute("projectID", projectID);
			request.setAttribute("buildsList", buildsList(projectID));
			request.setAttribute("managersList", membersList());
			request.setAttribute("projectUpdateError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
	
	/* method returns the list of all Members */
	private List<Member> membersList() {
		List<Member> listOfMembers = new ArrayList<>();
		MemberLogic ml = new MemberLogic();

		try {
			listOfMembers = ml.membersList();
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return listOfMembers;
	}

	/* method returns the list of all Builds of the given Project */
	private List<Build> buildsList(String projectID) {
		List<Build> listOfBuilds = new ArrayList<>();
		ProjectLogic pl = new ProjectLogic();

		try {
			listOfBuilds = pl.buildsList(Integer.parseInt(projectID));
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return listOfBuilds;
	}
}
