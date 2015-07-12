package com.epam.training.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class {@code ResourceBundleTag} represents a custom tag class. It uses
 * ResourceBundle to get messages from properties files depending on the current
 * Locale. It is needed for internationalization support. The tag will be used
 * in JSPs to print messages stored in properties files.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class ResourceBundleTag extends TagSupport {
	private static final long serialVersionUID = 5551144223921546596L;
	private String message; // there will be 'message' attribute in the tag

	/**
	 * Sets the value of message field.
	 * 
	 * @param message
	 *            The content of 'message' attribute
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method uses ResourceBundle to get messages from properties files
	 * depending on the current Locale.
	 * 
	 * @return javax.servlet.jsp.tagext.Tag.SKIP_BODY
	 * @throws JspException
	 *             If a JSP write error occurred
	 */
	@Override
	public int doStartTag() throws JspException {
		Locale locale = Locale.getDefault();
		ResourceBundle rb = ResourceBundle.getBundle("text", locale);

		try {
			pageContext.getOut().write(rb.getString(message));
		} catch (IOException exception) {
			throw new JspException("Error. Unable to handle tag!", exception);
		}
		return SKIP_BODY;
	}
}
