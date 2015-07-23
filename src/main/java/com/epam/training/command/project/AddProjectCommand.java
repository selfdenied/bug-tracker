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
 * Class {@code AddProjectCommand} allows to add new Projects to the database.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class AddProjectCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(AddProjectCommand.class);
	private static final String PARAM_PROJECT_NAME = "projectName";
	private static final String PARAM_PROJECT_DESC = "projectDescription";
	private static final String PARAM_PROJECT_MANAGER = "projectManager";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("add_project");
		String projectName = request.getParameter(PARAM_PROJECT_NAME);
		String projectDesc = request.getParameter(PARAM_PROJECT_DESC);
		String projectManager = request.getParameter(PARAM_PROJECT_MANAGER);

		if (projectName != null) {
			if (projectNameFree(projectName)) {
				Project project = new Project();
				Member manager = new Member();
				manager.setId(Integer.parseInt(projectManager));
				project.setProjectName(projectName);
				project.setProjectDescription(projectDesc);
				project.setManager(manager);
				addNewProject(request, project);
				addNewBuild(request, projectName);
				request.setAttribute("newProjectAdded", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("managersList", membersList());
				request.setAttribute("projectNameExists", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("managersList", membersList());
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new Project to the database */
	private void addNewProject(HttpServletRequest request, Project project) {
		boolean errorFree = false;
		ProjectLogic pl = new ProjectLogic();

		try {
			errorFree = pl.addNewProject(project);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			request.setAttribute("managersList", membersList());
			request.setAttribute("projectAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method adds new Build to the database */
	private void addNewBuild(HttpServletRequest request, String projectName) {
		boolean errorFree = false;
		ProjectLogic pl = new ProjectLogic();

		try {
			errorFree = pl.addNewBuild(initBuild(pl, projectName));
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			request.setAttribute("managersList", membersList());
			request.setAttribute("projectAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method checks if a Project name is free */
	private boolean projectNameFree(String projectName) {
		boolean nameFree = true;
		ProjectLogic pl = new ProjectLogic();

		try {
			List<Project> projectsList = pl.projectsList();
			for (Project project : projectsList) {
				if (project.getProjectName().equals(projectName)) {
					nameFree = false;
				}
			}
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return nameFree;
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

	/* method initializes defaultBuild */
	private Build initBuild(ProjectLogic pl, String projectName)
			throws GeneralLogicException {
		Build build = new Build();
		Project project = new Project();
		build.setBuildName("вер.0.0.1-SNAPSHOT");
		project.setId(pl.findProjectID(projectName));
		build.setProject(project);
		return build;
	}
}
