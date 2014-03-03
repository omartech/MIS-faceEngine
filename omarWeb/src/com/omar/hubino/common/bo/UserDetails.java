package com.omar.hubino.common.bo;

public class UserDetails {

	private String userId = null;
	
	private int numberOfAttempts;
	
	private String lastLogin = null;
	
	private String trackingTime = null;
	
	private String id = null;
	
	public String getTotalNumberOfUser() {
		return totalNumberOfUser;
	}

	public void setTotalNumberOfUser(String totalNumberOfUser) {
		this.totalNumberOfUser = totalNumberOfUser;
	}

	public String getEnrollmentUser() {
		return enrollmentUser;
	}

	public void setEnrollmentUser(String enrollmentUser) {
		this.enrollmentUser = enrollmentUser;
	}

	public String getNotEnrollmentUser() {
		return notEnrollmentUser;
	}

	public void setNotEnrollmentUser(String notEnrollmentUser) {
		this.notEnrollmentUser = notEnrollmentUser;
	}

	private String totalNumberOfUser = null;
	
	private String enrollmentUser = null;
	
	private String notEnrollmentUser = null;
	
	private String errorDesc = null;
	
	private String score = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(int numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTrackingTime() {
		return trackingTime;
	}

	public void setTrackingTime(String trackingTime) {
		this.trackingTime = trackingTime;
	}

}
