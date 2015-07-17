package com.epam.training.command.factory;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.epam.training.command.*;
import com.epam.training.command.issue.*;
import com.epam.training.command.member.*;
import com.epam.training.command.feature.*;
import com.epam.training.command.project.*;

/**
 * Class {@code CommandFactory} returns a proper ICommand class depending on the
 * value of "action" request parameter. The factory is constructed using
 * Singleton design pattern (allows to create only 1 instance of factory).
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class CommandFactory {
	private HashMap<String, ICommand> commandMap = new HashMap<>();

	/* Constructor is made private to implement Singleton design pattern */
	/* initializes a map with Command classes */
	private CommandFactory() {
		commandMap.put("changeLang", new ChangeLanguageCommand());
		commandMap.put("issueInfo", new IssueInfoCommand());
		commandMap.put("auth", new AuthCommand());
		commandMap.put("backHome", new BackHomeCommand());
		commandMap.put("logout", new LogoutCommand());
		commandMap.put("updatePass", new UpdatePassCommand());
		commandMap.put("updatePersonalData", new UpdatePersonalDataCommand());
		commandMap.put("listFeatures", new ListFeaturesCommand());
		commandMap.put("addFeature", new AddFeatureCommand());
		commandMap.put("editFeature", new EditFeatureCommand());
		commandMap.put("listProjects", new ListProjectsCommand());
		commandMap.put("addBuild", new AddBuildCommand());
		commandMap.put("addProject", new AddProjectCommand());
		commandMap.put("editProject", new EditProjectCommand());
		commandMap.put("listMembers", new ListMembersCommand());
		commandMap.put("changeUserPassword", new EditUserPassCommand());
		commandMap.put("addMember", new AddMemberCommand());
	}

	/*
	 * Private class that contains static field where CommandFactory object is
	 * created. Such schema prevents creation of 2 or more CommandFactory
	 * objects in a multithread environment
	 * 
	 * (see Singleton design pattern)
	 */
	private static class CommandFactoryHolder {
		private static final CommandFactory commandFactory = new CommandFactory();
	}

	/**
	 * Returns the only instance of CommandFactory
	 * 
	 * @return com.epam.training.command.factory.CommandFactory
	 */
	public static CommandFactory getInstance() {
		return CommandFactoryHolder.commandFactory;
	}

	/**
	 * Returns a proper ICommand class depending on the value of "action"
	 * request parameter.
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @return Command class (implements com.epam.training.command.ICommand
	 *         interface)
	 */
	public ICommand getCommand(HttpServletRequest request) {
		ICommand command = null;
		String action = request.getParameter("action");
		command = commandMap.get(action);

		if (command == null) {
			command = new NoCommand();
		}
		return command;
	}
}
