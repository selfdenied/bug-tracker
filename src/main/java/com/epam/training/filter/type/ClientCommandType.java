package com.epam.training.filter.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class {@code ClientCommandType} contains the set of all Commands that are
 * allowed to access by Clients with either 'User' or 'Guest' role.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public abstract class ClientCommandType {
	private static Set<String> userCommands = new HashSet<>();
	private static Set<String> guestCommands = new HashSet<>();

	/* initializes the sets of allowed Command 'action' keys */
	static {
		guestCommands.add("changeLang");
		guestCommands.add("issueInfo");
		guestCommands.add("auth");
		userCommands.add("changeLang");
		userCommands.add("issueInfo");
		userCommands.add("auth");
		userCommands.add("backHome");
		userCommands.add("logout");
		userCommands.add("updatePass");
		userCommands.add("updatePersonalData");
		userCommands.add("listIssues");
		userCommands.add("submitIssue");
		userCommands.add("editIssue");
		userCommands.add("closeIssue");
		userCommands.add("reopenIssue");
	}

	/**
	 * Returns a the set of Command 'action keys' allowed to access by Clients
	 * with 'User' role.
	 * 
	 * @return the set of Command 'action keys' for Users
	 */
	public static Set<String> getUserCommands() {
		return Collections.unmodifiableSet(userCommands);
	}

	/**
	 * Returns a the set of Command 'action keys' allowed to access by Clients
	 * with 'Guest' role.
	 * 
	 * @return the set of Command 'action keys' for Guests
	 */
	public static Set<String> getGuestCommands() {
		return Collections.unmodifiableSet(guestCommands);
	}
}
