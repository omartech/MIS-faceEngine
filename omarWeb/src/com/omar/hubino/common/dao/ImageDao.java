package com.omar.hubino.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.omar.hubino.util.DBUtil;

public class ImageDao {

	private static final String IMAGE_QUERY = "SELECT TI.TRACKING_IMG_PATH FROM tracking_img_info TI WHERE TI.TRACKING_IMG_ID = ? ";

	public static String getImagePath(int trackingID) {
		String path = "";

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			pstmt = connection.prepareStatement(ImageDao.IMAGE_QUERY);

			pstmt.setInt(1, trackingID);

			rst = pstmt.executeQuery();

			if (rst.next()) {
				path = rst.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (rst != null) {
					rst.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return path;
	}

	public static String getImagePath(String trackingID) {
		String path = "";
		try {
			int id = Integer.parseInt(trackingID);
			path = getImagePath(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
}
