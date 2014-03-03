package com.omar.hubino.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.omar.hubino.global.Constants;

public class InitAction extends DispatchAction {

	public ActionForward homeInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(InitAction.class);
		logInfo.info("Initilaize the application");
		return mapping.findForward(Constants.FORWARD_SUCCESS);
	}

	public ActionForward homeLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(InitAction.class);
		logInfo.info("Initilaize the login application");
		return mapping.findForward(Constants.FORWARD_SUCCESS_LOGIN);
	}

}
