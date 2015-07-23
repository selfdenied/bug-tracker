package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code UpdatePersonalDataCommand} allows to update Member's (either
 * user or admin) personal data.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class UpdatePersonalDataCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(UpdatePersonalDataCommand.class);
	private static final String PARAM_MEMBER = "member";
	private static final String PARAM_FIRST_NAME = "firstName";
	private static final String PARAM_LAST_NAME = "lastName";
	private static final String PARAM_LOGIN = "login";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("update_pers_data");
		String firstName = request.getParameter(PARAM_FIRST_NAME);
		String lastName = request.getParameter(PARAM_LAST_NAME);
		String login = request.getParameter(PARAM_LOGIN);
		
		if (login != null) {
			if (checkLoginFree(login)) {
				HttpSession session = request.getSession(false);
				Member member = (Member) session.getAttribute(PARAM_MEMBER);
				member.setFirstName(firstName);
				member.setLastName(lastName);
				member.setLogin(login);
				updateData(request, member, member.getId());
				session.setAttribute("member", member);
				request.setAttribute("dataUpdated", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("suchLoginExists", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* supplementary method that updates Member's pers. data */
	private void updateData(HttpServletRequest request, Member member, int id) {
		boolean errorFree = false;
		MemberLogic ml = new MemberLogic();

		try {
			errorFree = ml.updateMemberData(member, id);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			request.setAttribute("dataChangeError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* supplementary method that checks if the entered login is free */
	private boolean checkLoginFree(String login) {
		boolean loginFree = false;
		MemberLogic ml = new MemberLogic();
		
		try {
			Member member = ml.findMemberByLogin(login);
			loginFree = (member == null);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return loginFree;
	}
}
