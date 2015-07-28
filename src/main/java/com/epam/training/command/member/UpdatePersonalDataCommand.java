package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.MemberLogic;
import com.epam.training.util.Validator;

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
		url = BUNDLE.getString("update_pers_data");
		String firstName = request.getParameter(PARAM_FIRST_NAME);
		String lastName = request.getParameter(PARAM_LAST_NAME);
		String login = request.getParameter(PARAM_LOGIN);
		
		if (login != null) {
			if (checkLoginFree(request, login)) {
				HttpSession session = request.getSession(false);
				Member member = (Member) session.getAttribute(PARAM_MEMBER);
				member.setFirstName(firstName);
				member.setLastName(lastName);
				member.setLogin(login);
				request.setAttribute("dataUpdated", true);
				request.setAttribute("formNotFilled", false);
				updateData(request, member, member.getId());
				session.setAttribute("member", member);
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
		MemberLogic ml = new MemberLogic();

		if (Validator.validateMember(member)) {
			try {
				ml.updateMemberData(member, id);
			} catch (LogicException ex) {
				LOG.error(ex.getMessage());
				request.setAttribute("exception", ex);
				url = BUNDLE.getString(ERROR);
			}
		} else {
			request.setAttribute("dataUpdated", false);
			request.setAttribute("dataChangeError", true);
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
			url = BUNDLE.getString(ERROR);
		}
		return loginFree;
	}
}
