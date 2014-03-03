package com.omar.hubino.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.Resources;

import com.omar.hubino.global.Constants;

/**
 * The Class TagUtil.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class TagUtil Created
 * 
 */

public class TagUtil {

	public static final TagUtil instance = new TagUtil();

	public static TagUtil getInstance() {
		return TagUtil.instance;
	}

	/**
	 * 
	 * @param servletcontext
	 * @param request
	 * @param resourceBundle
	 * @param resourceKey
	 * @return
	 */
	public String getMessage(final ServletContext servletcontext,
			final HttpServletRequest request, final String resourceBundle,
			final String resourceKey) {
		String message = null;
		try {
			final MessageResources messageResource = Resources
					.getMessageResources(servletcontext, request,
							resourceBundle);
			if (messageResource != null) {
				message = messageResource.getMessage(resourceKey);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	/**
	 * 
	 * @param request
	 * @param resourceKey
	 * @return
	 */
	public String getMessage(final HttpServletRequest request,
			final String resourceKey) {
		String message = null;

		try {
			final MessageResources messageResource = Resources
					.getMessageResources(request);
			if (messageResource != null) {
				message = messageResource.getMessage(resourceKey);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * 
	 * @param pageContext
	 * @param bundle
	 * @param resourceKey
	 * @return
	 */
	public String getMessage(final PageContext pageContext,
			final String bundle, final String resourceKey) {
		String message = null;
		try {
			final MessageResources messageResource = TagUtils.getInstance()
					.retrieveMessageResources(pageContext, bundle, false);
			if (messageResource != null) {
				message = messageResource.getMessage(resourceKey);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * 
	 * @param pageContext
	 * @param name
	 * @param scope
	 * @return
	 */
	public Object getValueFromScope(final PageContext pageContext,
			final String name, final String scope) {

		Object returnObject = null;
		if ((scope != null) && (name != null)) {
			if (scope.equals(Constants.SESSION)) {
				final HttpSession session = pageContext.getSession();
				returnObject = session.getAttribute(name);
			} else if (scope.equals(Constants.REQUEST)) {
				returnObject = pageContext.getRequest().getAttribute(name);
			}
		} else if (name != null) {
			returnObject = pageContext.getRequest().getAttribute(name);
		}
		if (returnObject == null) {
			returnObject = pageContext.getRequest().getAttribute(
					Constants.ERRORS);
		}
		return returnObject;
	}

	/**
	 * 
	 * @param stringFromResource
	 * @param keyValues
	 * @return
	 */
	public String formatMessage(final String stringFromResource,
			final String[] keyValues) {
		String resourceString = stringFromResource;
		final String[] replaceStrings = keyValues;
		if ((resourceString != null) && (keyValues != null)) {
			for (int j = 0; j < replaceStrings.length; j++) {
				final String validateString = "{" + j + "}";
				if (resourceString.indexOf(validateString) != -1) {
					resourceString = TagUtil.getReplacedString(validateString,
							resourceString, replaceStrings[j]);
				}
			}
		}

		return resourceString;
	}

	/**
	 * 
	 * @param validateString
	 * @param strFromMr
	 * @param replaceString
	 * @return
	 */
	private static String getReplacedString(final String validateString,
			final String strFromMr, final String replaceString) {
		String returnString = "";
		final String[] strFromMsR = strFromMr.split(" ");
		for (final String messageString : strFromMsR) {
			if (messageString.equals(validateString)) {
				returnString = returnString + " " + replaceString;
			} else {
				returnString = returnString + " " + messageString;
			}
		}
		return returnString;
	}
}
