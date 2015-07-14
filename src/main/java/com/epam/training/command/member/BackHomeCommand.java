package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;

/**
 * Class {@code BackHomeCommand} redirects the request back to the start of the
 * Member page.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class BackHomeCommand implements ICommand {
	private static final String USER_URL = "jsp/common/user.jsp";
	private static final String ADMIN_URL = "jsp/common/admin.jsp";
	private static final String PARAM_MEMBER = "member";

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute(PARAM_MEMBER);
		return findProperURL(member.isAdmin());
	}

	/* supplementary method that redirects the request to a proper JSP */
	private String findProperURL(boolean isAdmin) {
		String url;

		if (isAdmin) {
			url = ADMIN_URL;
		} else {
			url = USER_URL;
		}
		return url;
	}
}
