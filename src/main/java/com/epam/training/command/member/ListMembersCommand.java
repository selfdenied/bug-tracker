package com.epam.training.command.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code ListMembersCommand} allows to view the list of Members (both
 * Users and Admins) registered in the application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ListMembersCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ListMembersCommand.class);
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("list_members");
		MemberLogic ml = new MemberLogic();
		List<Member> listOfMembers = new ArrayList<>();

		try {
			listOfMembers = ml.membersList();
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		request.setAttribute("listOfMembers", listOfMembers);
		return url;
	}
}
