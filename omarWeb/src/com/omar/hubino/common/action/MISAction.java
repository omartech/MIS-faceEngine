package com.omar.hubino.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.omar.hubino.common.command.MISCommand;
import com.omar.hubino.common.form.MISForm;
import com.omar.hubino.global.Constants;

public class MISAction extends DispatchAction{

	public ActionForward misInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(MISAction.class);
		logInfo.info("Initilaize the application");
		return mapping.findForward(Constants.FORWARD_SUCCESS);
	}
	
	public ActionForward misSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(MISAction.class);
		logInfo.info("Initilaize the application");
		
		MISForm misForm = (MISForm)form;
		
		String forward = Constants.FORWARD_SUCCESS;
		
		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			MISCommand command = new MISCommand();
			command.setForm(misForm);
			command.execute();
			
			request.setAttribute("recordList", command.getSearchResult());
		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		
		return mapping.findForward(forward);
	}
}
