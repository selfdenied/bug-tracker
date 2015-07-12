package com.epam.training.bean;

import com.epam.training.bean.Build;
import com.epam.training.bean.Feature;
import com.epam.training.bean.Member;
import com.epam.training.bean.Project;

/**
 * Class {@code Issue} is a Java Bean that stores the data of issues
 * reported in the application (id, summary, description, status, priority,
 * type, project, assignee, etc.).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class Issue extends Entity {
	private static final long serialVersionUID = -6413461259243067846L;
	private String createdDate;
	private Member createdBy;
	private String modifiedDate;
	private Member modifiedBy;
	private String summary;
	private String description;
	private Feature status;
	private Feature resolution;
	private Feature type;
	private Feature priority;
	private Project project;
	private Build build;
	private Member assignee;

	/* no need to use setters with validation (we take data from DB) */
	
	/**
	 * Returns the date of issue creation.
	 * 
	 * @return Date of creation
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the date of issue creation. 
	 * (use current date converted to String)
	 * 
	 * @param createdDate the date of issue creation
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Returns the member who reported (created) this issue.
	 * 
	 * @return Member whom it was created by
	 * @see com.epam.training.bean.Member
	 */
	public Member getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the member who reported (created) this issue.
	 * 
	 * @param createdBy the member who created this issue
	 * @see com.epam.training.bean.Member
	 */
	public void setCreatedBy(Member createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns the date of issue's last modification.
	 * 
	 * @return Date of modification
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the date of issue's last modification. 
	 * (use current date converted to String)
	 * 
	 * @param modifiedDate the date of issue's last modification
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Returns the last member who modified this issue.
	 * 
	 * @return Member who modified this issue
	 * @see com.epam.training.bean.Member
	 */
	public Member getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the last member who modified this issue.
	 * 
	 * @param modifiedBy the member who modified this issue
	 * @see com.epam.training.bean.Member
	 */
	public void setModifiedBy(Member modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Returns the summary of an issue.
	 * 
	 * @return Issue Summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary of an issue.
	 * 
	 * @param summary the issue's summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Returns the description of an issue.
	 * 
	 * @return Issue Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of an issue.
	 * 
	 * @param description the issue's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the current status of an issue.
	 * 
	 * @return Issue Status
	 * @see com.epam.training.bean.Feature
	 */
	public Feature getStatus() {
		return status;
	}

	/**
	 * Sets the status of an issue.
	 * 
	 * @param status the issue's current status
	 * @see com.epam.training.bean.Feature
	 */
	public void setStatus(Feature status) {
		this.status = status;
	}

	/**
	 * Returns the resolution of an issue.
	 * 
	 * @return Issue Resolution
	 * @see com.epam.training.bean.Feature
	 */
	public Feature getResolution() {
		return resolution;
	}

	/**
	 * Sets the resolution of an issue.
	 * 
	 * @param resolution the issue's resolution
	 * @see com.epam.training.bean.Feature
	 */
	public void setResolution(Feature resolution) {
		this.resolution = resolution;
	}

	/**
	 * Returns the type of an issue.
	 * 
	 * @return Issue Type
	 * @see com.epam.training.bean.Feature
	 */
	public Feature getType() {
		return type;
	}

	/**
	 * Sets the type of an issue.
	 * 
	 * @param type the issue's type
	 * @see com.epam.training.bean.Feature
	 */
	public void setType(Feature type) {
		this.type = type;
	}

	/**
	 * Returns the priority of an issue.
	 * 
	 * @return Issue Priority
	 * @see com.epam.training.bean.Feature
	 */
	public Feature getPriority() {
		return priority;
	}

	/**
	 * Sets the priority of an issue.
	 * 
	 * @param priority the issue's priority
	 * @see com.epam.training.bean.Feature
	 */
	public void setPriority(Feature priority) {
		this.priority = priority;
	}

	/**
	 * Returns the project where this issue was found.
	 * 
	 * @return Project where the issue was found
	 * @see com.epam.training.bean.Project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project where this issue was found.
	 * 
	 * @param project the project where the issue was found
	 * @see com.epam.training.bean.Project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Returns the version of the project's build 
	 * where this issue was found.
	 * 
	 * @return Project's build version
	 * @see com.epam.training.bean.Build
	 */
	public Build getBuild() {
		return build;
	}

	/**
	 * Sets the version of the project's build 
	 * where this issue was found.
	 * 
	 * @param build project's build version
	 * @see com.epam.training.bean.Build
	 */
	public void setBuild(Build build) {
		this.build = build;
	}

	/**
	 * Returns the member whom this issue was assigned to.
	 * 
	 * @return Member who is responsible for solving the issue
	 * @see com.epam.training.bean.Member
	 */
	public Member getAssignee() {
		return assignee;
	}

	/**
	 * Sets the member who is responsible for solving the issue.
	 * 
	 * @param assignee the member who should solve the issue
	 * @see com.epam.training.bean.Member
	 */
	public void setAssignee(Member assignee) {
		this.assignee = assignee;
	}
}
