package com.omar.hubino.common.form;

import org.apache.struts.action.ActionForm;

public class MISForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId = null;
	
	private String userIdFlag = null;

	private String fromDate = null;
	
	private String fromDateFlag = null;
	
	private String toDate = null;
	
	private String toDateFlag = null;
	
	private String[] status = null;
	
	private String[] enrollStatus = null;
	
	private String enrolled = null;
	
	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	public String getNotErolled() {
		return notErolled;
	}

	public void setNotErolled(String notErolled) {
		this.notErolled = notErolled;
	}

	public String getLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(String loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public String getLoginFailure() {
		return loginFailure;
	}

	public void setLoginFailure(String loginFailure) {
		this.loginFailure = loginFailure;
	}

	private String notErolled = null;
	
	private String loginSuccess = null;
	
	private String loginFailure = null;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public String[] getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(String[] enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdFlag() {
		return userIdFlag;
	}

	public void setUserIdFlag(String userIdFlag) {
		this.userIdFlag = userIdFlag;
	}

	public String getFromDateFlag() {
		return fromDateFlag;
	}

	public void setFromDateFlag(String fromDateFlag) {
		this.fromDateFlag = fromDateFlag;
	}

	public String getToDateFlag() {
		return toDateFlag;
	}

	public void setToDateFlag(String toDateFlag) {
		this.toDateFlag = toDateFlag;
	}
			
			
}
