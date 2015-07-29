package com.epam.training.command.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Build;
import com.epam.training.bean.Project;
import com.epam.training.command.ICommand;
import com.epam.training.command.feature.AddFeatureCommand;
import com.epam.training.logic.LogicException;
import com.epam.training.logic.ProjectLogic;
import com.epam.training.util.Validator;

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
	private static final String PARAM_PROJECT_ID = "projectID";
	private static final String PARAM_BUILD_NAME = "buildName";

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("add_build");
		String buildName = request.getParameter(PARAM_BUILD_NAME);
		String projectID = request.getParameter(PARAM_PROJECT_ID);

		if (buildName != null) {
			try {
				if (buildNameFree(request, buildName, Integer.parseInt(projectID))) {
					Build build = initBuild(buildName, projectID);
					request.setAttribute("newBuildAdded", true);
					request.setAttribute("formNotFilled", false);
					addNewBuild(request, build);
				} else {
					request.setAttribute("projectID", projectID);
					request.setAttribute("buildNameExists", true);
					request.setAttribute("formNotFilled", true);
				}
			} catch (LogicException ex) {
				LOG.error(ex);
				request.setAttribute("exception", ex);
				url = BUNDLE.getString(ERROR);
			}
		} else {
			request.setAttribute("projectID", projectID);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new Build to the database */
	private void addNewBuild(HttpServletRequest request, Build build) 
			throws LogicException {
		if (Validator.validateBuild(build)) {
			ProjectLogic pl = new ProjectLogic();
			pl.addNewBuild(build);
		} else {
			String projectID = request.getParameter(PARAM_PROJECT_ID);
			request.setAttribute("projectID", projectID);
			request.setAttribute("newBuildAdded", false);
			request.setAttribute("buildAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method checks if the Build name exists for the given Project */
	private boolean buildNameFree(HttpServletRequest request, String buildName, 
			int projectID) throws LogicException {
		boolean nameFree = true;
		ProjectLogic pl = new ProjectLogic();
		List<Build> buildsList = pl.buildsList(projectID);

		for (Build build : buildsList) {
			if (build.getBuildName().equals(buildName)) {
				nameFree = false;
			}
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
