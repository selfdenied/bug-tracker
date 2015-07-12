package com.epam.training.bean;

import com.epam.training.bean.Member;

/**
 * Class {@code Project} is a Java Bean that stores the data of projects
 * registered in the application (id, name, description, project's manager).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class Project extends Entity {
	private static final long serialVersionUID = 4279315717743010097L;
	private String projectName;
	private String projectDescription;
	private Member manager; // the project's manager

	/* no need to use setters with validation (we take data from DB) */

	/**
	 * Returns the name of a project registered in the application.
	 * 
	 * @return Project Name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the name of a project.
	 * 
	 * @param projectName the project's name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Returns the description of a project registered in the application.
	 * 
	 * @return Project Description
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * Sets the description of a project.
	 * 
	 * @param projectDescription the project's description
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	/**
	 * Returns the manager of a project registered in the application.
	 * 
	 * @return Project Manager
	 * @see com.epam.training.bean.Member
	 */
	public Member getManager() {
		return manager;
	}

	/**
	 * Sets the manager of a project.
	 * 
	 * @param manager the project's manager
	 * @see com.epam.training.bean.Member
	 */
	public void setManager(Member manager) {
		this.manager = manager;
	}
}
