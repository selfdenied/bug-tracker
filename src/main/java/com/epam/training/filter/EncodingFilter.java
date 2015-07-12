package com.epam.training.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Class {@code EncodingFilter} is a Filter that sets the 'UTF-8' character
 * encoding for Request and Response objects.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see javax.servlet.Filter
 */
public class EncodingFilter implements Filter {
	private String code;

	/**
	 * Constructs EncodingFilter object
	 */
	public EncodingFilter() {
	}

	/**
	 * This method is invoked during Filter initialization. Here the encoding
	 * parameter (value is stored as an init parameter) is set.
	 * 
	 * @param fConfig
	 *            javax.servlet.FilterConfig
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @see javax.servlet.Filter
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");
	}

	/**
	 * Here the encoding for Request and Response objects is set.
	 * 
	 * @param request
	 *            javax.servlet.ServletRequest
	 * @param response
	 *            javax.servlet.ServletResponse
	 * @param chain
	 *            javax.servlet.FilterChain
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @throws IOException
	 *             When an I/O exception of some sort has occurred
	 * @see javax.servlet.Filter
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	/**
	 * This method is invoked during Filter destruction. Here the encoding
	 * parameter is set to 'null'.
	 * 
	 * @see javax.servlet.Filter
	 */
	public void destroy() {
		code = null;
	}
}
