package com.omar.hubino.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.Resources;

/**
 * The Class GenesisValidator.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class GenesisValidator Created
 * 
 */

public class GenesisValidator {

	private static final GenesisValidator instance = new GenesisValidator();

	public static GenesisValidator getInstance() {
		return GenesisValidator.instance;
	}

	private GenesisValidator() {

	}

	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request, final Object bean) {
		final ActionErrors errors = new ActionErrors();
		final String validationKey = mapping.getAttribute();
		final ServletContext application = request.getSession()
				.getServletContext();
		final Validator validator = Resources.initValidator(validationKey,
				bean, application, request, errors, 0);
		try {
			validator.validate();
		} catch (final ValidatorException e) {
			e.printStackTrace();
		}
		return errors;
	}
}
