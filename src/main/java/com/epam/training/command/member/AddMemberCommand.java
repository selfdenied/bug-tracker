package com.epam.training.command.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Member;
import com.epam.training.command.ICommand;
import com.epam.training.logic.LogicException;
import com.epam.training.logic.MemberLogic;

import static com.epam.training.util.Validator.validateMember;
import static com.epam.training.util.Validator.validatePassword;

/**
 * Class {@code AddMemberCommand} allows to add new Members to the database.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class AddMemberCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(AddMemberCommand.class);
	private static final String PARAM_MEMBER_FNAME = "firstName";
	private static final String PARAM_MEMBER_LNAME = "lastName";
	private static final String PARAM_MEMBER_LOGIN = "login";
	private static final String PARAM_MEMBER_ADMIN = "admin";
	private static final String PARAM_MEMBER_PASS = "pass";
	private static final String PARAM_MEMBER_PASS_CONF = "passConf";

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("add_member");
		String firstName = request.getParameter(PARAM_MEMBER_FNAME);

		if (firstName != null) {
			String login = request.getParameter(PARAM_MEMBER_LOGIN);
			String pass = request.getParameter(PARAM_MEMBER_PASS);
			String passConf = request.getParameter(PARAM_MEMBER_PASS_CONF);
			try {
				if (checkLoginFree(request, login) && pass.equals(passConf)) {
					String lastName = request.getParameter(PARAM_MEMBER_LNAME);
					String role = request.getParameter(PARAM_MEMBER_ADMIN);
					Member member = initMemberData(firstName, lastName, login,
							pass, Boolean.parseBoolean(role));
					request.setAttribute("newMemberAdded", true);
					request.setAttribute("formNotFilled", false);
					addNewMember(request, member);
				} else {
					request.setAttribute("dataError", true);
					request.setAttribute("formNotFilled", true);
				}
			} catch (LogicException ex) {
				LOG.error(ex);
				request.setAttribute("exception", ex);
				url = BUNDLE.getString(ERROR);
			}
		} else {
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new Member to the database */
	private void addNewMember(HttpServletRequest request, Member member) 
			throws LogicException {
		String password = member.getPassword();

		if (validateMember(member) && validatePassword(password)) {
			MemberLogic ml = new MemberLogic();
			ml.addNewMember(member);
		} else {
			request.setAttribute("newMemberAdded", false);
			request.setAttribute("memberAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* supplementary method that checks if the entered login is free */
	private boolean checkLoginFree(HttpServletRequest request, 
			String login) throws LogicException {
		MemberLogic ml = new MemberLogic();
		Member member = ml.findMemberByLogin(login);
		return (member == null);
	}

	/* method initializes Member's data */
	private Member initMemberData(String fName, String lName, String login,
			String pass, boolean role) {
		Member member = new Member();
		member.setFirstName(fName);
		member.setLastName(lName);
		member.setLogin(login);
		member.setPassword(pass);
		member.setAdmin(role);
		return member;
	}
}
