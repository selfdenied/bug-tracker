package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code EditMemberCommand} allows to edit existing Members data.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditMemberCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(EditMemberCommand.class);
	private static final String PARAM_USER_ID = "userID";
	private static final String PARAM_MEMBER_FNAME = "firstName";
	private static final String PARAM_MEMBER_LNAME = "lastName";
	private static final String PARAM_MEMBER_LOGIN = "login";
	private static final String PARAM_MEMBER_ADMIN = "admin";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("edit_member");
		String firstName = request.getParameter(PARAM_MEMBER_FNAME);
		String userID = request.getParameter(PARAM_USER_ID);

		if (firstName != null) {
			String login = request.getParameter(PARAM_MEMBER_LOGIN);
			if (checkLoginFree(request, login)) {
				HttpSession session = request.getSession(false);
				String lastName = request.getParameter(PARAM_MEMBER_LNAME);
				String role = request.getParameter(PARAM_MEMBER_ADMIN);
				Member member = initMemberData(firstName, lastName, login,
						Boolean.parseBoolean(role));
				request.setAttribute("dataUpdated", true);
				request.setAttribute("formNotFilled", false);
				updateMember(request, member, userID);
				session.setAttribute("member", member);
			} else {
				request.setAttribute("userID", userID);
				request.setAttribute("loginExists", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("userID", userID);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method updates existing Member data */
	private void updateMember(HttpServletRequest request, Member member,
			String userID) {
		boolean errorFree = false;
		MemberLogic ml = new MemberLogic();

		try {
			errorFree = ml.updateMemberData(member, Integer.parseInt(userID));
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		if (!errorFree) {
			request.setAttribute("userID", userID);
			request.setAttribute("dataUpdated", false);
			request.setAttribute("dataUpdateError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* supplementary method that checks if the entered login is free */
	private boolean checkLoginFree(HttpServletRequest request, 
			String login) {
		boolean loginFree = false;
		MemberLogic ml = new MemberLogic();

		try {
			Member member = ml.findMemberByLogin(login);
			loginFree = (member == null);
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		return loginFree;
	}

	/* method initializes Member's data */
	private Member initMemberData(String fName, String lName, String login,
			boolean role) {
		Member member = new Member();
		member.setFirstName(fName);
		member.setLastName(lName);
		member.setLogin(login);
		member.setAdmin(role);
		return member;
	}
}
