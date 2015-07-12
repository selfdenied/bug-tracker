package com.epam.training.constant;

/**
 * Class {@code DBFieldsConstants} contains a list of constants that will be
 * used to extract data from a relational database. The constants are the values
 * (names) of corresponding fields in different tables (i.e. Member, Project,
 * Issue, Build, etc.)
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class DBFieldsConstants {
	/* MEMBER table */
	public static final String MEMBER_ID = "MEMBER_ID";
	public static final String LOGIN = "LOGIN";
	public static final String PASSWORD = "PASSWORD";
	public static final String FIRST_NAME = "FIRST_NAME";
	public static final String LAST_NAME = "LAST_NAME";
	public static final String ROLE = "ROLE";

	/* STATUS table */
	public static final String STATUS_ID = "STATUS_ID";
	public static final String STATUS_NAME = "STATUS_NAME";

	/* RESOLUTION table */
	public static final String RESOLUTION_ID = "RESOLUTION_ID";
	public static final String RESOLUTION_NAME = "RESOLUTION_NAME";

	/* TYPE table */
	public static final String TYPE_ID = "TYPE_ID";
	public static final String TYPE_NAME = "TYPE_NAME";

	/* PRIORITY table */
	public static final String PRIORITY_ID = "PRIORITY_ID";
	public static final String PRIORITY_NAME = "PRIORITY_NAME";

	/* PROJECT table */
	public static final String PROJECT_ID = "PROJECT_ID";
	public static final String PROJECT_NAME = "PROJECT_NAME";
	public static final String PROJECT_DESCRIPTION = "PROJECT_DESCRIPTION";
	public static final String PROJECT_MANAGER = "PROJECT_MANAGER";

	/* BUILD table */
	public static final String BUILD_ID = "BUILD_ID";
	public static final String BUILD_NAME = "BUILD_NAME";
	public static final String BUILD_PROJECT = "PROJECT_ID";

	/* ISSUE table */
	public static final String ISSUE_ID = "ISSUE_ID";
	public static final String ISSUE_CREATED_DATE = "CREATED_DATE";
	public static final String ISSUE_CREATED_BY = "CREATED_BY";
	public static final String ISSUE_MOD_DATE = "MOD_DATE";
	public static final String ISSUE_MOD_BY = "MOD_BY";
	public static final String ISSUE_SUMMARY = "SUMMARY";
	public static final String ISSUE_DESCRIPTION = "DESCRIPTION";
	public static final String ISSUE_STATUS = "STATUS_ID";
	public static final String ISSUE_RESOLUTION = "RESOLUTION_ID";
	public static final String ISSUE_TYPE = "TYPE_ID";
	public static final String ISSUE_PRIORITY = "PRIORITY_ID";
	public static final String ISSUE_PROJECT = "PROJECT_ID";
	public static final String ISSUE_BUILD = "BUILD_ID";
	public static final String ISSUE_ASSIGNEE = "ASSIGNEE";
}
