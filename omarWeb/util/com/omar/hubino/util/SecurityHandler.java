package com.omar.hubino.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.omar.hubino.global.Constants;

/**
 * The Class SecurityHandler.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class SecurityHandler Created
 * 
 */

public class SecurityHandler {

	private static final SecurityHandler instance = new SecurityHandler();

	public static SecurityHandler getInstance() {
		return SecurityHandler.instance;
	}

	/**
	 * Security Handler
	 * 
	 * @param request
	 * @param formValue
	 * @param access
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean securityCheck(final HttpServletRequest request,
			String formValue, final String access) {
		boolean returnValue = false;
		if (request != null) {
			final HttpSession session = request.getSession(true);
			final HashMap<String, String> securityMap;
			securityMap = (HashMap<String, String>) session
					.getAttribute(Constants.SECURITY_MAP);
			formValue = formValue == null ? (String) request
					.getParameter(Constants.FORM_VALUE) : formValue;
			returnValue = this.hasAccessRights(securityMap, formValue, access);
		}
		return returnValue;
	}

	/**
	 * 
	 * @param securityMap
	 * @param formValue
	 * @return
	 */
	public boolean hasRights(final HashMap<String, String> securityMap,
			final String formValue) {
		boolean returnValue = false;
		if ((formValue != null) && securityMap.containsKey(formValue)) {
			final String secStr = securityMap.get(formValue).toString();
			if (secStr != null) {
				returnValue = !secStr.endsWith("N");
			}
		}
		return returnValue;
	}

	/**
	 * 
	 * @param securityMap
	 * @param formValue
	 * @param access
	 * @return
	 */
	public boolean hasAccessRights(final HashMap<String, String> securityMap,
			final String formValue, final String access) {
		boolean returnValue = false;
		if ((formValue != null) && securityMap != null
				&& securityMap.containsKey(formValue)) {
			final String secStr = securityMap.get(formValue).toString();
			if (secStr != null) {
				if (-1 != secStr.indexOf(access)) {
					returnValue = true;
				}
			}
		}
		return returnValue;
	}
}
