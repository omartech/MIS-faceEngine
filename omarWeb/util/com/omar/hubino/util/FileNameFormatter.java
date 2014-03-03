package com.omar.hubino.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.omar.hubino.global.Constants;

/**
 * The Class FileNameFormatter.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class FileNameFormatter Created
 * 
 */

public class FileNameFormatter {

	private static ConfigProperties objProbs = new ConfigProperties(
			"ApplicationResource.properties");

	public static String fileNameFormatter(String fileName, String sourceDir) {

		Date currDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				objProbs.getPropertyValues("renameFileFormate"));
		fileName = fileName.substring(0, fileName.length() - 4);
		String changedName = fileName + "_" + formatter.format(currDate)
				+ ".xml";
		return changedName;

	}

	public static String getNewFileName(String filePath) {
		String dir = filePath;
		int i = 0;
		String leadingZeros = "00000000";
		String fileName = "";
		File file = null;
		do {
			fileName = "" + i++;
			fileName = leadingZeros.substring(Math.min(fileName.length(),
					leadingZeros.length() - 1))
					+ fileName
					+ "."
					+ Constants.FILE_EXT_JPG;
			file = new File(dir + File.separator + fileName);
		} while (file.exists());
		return fileName;
	}

	public static String getNewDirectoryName() {
		Date currDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				objProbs.getPropertyValues("renameFileFormate"));
		String directoryName = formatter.format(currDate);
		return directoryName;
	}
}