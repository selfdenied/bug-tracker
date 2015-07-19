package com.epam.training.tag;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class {@code HeaderTag} represents a custom tag class. It prints a bunch of
 * information as a page header, including info about Member's access mode. It
 * also adds common menu and Admin's menu in case when the value of 'role'
 * attribute corresponds to the Administrator role.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class HeaderTag extends TagSupport {
	private static final long serialVersionUID = -2744099822640443064L;
	private String role; // there will be 'role' attribute in the tag

	/**
	 * Sets the value of role field.
	 * 
	 * @param role
	 *            The content of 'role' attribute
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Method adds some fragment JSPs to the current page.
	 * 
	 * @return javax.servlet.jsp.tagext.Tag.SKIP_BODY
	 * @throws JspException
	 *             If a JSP write/include error occurred
	 */
	@Override
	public int doStartTag() throws JspException {
		boolean admin = Boolean.parseBoolean(role);

		try {
			if (admin) {
				pageContext.include("/jsp/common/fragment/adminMenu.jsp");
			}
			pageContext.include("/jsp/member/fragment/greeting.jsp");
			pageContext.include("/jsp/member/fragment/accessMode.jsp");
			pageContext.getOut().write("<br><br>");
			pageContext.include("/jsp/common/fragment/smallMenu.jsp");
			pageContext.getOut().write("<br><br>");
		} catch (ServletException ex) {
			throw new JspException("Error. Unable to handle tag!", ex);
		} catch (IOException ex) {
			throw new JspException("Error. Unable to handle tag!", ex);
		}
		return SKIP_BODY;
	}
}
