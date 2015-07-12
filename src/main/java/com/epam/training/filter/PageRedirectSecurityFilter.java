package com.epam.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code PageRedirectSecurityFilter} is a Filter that prohibits direct
 * access to the JSP pages and redirects such requests to the index page.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see javax.servlet.Filter
 */
public class PageRedirectSecurityFilter implements Filter {
	private String indexPath;

	/**
	 * Constructs PageRedirectSecurityFilter object
	 */
	public PageRedirectSecurityFilter() {
	}

	/**
	 * This method is invoked during Filter initialization. Here the index path
	 * parameter (value is stored as an init parameter) is set.
	 * 
	 * @param fConfig
	 *            javax.servlet.FilterConfig
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @see javax.servlet.Filter
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("indexPath");
	}

	/**
	 * Here the request that tries direct access to a JSP pages is redirected to
	 * the index (home) page.
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		/* redirecting to the index page */
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		chain.doFilter(request, response);
	}

	/**
	 * This method is invoked during Filter destruction.
	 * 
	 * @see javax.servlet.Filter
	 */
	public void destroy() {
	}
}
