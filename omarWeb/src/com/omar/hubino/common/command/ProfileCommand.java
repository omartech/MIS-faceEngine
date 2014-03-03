package com.omar.hubino.common.command;

import java.util.List;

import com.omar.hubino.common.bo.AuthLogs;
import com.omar.hubino.common.bo.UserDetails;
import com.omar.hubino.common.dao.UserDao;

public class ProfileCommand {

	private String userId = null;
	
	private UserDetails userDetails = null;
	
	private String trackId = null;
	
	private List<String> imageIds;
	
	private List<AuthLogs> logAtts;
	
	private String flag = "";
	
	public void execute(){
		userDetails = UserDao.getUserDetails(this.userId);
		
		if(flag.equals("ENROLL")){
			setImageIds(UserDao.getImageIds(this.userId));
		}
		
		if(flag.equals("AUTH")){
			setLogAtts(UserDao.getDates(getUserId()));
			setImageIds(UserDao.getAuthImageIds(this.userId));
			setTrackId(UserDao.getLastTrackingID(this.userId));
		}
		
		if(flag.equals("AUTHLOG")){
			setLogAtts(UserDao.getDates(getUserId()));
			setImageIds(UserDao.getAuthImageIdsByTrack(trackId));
			userDetails.setErrorDesc(UserDao.getErrorDesc(trackId));
			userDetails.setScore(String.valueOf(calculateScore(UserDao.getScore(trackId))));
			userDetails.setTrackingTime(UserDao.getTrackingDate(trackId));
		}
		
		if(flag.equals("AUTHDEL")){
			UserDao.deleteTrackingRecord(trackId);
		}
		
	}

	private int calculateScore(List<Double> faceScore){
		int countOne = 0;
		for(int i=0;i<faceScore.size();i++)
		{
		    if(faceScore.get(i) >= 0.9999995)
		        countOne+=1;
		}
		return countOne;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<String> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<AuthLogs> getLogAtts() {
		return logAtts;
	}

	public void setLogAtts(List<AuthLogs> logAtts) {
		this.logAtts = logAtts;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

			
}
