package com.omar.hubino.common.command;

import java.util.List;

import com.omar.hubino.common.bo.MisRecord;
import com.omar.hubino.common.dao.MISDao;
import com.omar.hubino.common.form.MISForm;

public class MISCommand {

	private MISForm form = null;

	private List<MisRecord> searchResult = null;

	public void execute() {

		if (form != null) {

			String[] status = form.getStatus();

			if (status != null && status.length > 0) {
				for (int i = 0; i < status.length; i++) {
					String statusFlag = status[i];
					if (statusFlag.equals("success")) {
						form.setLoginSuccess(statusFlag);
					} else if (statusFlag.equals("failure")) {
						form.setLoginFailure(statusFlag);
					}
				}
			}

			String[] enrollStatus = form.getEnrollStatus();

			if (enrollStatus != null && enrollStatus.length > 0) {
				for (int i = 0; i < enrollStatus.length; i++) {
					String enStatus = enrollStatus[i];
					if (enStatus.equals("Enrolled")) {
						form.setEnrolled(enStatus);
					} else if (enStatus.equals("notEnrolled")) {
						form.setNotErolled(enStatus);
					}
				}
			}
			
			if(form.getUserId() != null && !form.getUserId().equals("")){
				form.setUserIdFlag("UserID");
			}
			
			if(form.getFromDate() != null && !form.getFromDate().equals("")){
				form.setFromDateFlag("FromDate");
			}
			
			if(form.getToDate() != null && !form.getToDate().equals("")){
				form.setToDateFlag("ToDate");
			}
			
			searchResult = MISDao.searchMIS(form);

		}

	}

	public MISForm getForm() {
		return form;
	}

	public void setForm(MISForm form) {
		this.form = form;
	}

	public List<MisRecord> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<MisRecord> searchResult) {
		this.searchResult = searchResult;
	}
}
