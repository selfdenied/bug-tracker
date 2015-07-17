package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code EditUserPassCommand} updates the password of a Member with the
 * given ID.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditUserPassCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(EditUserPassCommand.class);
	private static final String URL = "jsp/member/changeUserPass.jsp";
	private static final String PARAM_USER_ID = "userID";
	private static final String PARAM_PASS = "newPassword";
	private static final String PARAM_PASS_CONFIRM = "newPasswordConfirm";

	@Override
	public String execute(HttpServletRequest request) {
		String userID = request.getParameter(PARAM_USER_ID);
		String password = request.getParameter(PARAM_PASS);
		String passwordConfirm = request.getParameter(PARAM_PASS_CONFIRM);
		
		if (password != null) {
			if (password.equals(passwordConfirm)) {
				updatePass(request, password, Integer.parseInt(userID));
				request.setAttribute("passChanged", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("userID", userID);
				request.setAttribute("passNotEqual", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("userID", userID);
			request.setAttribute("formNotFilled", true);
		}
		return URL;
	}
	
	/* supplementary method that updates Member's password */
	private void updatePass(HttpServletRequest request, String password, int memberID) {
		boolean errorFree = false;
		MemberLogic ml = new MemberLogic();
		
		try {
			errorFree = ml.updateMemberPass(password, memberID);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			String userID = request.getParameter(PARAM_USER_ID);
			request.setAttribute("userID", userID);
			request.setAttribute("passChangeError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
}
