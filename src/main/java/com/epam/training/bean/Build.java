package com.epam.training.bean;

import com.epam.training.bean.Project;

/**
 * Class {@code Build} is a Java Bean that stores the data of project's builds
 * available in the application (id, name, project in which this build is found).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class Build extends Entity {
	private static final long serialVersionUID = -6654758458571460342L;
	private String buildName;
	private Project project; // the project in which this build can be found

	/* no need to use setters with validation (we take data from DB) */
	
	/**
	 * Returns the name of a project's build.
	 * 
	 * @return Build Name
	 */
	public String getBuildName() {
		return buildName;
	}

	/**
	 * Sets the name of a build.
	 * 
	 * @param buildName the project's build name
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	/**
	 * Returns the project in which this build can be found.
	 * 
	 * @return Project
	 * @see com.epam.training.bean.Project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project in which this build can be found.
	 * 
	 * @param project the project where this build is found 
	 * @see com.epam.training.bean.Project
	 */
	public void setProject(Project project) {
		this.project = project;
	}
}
