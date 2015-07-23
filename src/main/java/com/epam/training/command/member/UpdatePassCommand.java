package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.MemberLogic;

/**
 * Class {@code UpdatePassCommand} updates the password of a Member.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class UpdatePassCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(UpdatePassCommand.class);
	private static final String PARAM_MEMBER = "member";
	private static final String PARAM_PASS = "newPassword";
	private static final String PARAM_PASS_CONFIRM = "newPasswordConfirm";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("update_pass");
		String password = request.getParameter(PARAM_PASS);
		String passwordConfirm = request.getParameter(PARAM_PASS_CONFIRM);
		
		if (password != null) {
			if (password.equals(passwordConfirm)) {
				HttpSession session = request.getSession(false);
				Member member = (Member) session.getAttribute(PARAM_MEMBER);
				updatePass(request, password, member.getId());
				request.setAttribute("passChanged", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("passNotEqual", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("formNotFilled", true);
		}
		return url;
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
			request.setAttribute("passChangeError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
}
