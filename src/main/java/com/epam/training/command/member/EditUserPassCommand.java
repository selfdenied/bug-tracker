package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
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
	private static final String PARAM_USER_ID = "userID";
	private static final String PARAM_PASS = "newPassword";
	private static final String PARAM_PASS_CONFIRM = "newPasswordConfirm";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("change_user_pass");
		String userID = request.getParameter(PARAM_USER_ID);
		String password = request.getParameter(PARAM_PASS);
		String passwordConfirm = request.getParameter(PARAM_PASS_CONFIRM);
		
		if (password != null) {
			if (password.equals(passwordConfirm)) {
				request.setAttribute("passChanged", true);
				request.setAttribute("formNotFilled", false);
				updatePass(request, password, Integer.parseInt(userID));
			} else {
				request.setAttribute("userID", userID);
				request.setAttribute("passNotEqual", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("userID", userID);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}
	
	/* supplementary method that updates Member's password */
	private void updatePass(HttpServletRequest request, String password, 
			int memberID) {
		boolean errorFree = false;
		MemberLogic ml = new MemberLogic();
		
		try {
			errorFree = ml.updateMemberPass(password, memberID);
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		if (!errorFree) {
			String userID = request.getParameter(PARAM_USER_ID);
			request.setAttribute("userID", userID);
			request.setAttribute("passChanged", false);
			request.setAttribute("passChangeError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
}
