package com.omar.hubino.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.omg.CORBA.Request;

import com.omar.hubino.common.bo.UserDetails;
import com.omar.hubino.common.dao.UserDao;
import com.omar.hubino.common.form.LoginForm;
import com.omar.hubino.global.Constants;

public class LoginAction extends DispatchAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(InitAction.class);
		logInfo.info("Initilaize the application");

		LoginForm loginForm = (LoginForm) form;

		String forward = Constants.FORWARD_FAILURE;

		boolean auth = UserDao.validateSystemUserLogin(loginForm.getUserName(), loginForm.getPassword());
		
		if (auth) {
			UserDetails userDetails = UserDao.getDashBoardValues();
			request.setAttribute("userDetail", userDetails);
			HttpSession session = request.getSession(true);
			session.setAttribute("SESSION_HANDLER", "LIVE");
			forward = "dashboard";
		}else{
			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}

	public ActionForward dashboard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Logger logInfo = Logger.getLogger(InitAction.class);
		logInfo.info("Initilaize the application");
		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");
		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			UserDetails userDetails = UserDao.getDashBoardValues();
			request.setAttribute("userDetail", userDetails);
		} else {
			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(InitAction.class);
		logInfo.info("Initilaize the application");
		request.getSession().invalidate();
		return mapping.findForward("logout");
	}
}
