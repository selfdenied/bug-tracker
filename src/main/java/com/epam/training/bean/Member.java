package com.epam.training.bean;

/**
 * Class {@code Member} is a Java Bean that stores the data of application
 * members (id, login and password, full name, etc.).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class Member extends Entity {
	private static final long serialVersionUID = -3762672954129754297L;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private boolean admin; // true is Administrator, false is User

	/* no need to use setters with validation (we take data from DB) */

	/**
	 * Returns the login of an application member.
	 * 
	 * @return Login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login of an application member.
	 * 
	 * @param login	the member's login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns the password of an application member.
	 * 
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of an application member.
	 * 
	 * @param password	the member's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the first name of an application member.
	 * 
	 * @return First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of an application member.
	 * 
	 * @param firstName	the member's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of an application member.
	 * 
	 * @return Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of an application member.
	 * 
	 * @param lastName	the member's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the role of an application member.
	 * {@code true} corresponds to the Administrator, while
	 * {@code false} corresponds to the User.
	 * 
	 * @return Role
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Sets the role of an application member.
	 * 
	 * @param admin	the member's role (true - Admin, false - User)
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
