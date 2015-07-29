package com.epam.training.command.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.logic.LogicException;
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

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("list_members");
		MemberLogic ml = new MemberLogic();
		List<Member> listOfMembers = new ArrayList<>();

		try {
			listOfMembers = ml.membersList();
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		request.setAttribute("listOfMembers", listOfMembers);
		return url;
	}
}
