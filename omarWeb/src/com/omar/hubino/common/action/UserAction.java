package com.omar.hubino.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.omar.hubino.common.command.ProfileCommand;
import com.omar.hubino.common.dao.UserDao;
import com.omar.hubino.global.Constants;

public class UserAction extends DispatchAction {

	public ActionForward userInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");
		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			String userId = request.getParameter("userId");

			ProfileCommand command = new ProfileCommand();
			command.setUserId(userId);
			command.execute();
			request.setAttribute("userDetail", command.getUserDetails());

		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}

	public ActionForward userEnroll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");

		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			String userId = request.getParameter("userId");

			ProfileCommand command = new ProfileCommand();
			command.setUserId(userId);
			command.setFlag("ENROLL");
			command.execute();

			request.setAttribute("userDetail", command.getUserDetails());
			request.setAttribute("imgIds", command.getImageIds());

			forward = "enroll";
		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}

	public ActionForward userAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");

		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			String userId = request.getParameter("userId");

			ProfileCommand command = new ProfileCommand();
			command.setUserId(userId);
			command.setFlag("AUTH");
			command.execute();

			request.setAttribute("userDetail", command.getUserDetails());
			request.setAttribute("imgIds", command.getImageIds());
			request.setAttribute("trackList", command.getLogAtts());
			request.setAttribute("trackingID", command.getTrackId());

			forward = "auth";
		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}

	public ActionForward authLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");

		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {
			String userId = request.getParameter("userId");
			String trackID = request.getParameter("trackID");

			ProfileCommand command = new ProfileCommand();
			command.setUserId(userId);
			command.setTrackId(trackID);
			command.setFlag("AUTHLOG");
			command.execute();

			request.setAttribute("userDetail", command.getUserDetails());
			request.setAttribute("imgIds", command.getImageIds());
			request.setAttribute("trackList", command.getLogAtts());
			request.setAttribute("trackingID", trackID);
			request.setAttribute("logStatus", "logStatus");

			forward = "auth";
		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		return mapping.findForward(forward);
	}

	public ActionForward logDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");

		String forward = Constants.FORWARD_SUCCESS;

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {

			String userId = request.getParameter("userId");
			String trackID = request.getParameter("trackID");

			ProfileCommand command = new ProfileCommand();
			command.setUserId(userId);
			command.setTrackId(trackID);
			command.setFlag("AUTHDEL");
			command.execute();

		} else {

			forward = Constants.FORWARD_FAILURE;
		}

		return userAuth(mapping, form, request, response);
	}

	public ActionForward downloadImages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Logger logInfo = Logger.getLogger(UserAction.class);
		logInfo.info("Initilaize the application");

		HttpSession session = request.getSession(true);
		String sessionHandler = (String) session
				.getAttribute("SESSION_HANDLER");

		if (sessionHandler != null && sessionHandler.equals("LIVE")) {

			String trackID = request.getParameter("trackID");

			String folderName = UserDao.getImagePath(trackID);

			String outFilename = "/tmp/Report.zip"; // temp folder to put files
													// for
													// zipping
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outFilename));
			File zipFolder = new File(folderName); // Path of input folder

			int len = zipFolder.getAbsolutePath().lastIndexOf(File.separator);

			String baseName = zipFolder.getAbsolutePath().substring(0, len + 1);

			addFolderToZip(zipFolder, out, baseName);
			out.close();

			File zipFile = new File(outFilename);
			if (zipFile.exists()) {
				response.setContentType("application/zip");
				response.addHeader("Content-Disposition",
						"attachment; filename=\"Report.zip\"");
				FileInputStream in = new FileInputStream(zipFile);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] outputByte = new byte[4096];
				// copy binary content to output stream
				while (in.read(outputByte, 0, 4096) != -1) {
					outStream.write(outputByte, 0, 4096);
				}
				in.close();
				outStream.flush();
				outStream.close();
				zipFile.delete(); // comment here not to remove after zipping
			}
		}

		return null;
	}

	private static void addFolderToZip(File zipFolder, ZipOutputStream out,
			String baseName) throws IOException {
		File[] files = zipFolder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				addFolderToZip(file, out, baseName);
			} else {
				String name = file.getAbsolutePath().substring(
						baseName.length());
				ZipEntry zipEntry = new ZipEntry(name);
				out.putNextEntry(zipEntry);
				IOUtils.copy(new FileInputStream(file), out);
				out.closeEntry();
			}
		}
	}
}
