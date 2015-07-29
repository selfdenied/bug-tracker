package com.epam.training.command.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Project;
import com.epam.training.command.ICommand;
import com.epam.training.command.feature.ListFeaturesCommand;
import com.epam.training.logic.LogicException;
import com.epam.training.logic.ProjectLogic;

/**
 * Class {@code ListProjectsCommand} allows to view the list of Projects
 * available in the application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ListProjectsCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ListFeaturesCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("list_projects");
		ProjectLogic pl = new ProjectLogic();
		List<Project> listOfProjects = new ArrayList<>();

		try {
			listOfProjects = pl.projectsList();
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		request.setAttribute("listOfProjects", listOfProjects);
		return url;
	}
}
