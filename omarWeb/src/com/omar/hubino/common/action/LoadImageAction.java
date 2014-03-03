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

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.omar.hubino.common.dao.ImageDao;
import com.omar.hubino.common.dao.UserDao;

public class LoadImageAction extends Action {

	private static final Logger LOGGER = Logger
			.getLogger(LoadImageAction.class);

	public ActionForward execute(ActionMapping oMapping, ActionForm oForm,
			HttpServletRequest oRequest, HttpServletResponse oResponse)
			throws Exception {
		LOGGER.info("LoadImageAction execute() Starts");
		try {
			String trackingID = oRequest.getParameter("id");

			String filePath = ImageDao.getImagePath(trackingID);
			File oFile = new File(filePath);
			if (oFile.exists()) {

				FileInputStream fin = new FileInputStream(filePath);
				ServletOutputStream outStream = oResponse.getOutputStream();
				oResponse.setContentType("image/jpg");
				int i = 0;
				while (i != -1) {
					i = fin.read();
					outStream.write(i);
				}
				fin.close();

				LOGGER.info("LoadImageAction execute() Ends");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
