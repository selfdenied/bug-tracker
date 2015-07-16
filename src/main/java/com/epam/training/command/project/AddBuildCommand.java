package com.epam.training.command.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Build;
import com.epam.training.bean.Project;
import com.epam.training.command.ICommand;
import com.epam.training.command.feature.AddFeatureCommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.ProjectLogic;

/**
 * Class {@code AddBuildCommand} allows to add new Builds of the given Project
 * to the database.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class AddBuildCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(AddFeatureCommand.class);
	private static final String URL = "jsp/project/addBuild.jsp";
	private static final String PARAM_PROJECT_ID = "projectID";
	private static final String PARAM_BUILD_NAME = "buildName";

	@Override
	public String execute(HttpServletRequest request) {
		String buildName = request.getParameter(PARAM_BUILD_NAME);
		String projectID = request.getParameter(PARAM_PROJECT_ID);

		if (buildName != null) {
			if (buildNameFree(buildName, Integer.parseInt(projectID))) {
				Build build = initBuild(buildName, projectID);
				addNewBuild(request, build);
				request.setAttribute("newBuildAdded", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("projectID", projectID);
				request.setAttribute("buildNameExists", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("projectID", projectID);
			request.setAttribute("formNotFilled", true);
		}
		return URL;
	}

	/* method adds new Build to the database */
	private void addNewBuild(HttpServletRequest request, Build build) {
		boolean errorFree = false;
		ProjectLogic pl = new ProjectLogic();

		try {
			errorFree = pl.addNewBuild(build);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			String projectID = request.getParameter(PARAM_PROJECT_ID);
			request.setAttribute("projectID", projectID);
			request.setAttribute("buildAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method checks if the Build name exists for the given Project */
	private boolean buildNameFree(String buildName, int projectID) {
		boolean nameFree = true;
		ProjectLogic pl = new ProjectLogic();

		try {
			List<Build> buildsList = pl.buildsList(projectID);
			for (Build build : buildsList) {
				if (build.getBuildName().equals(buildName)) {
					nameFree = false;
				}
			}
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return nameFree;
	}

	/* method initialized Build */
	private Build initBuild(String buildName, String projectID) {
		Project project = new Project();
		Build build = new Build();
		project.setId(Integer.parseInt(projectID));
		build.setBuildName(buildName);
		build.setProject(project);
		return build;
	}
}
