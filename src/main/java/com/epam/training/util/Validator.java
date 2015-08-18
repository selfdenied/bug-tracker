package com.epam.training.util;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.training.bean.*;

/**
 * Class {@code Validator} contains various methods that allow to validate data
 * received from JSPs before inserting them into the database/updating the
 * database.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public abstract class Validator {
	private static final ResourceBundle rb;
	private static final int MAX_LENGTH = 50;
	private static Pattern pattern;
	private static Matcher matcher;

	/* initializes the ResourceBundle instance */
	static {
		rb = ResourceBundle.getBundle("regExp");
	}

	/**
	 * Validates the entered password.
	 * 
	 * @param password
	 *            the password to validate
	 * @return {@code true} when entered password passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validatePassword(String password) {
		boolean dataOk = false;
		String regExp = rb.getString("password");
		pattern = Pattern.compile(regExp);

		if (password != null && password.length() > 0) {
			matcher = pattern.matcher(password);
			dataOk = matcher.matches();
			matcher.reset();
		}
		return dataOk;
	}

	/**
	 * Validates the entered Member's data.
	 * 
	 * @param member
	 *            the Member who's data needs validation
	 * @return {@code true} when Member's data passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validateMember(Member member) {
		boolean dataOk = false;
		
		if (member != null) {
			String firstName = member.getFirstName();
			String lastName = member.getLastName();
			boolean fullNameOk = validateFullName(firstName, lastName);
			boolean loginOk = validateLogin(member.getLogin());
			dataOk = (fullNameOk && loginOk);
		}
		return dataOk;
	}

	/**
	 * Validates the entered Build's data.
	 * 
	 * @param build
	 *            the Build which data needs validation
	 * @return {@code true} when Build's data passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validateBuild(Build build) {
		boolean dataOk = false;
		
		if (build != null) {
			dataOk = validateEntityName(build.getBuildName());
		}
		return dataOk;
	}

	/**
	 * Validates the entered Project's data.
	 * 
	 * @param project
	 *            the Project which data needs validation
	 * @return {@code true} when Project's data passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validateProject(Project project) {
		boolean dataOk = false;
		
		if (project != null) {
			String projectName = project.getProjectName();
			String projectDesc = project.getProjectDescription();
			boolean nameOk = validateEntityName(projectName);
			boolean descOk = validateEntityDesc(projectDesc);
			dataOk = (nameOk && descOk);
		}
		return dataOk;
	}
	
	/**
	 * Validates the entered Feature's data.
	 * 
	 * @param feature
	 *            the Feature which data needs validation
	 * @return {@code true} when Feature's data passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validateFeature(Feature feature) {
		boolean dataOk = false;
		
		if (feature != null) {
			dataOk = validateEntityName(feature.getFeatureName());
		}
		return dataOk;
	}
	
	/**
	 * Validates the entered Issue's data.
	 * 
	 * @param issue
	 *            the Issue which data needs validation
	 * @return {@code true} when Issue's data passes validation and
	 *         {@code false} otherwise
	 */
	public static boolean validateIssue(Issue issue) {
		boolean dataOk = false;
		
		if (issue != null) {
			String summary = issue.getSummary();
			String issueDesc = issue.getDescription();
			boolean summaryOk = validateIssueSummary(summary);
			boolean descOk = validateEntityDesc(issueDesc);
			dataOk = (summaryOk && descOk);
		}
		return dataOk;
	}

	/* Validates the entered login (e-mail) */
	private static boolean validateLogin(String login) {
		boolean dataOk = false;
		String regExp = rb.getString("email");
		pattern = Pattern.compile(regExp);

		if (login != null) {
			if ((login.length() > 0) && (login.length() <= MAX_LENGTH)) {
				matcher = pattern.matcher(login);
				dataOk = matcher.matches();
				if (matcher != null) {
					matcher.reset();
				}
			}
		}
		return dataOk;
	}

	/* validates Member's full name */
	private static boolean validateFullName(String firstName, String lastName) {
		boolean dataOk = false;
		String regExp = rb.getString("client_name");
		pattern = Pattern.compile(regExp);

		if (firstName != null && lastName != null) {
			if (firstName.length() > 0 && lastName.length() > 0) {
				dataOk = (pattern.matcher(firstName).matches() && 
						pattern.matcher(lastName).matches());
				if (matcher != null) {
					matcher.reset();
				}
			}
		}
		return dataOk;
	}

	/* Validates the entered Entity's name */
	private static boolean validateEntityName(String name) {
		boolean dataOk = false;
		String regExp = rb.getString("entity_name");
		pattern = Pattern.compile(regExp);

		if (name != null && name.length() > 0) {
			matcher = pattern.matcher(name);
			dataOk = matcher.matches();
			if (matcher != null) {
				matcher.reset();
			}
		}
		return dataOk;
	}
	
	/* Validates the entered Issue's summary */
	private static boolean validateIssueSummary(String summary) {
		boolean dataOk = false;
		String regExp = rb.getString("issue_name");
		pattern = Pattern.compile(regExp);

		if (summary != null && summary.length() > 0) {
			matcher = pattern.matcher(summary);
			dataOk = matcher.matches();
			if (matcher != null) {
				matcher.reset();
			}
		}
		return dataOk;
	}

	/* Validates the entered Entity's description */
	private static boolean validateEntityDesc(String desc) {
		boolean dataOk = false;
		String regExp = rb.getString("entity_desc");
		pattern = Pattern.compile(regExp);

		if (desc != null && desc.length() > 0) {
			matcher = pattern.matcher(desc);
			dataOk = matcher.matches();
			if (matcher != null) {
				matcher.reset();
			}
		}
		return dataOk;
	}
}
