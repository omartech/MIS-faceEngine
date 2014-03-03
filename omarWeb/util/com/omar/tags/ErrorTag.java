package com.omar.tags;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.omar.hubino.util.TagUtil;

/**
 * The Class ErrorTag.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class ErrorTag Created
 * 
 */

public class ErrorTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private ActionErrors errors = null;

	private String name = null;

	private String scope = null;

	private String bundle = null;

	private String properties = null;

	private transient StringBuffer htmlString = null;

	private transient int count = 0;

	private static final Logger logger = Logger.getLogger(ErrorTag.class);

	@SuppressWarnings("rawtypes")
	@Override
	public int doStartTag() {

		final Object obj = TagUtil.getInstance().getValueFromScope(
				this.pageContext, this.getName(), this.getScope());
		this.count = 0;
		if ((obj != null) && (obj instanceof ActionErrors)) {
			this.errors = (ActionErrors) obj;
		}

		if (!this.getErrors().isEmpty()) {
			this.htmlString = new StringBuffer();
			final Iterator messageItr = this.errors.get();
			final Iterator propertyItr = this.errors.properties();
			ActionMessage actionMessage = null;
			Object[] keyValues = null;
			while (messageItr.hasNext()) {
				actionMessage = (ActionMessage) messageItr.next();
				final String property = (String) propertyItr.next();
				if (this.getProperties().indexOf(property) != -1) {
					final String errorKey = actionMessage.getKey();
					keyValues = actionMessage.getValues();
					final String stringFromResource = TagUtil.getInstance()
							.getMessage(this.pageContext, this.getBundle(),
									errorKey);
					this.witeHtml(
							TagUtil.getInstance()
									.formatMessage(stringFromResource,
											(String[]) keyValues).trim(),
							this.htmlString);
				}
			}
		}

		return Tag.EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		final JspWriter out = this.pageContext.getOut();
		try {
			this.htmlString
					.insert(0,
							"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">");
			this.htmlString.append("</table>");
			out.print(this.htmlString.toString());
		} catch (final IOException e) {
			ErrorTag.logger.error(e.getMessage());
		}
		return Tag.EVAL_PAGE;
	}

	private void witeHtml(final String content, final StringBuffer htmlString) {
		this.count++;
		htmlString.append("<tr><td class=\"errorMessage dataTextBox\">"
				+ this.count + " . &nbsp;" + content + "</td></tr>");
	}

	public ActionErrors getErrors() {
		return this.errors;
	}

	public void setErrors(final ActionErrors errors) {
		this.errors = errors;
	}

	public String getProperties() {
		return this.properties;
	}

	public void setProperties(final String properties) {
		this.properties = properties;
	}

	public String getBundle() {
		return this.bundle;
	}

	public void setBundle(final String bundle) {
		this.bundle = bundle;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(final String scope) {
		this.scope = scope;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
