package com.omar.hubino.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.omar.hubino.common.bo.AuthLogs;
import com.omar.hubino.common.bo.UserDetails;
import com.omar.hubino.util.DBUtil;

public class UserDao {
	
	private static final String VALIDATE_SYSTEM_USER_LOGIN = " SELECT 1 AND ( SELECT 1 FROM system_users WHERE " +
			"user_name = ? AND user_pwd = PASSWORD(?)) IS NOT NULL AS RETURN_CODE "; 

	private static final String LAST_LOGIN = "SELECT INSERTED_DT FROM cust_tracking_info WHERE ENROLL_VALID_TYPE = 'V' "
			+ "AND CUST_ID = ? ORDER BY INSERTED_DT DESC LIMIT 1 ";

	private static final String NO_OF_ATTEMPTS = "SELECT COUNT(*) FROM cust_tracking_info WHERE ENROLL_VALID_TYPE = 'V' AND CUST_ID = ? ";

	private static final String GET_DISPLAY_IMAGE = " SELECT 																"
			+ " 	TI.TRACKING_IMG_ID	                                            "
			+ " FROM                                                                "
			+ " 	cust_info CI, cust_tracking_info CT , tracking_img_info TI      "
			+ " WHERE                                                               "
			+ " 	CI.CUST_ID = CT.CUST_ID                                         "
			+ " AND CT.CUST_ID = ?                                              "
			+ " AND TI.TRACKING_ID = CT.TRACKING_ID LIMIT 1                         ";

	private static final String LIST_OF_IMG_IDS = " SELECT 																		"
			+ " 	TI.TRACKING_IMG_ID                                                      "
			+ " FROM                                                                        "
			+ " 	cust_info CI, cust_tracking_info CT , tracking_img_info TI              "
			+ " WHERE                                                                       "
			+ " 	CI.CUST_ID = CT.CUST_ID                                                 "
			+ " AND CT.CUST_ID = ?                                                      "
			+ " AND CT.ENROLL_VALID_TYPE = 'E'                                              "
			+ " AND CI.CUST_ENROLLMENT_IN = 'Y'                                             "
			+ " AND CT.STATUS_IN = 'S'                                                      "
			+ " AND TI.TRACKING_ID = CT.TRACKING_ID                                         ";

	private static final String GET_DATES = "SELECT TRACKING_ID,INSERTED_DT FROM cust_tracking_info WHERE ENROLL_VALID_TYPE = 'V' AND CUST_ID = ?";

	private static final String GET_AUTH_DISPLAY_ID = " SELECT TI.TRACKING_IMG_ID FROM tracking_img_info TI WHERE TI.TRACKING_ID  = (   "
			+ " SELECT TRACKING_ID                                                              "
			+ " 	FROM cust_tracking_info                                                      "
			+ " WHERE ENROLL_VALID_TYPE = 'V'                                                   "
			+ " 	AND CUST_ID = ? ORDER BY INSERTED_DT DESC LIMIT 1)                           ";

	private static final String GET_AUTH_IMG_IDS_BY_TRACK_ID = " SELECT TI.TRACKING_IMG_ID FROM tracking_img_info TI WHERE TI.TRACKING_ID  = ? ";

	private static final String GET_TRACK_ID = " SELECT TRACKING_ID                                                              "
			+ " 	FROM cust_tracking_info                                                      "
			+ " 	WHERE ENROLL_VALID_TYPE = 'V'                                                "
			+ " 	AND CUST_ID = ? ORDER BY INSERTED_DT DESC LIMIT 1                            ";

	private static final String DEL_TRACK_IMG = " DELETE FROM tracking_img_info WHERE TRACKING_ID = ? ";

	private static final String DEL_TRACK_SCORE = "DELETE FROM tracking_score WHERE TRACKING_ID = ? ";

	private static final String DEL_CUST_TRACK = " DELETE FROM cust_tracking_info WHERE TRACKING_ID = ? ";

	private static final String GET_IMAGE_PATH = " SELECT CI.TRACKING_IMG_PATH FROM cust_tracking_info CI WHERE CI.TRACKING_ID = ? ";

	/* Dashboard Records */

	private static final String TOTAL_NO_OF_USER = " SELECT COUNT(*) FROM cust_info ";

	private static final String ENROLLED_USER = " SELECT COUNT(*) FROM cust_info CI, cust_tracking_info CT	"
			+ " WHERE                                                       "
			+ " CI.CUST_ENROLLMENT_IN = 'Y'                                 "
			+ "  AND                                                        "
			+ " CT.ENROLL_VALID_TYPE = 'E' AND CT.STATUS_IN = 'S'           "
			+ " AND CI.CUST_ID = CT.CUST_ID                                 ";

	private static final String NOT_ENROLLED_USER = "  SELECT COUNT(*) FROM cust_info CI, cust_tracking_info CT						"
			+ "  WHERE                                                                         "
			+ "  CI.CUST_ENROLLMENT_IN = 'N'                                                   "
			+ "   AND                                                                          "
			+ "  CT.ENROLL_VALID_TYPE = 'E' AND (CT.STATUS_IN = 'F' OR CT.STATUS_IN IS NULL)   "
			+ "  AND CI.CUST_ID = CT.CUST_ID                                                   ";

	public static void deleteTrackingRecord(String trackingID) {

		Connection connection = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			DBUtil.getInstance().executeUpdate(DEL_TRACK_IMG,
					new Object[] { trackingID }, connection);

			DBUtil.getInstance().executeUpdate(DEL_TRACK_SCORE,
					new Object[] { trackingID }, connection);

			DBUtil.getInstance().executeUpdate(DEL_CUST_TRACK,
					new Object[] { trackingID }, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}
	}

	public static UserDetails getUserDetails(String userId) {
		Connection connection = null;
		UserDetails details = new UserDetails();

		try {
			connection = DBUtil.getInstance().getConnection();

			details.setLastLogin(DBUtil.getInstance().getValue(LAST_LOGIN,
					new Object[] { userId }, connection));
			details.setNumberOfAttempts(DBUtil.getInstance().getRowCount(
					NO_OF_ATTEMPTS, new Object[] { userId }, connection));
			details.setId(DBUtil.getInstance().getValue(GET_DISPLAY_IMAGE,
					new Object[] { userId }, connection));
			details.setUserId(userId);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return details;
	}

	public static String getLastTrackingID(String userId) {

		String trackingID = null;
		Connection connection = null;
		try {
			connection = DBUtil.getInstance().getConnection();
			trackingID = DBUtil.getInstance().getValue(GET_TRACK_ID,
					new Object[] { userId }, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return trackingID;
	}

	public static String getImagePath(String trackingID) {

		String imagePath = null;
		Connection connection = null;
		try {
			connection = DBUtil.getInstance().getConnection();
			imagePath = DBUtil.getInstance().getValue(GET_IMAGE_PATH,
					new Object[] { trackingID }, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return imagePath;
	}

	public static List<String> getImageIds(String userId) {

		List<String> ids = null;

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			pstmt = connection.prepareStatement(LIST_OF_IMG_IDS);

			pstmt.setString(1, userId);

			rst = pstmt.executeQuery();

			ids = new ArrayList<String>();

			while (rst.next()) {
				ids.add(rst.getString(1));
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

		return ids;
	}

	public static List<String> getAuthImageIds(String userId) {

		List<String> ids = null;

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			pstmt = connection.prepareStatement(GET_AUTH_DISPLAY_ID);

			pstmt.setString(1, userId);

			rst = pstmt.executeQuery();

			ids = new ArrayList<String>();

			while (rst.next()) {
				ids.add(rst.getString(1));
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

		return ids;
	}

	public static List<String> getAuthImageIdsByTrack(String trackId) {

		List<String> ids = null;

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			pstmt = connection.prepareStatement(GET_AUTH_IMG_IDS_BY_TRACK_ID);

			pstmt.setString(1, trackId);

			rst = pstmt.executeQuery();

			ids = new ArrayList<String>();

			while (rst.next()) {
				ids.add(rst.getString(1));
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

		return ids;
	}

	public static List<AuthLogs> getDates(String userId) {

		List<AuthLogs> loginLog = new ArrayList<AuthLogs>();

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;

		try {
			connection = DBUtil.getInstance().getConnection();

			pstmt = connection.prepareStatement(GET_DATES);

			pstmt.setString(1, userId);

			rst = pstmt.executeQuery();

			AuthLogs authLogs = null;

			while (rst.next()) {
				authLogs = new AuthLogs();
				authLogs.setTrackId(rst.getString(1));
				authLogs.setLogDate(rst.getString(2));
				loginLog.add(authLogs);
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

		return loginLog;
	}

	public static UserDetails getDashBoardValues() {

		Connection connection = null;

		UserDetails userDetails = new UserDetails();

		try {

			connection = DBUtil.getInstance().getConnection();

			userDetails.setTotalNumberOfUser(String.valueOf(DBUtil
					.getInstance().getRowCount(TOTAL_NO_OF_USER, connection)));
			userDetails.setEnrollmentUser(String.valueOf(DBUtil.getInstance()
					.getRowCount(ENROLLED_USER, connection)));
			userDetails.setNotEnrollmentUser(String.valueOf(DBUtil
					.getInstance().getRowCount(NOT_ENROLLED_USER, connection)));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return userDetails;
	}

	private static final String GET_ERROR_DESC = " SELECT ERROR_DESC FROM cust_tracking_info WHERE TRACKING_ID = ? ";

	private static final String GET_SCORE = " SELECT SCORE_VALUE FROM `tracking_score` WHERE TRACKING_ID = ? ";

	public static String getErrorDesc(String trackingID) {

		String desc = null;
		Connection connection = null;
		try {
			connection = DBUtil.getInstance().getConnection();
			desc = DBUtil.getInstance().getValue(GET_ERROR_DESC,
					new Object[] { trackingID }, connection);
			
			desc = (desc == null) ? "" : desc;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}

		return desc;
	}

	public static List<Double> getScore(String trackingID) {

		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		
		List<Double> scoreValue = new ArrayList<Double>();
		try {
			connection = DBUtil.getInstance().getConnection();
			pstmt = connection.prepareStatement(GET_SCORE);
			pstmt.setString(1, trackingID);
			
			/*score = DBUtil.getInstance().getValue(GET_SCORE,
					new Object[] { trackingID }, connection);
			
			score = (score == null) ? "" : score;*/
			
			rst = pstmt.executeQuery();
			
			while (rst.next()) {
				double scoreVal = rst.getDouble(1);
				scoreValue.add(scoreVal);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}
		return scoreValue;
	}

	private static final String GET_TRACKING_TIME_BY_ID = " SELECT CI.INSERTED_DT FROM cust_tracking_info CI WHERE CI.TRACKING_ID = ? ";
	
	public static String getTrackingDate(String trackingID) {

		String date = null;
		Connection connection = null;
		try {
			connection = DBUtil.getInstance().getConnection();
			date = DBUtil.getInstance().getValue(GET_TRACKING_TIME_BY_ID,
					new Object[] { trackingID }, connection);
			
			date = (date == null) ? "" : date;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception uncatched) {
				uncatched.printStackTrace();
			}
		}
		return date;
	}

	public static boolean validateSystemUserLogin(
			String userName,String password) {
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		boolean isRetval = false;
		try {
			connection = DBUtil.getInstance().getConnection();
			pstmt = connection
					.prepareStatement(VALIDATE_SYSTEM_USER_LOGIN);
			pstmt.setString(1,userName);
			pstmt.setString(2,password);
			rst = pstmt.executeQuery();
			if (rst.next()) {
				isRetval =rst.getBoolean("RETURN_CODE");
			}
		} catch (SQLException e) {
			isRetval = false;
			e.printStackTrace();
		} catch (Exception e) {
			isRetval = false;
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
				isRetval = false;
				uncatched.printStackTrace();
			}
		}
		return isRetval;
	}
}
