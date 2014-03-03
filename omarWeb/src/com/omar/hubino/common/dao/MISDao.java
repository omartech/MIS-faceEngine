package com.omar.hubino.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.omar.hubino.common.bo.MisRecord;
import com.omar.hubino.common.form.MISForm;
import com.omar.hubino.util.DBUtil;

public class MISDao {
	
	public static final String SEARCH = "       SELECT 									"
			+ "       	CI.CUST_ID Customer_ID,                                         "
			+ "       	CT.TRACKING_IMG_PATH Image_Path,                                "
			+ "       	CI.CUST_ENROLLMENT_IN Enrollment_status,                        "
			+ "       	CT.ENROLL_VALID_TYPE Enroll_Type,                               "
			+ "       	CT.STATUS_IN Record_status,                                     "
			+ "       	CT.INSERTED_DT Record_date                                      "
			+ "       FROM                                                              "
			+ "          cust_info CI,                                                  "
			+ "          cust_tracking_info CT                                          "
			+ "       WHERE                                                             "
			+ "       	CI.CUST_ID = CT.CUST_ID                                         "
			+ "       AND                                                               "
			+ "       	CI.CUST_ID =                                                    "
			+ "       	 (SELECT CASE                                                   "
			+ "       		 	WHEN ( 'UserID' = ? ) THEN ?		 	                "
			+ "       		ELSE CI.CUST_ID                                             "
			+ "       		END)                                                        "
			+ "       AND                                                               "
			+ "       	CI.CUST_ENROLLMENT_IN = (                                       "
			+ "       		SELECT CASE                                                 "
			+ "       		 	WHEN ('Enrolled' = ?) THEN 'Y'                          "
			+ "       		 	WHEN ('notEnrolled' = ?) THEN 'N'                       "
			+ "       		 	WHEN ('success' = ? OR 'failure' = ?) THEN 'Y'          "
			+ "        		ELSE CI.CUST_ENROLLMENT_IN                                  "
			+ "       		END                                                         "
			+ "       	)                                                               "
			+ "       AND                                                               "
			
			/* ENROLL_VALID_TYPE */
			+ "   	(																	"
			+ "   	CT.ENROLL_VALID_TYPE = (                                            "
			+ "   		 SELECT CASE                                                    "
			+ "   		 	WHEN ('Enrolled' = ?) THEN 'E'                           "
			+ "   		 	WHEN ('notEnrolled' = ?) THEN 'E'                        "
			+ "   		 	WHEN ('success' = ?) THEN 'V'                            "
			+ "   		 	WHEN ('failure' = ?) THEN 'V'                       "
			+ "   		 ELSE CT.ENROLL_VALID_TYPE                                      "
			+ "   		END                                                             "
			+ "   	)                                                                   "
			+ "   	OR                                                                  "
			+ "   		CT.ENROLL_VALID_TYPE = (                                        "
			+ "   		 SELECT CASE                                                    "
			+ "   		 	WHEN ('Enrolled' = ? AND 'success' = ?) THEN 'E'      "
			+ "   		 	WHEN ('Enrolled' = ? AND 'failure' = ?) THEN 'E'		"	 
			+ "   		END                                                             "
			+ "   	))	                                                                "
			
			/* STATUS_IN */
			
			+ "       AND (                                                             "
			+ "       	CT.STATUS_IN = (                                                "
			+ "       	SELECT CASE                                                     "
			+ "       			WHEN ('Enrolled' = ?) THEN 'S'                          "
			+ "       			WHEN ('notEnrolled' = ?) THEN 'F'                       "
			+ "       			WHEN ('success' = ?) THEN 'S'                           "
			+ "       			WHEN ('failure' = ?) THEN 'F'                           "
			+ "       		ELSE CT.STATUS_IN	 	                                    "
			+ "       	END                                                             "
			+ "       	)                                                               "
			
			+ "  	  OR																"
			+ "       	CT.STATUS_IN = (                                                "
			+ "       	SELECT CASE                                                     "
//			+ "       			WHEN ('Enrolled' = ?) THEN 'S'                          "
			+ "       			WHEN ('notEnrolled' = ?) THEN 'F'                       "
			+ "       			WHEN ('success' = ?) THEN 'S'                           "
			+ "       			WHEN ('failure' = ?) THEN 'F'                           "
			+ "       	END                                                             "
			+ "       	)                                                               "
			
			+ "  	  OR																"
			+ "       	CT.STATUS_IN = (                                                "
			+ "       	SELECT CASE                                                     "
//			+ "       			WHEN ('Enrolled' = ?) THEN 'S'                          "
//			+ "       			WHEN ('notEnrolled' = ?) THEN 'F'                       "
			+ "       			WHEN ('success' = ?) THEN 'S'                           "
			+ "       			WHEN ('failure' = ?) THEN 'F'                           "
			+ "       	END                                                             "
			+ "       	)                                                               "
			
			+ "  	  OR																"
			+ "       	CT.STATUS_IN = (                                                "
			+ "       	SELECT CASE                                                     "
			//+ "       			WHEN ('Enrolled' = ?) THEN 'S'                          "
			//+ "       			WHEN ('notEnrolled' = ?) THEN 'F'                       "
//			+ "       			WHEN ('success' = ?) THEN 'S'                           "
			+ "       			WHEN ('failure' = ?) THEN 'F'                           "
			+ "       	END                                                             "
			+ "       	)                                                               "
			
			+ "  	  OR																"
			+ "         (                                                               "
			+ "          SELECT CASE                                                    "
			+ "     	        WHEN ('failure' = ?) THEN 	CT.STATUS_IN IS NULL        "
			+ "       			WHEN ('notEnrolled' = ?) THEN CT.STATUS_IN IS NULL      "
			/*+ "           ELSE CT.STATUS_IN IS NOT NULL                                  "*/
			+ "         END                                                             "
			+ "  	    )                                                               "
			
			/*+ "  	  OR																"
			+ "         (                                                               "
			+ "          SELECT CASE                                                    "
			+ "     	        WHEN ('success' = ?) THEN 	CT.STATUS_IN IS NOT NULL    "
			+ "       			WHEN ('Enrolled' = ?) THEN CT.STATUS_IN IS NOT NULL     "
			+ "          ELSE CT.STATUS_IN IS NULL                                      "
			+ "         END                                                             "
			+ "  	    )                                                               "*/
			
			
			/*+ "   	  OR																"
			+ "   		(                                                               "
			+ "   		SELECT CASE                                                     "
			+ "   	 			WHEN ( 'UserID' = ? ) THEN CT.STATUS_IN IS NULL		 	                "
			+ "   		ELSE CT.STATUS_IN IS NULL                                       "
			+ "   		END                                                             "
			+ "   	)                                                                   "*/
			+ "  	    )                                                               "
			
			/*+ "   	AND																	"
			+ "         (                                                               "
			+ "          SELECT CASE                                                    "
			+ "     	        WHEN ('success' = ?) THEN 	CT.STATUS_IN IS NOT NULL    "
			+ "       			WHEN ('Enrolled' = ?) THEN CT.STATUS_IN IS NOT NULL     "
			+ "          ELSE CT.STATUS_IN IS NULL                                      "
			+ "         END                                                             "
			+ "  	    )                                                               "*/
			
			/* INSERTED_DT */
			
			+ "   	AND																	"
			+ "   	CT.INSERTED_DT between                                              "
			+ "   	(                                                                   "
			+ "   		SELECT CASE                                                     "
			+ "   	 		WHEN ( 'FromDate' = ? ) THEN 	?	 						"
			+ "   		ELSE '0000-00-00 00:00:00'                                      "
			+ "   		END                                                             "
			+ "   	)                                                                   "
			+ "   	AND                                                                 "
			+ "   	(                                                                   "
			+ "   		SELECT CASE                                                     "
			+ "   	 		WHEN ( 'ToDate' = ? ) THEN 	?	 	    					"
			+ "   		ELSE '9999-00-00 00:00:00'                                      "
			+ "   		END                                                             "
			+ "   	)                                                                   ";
	
	public static List<MisRecord> searchMIS(MISForm form) {
		
		List<MisRecord> recordList = new ArrayList<MisRecord>();
		
		MisRecord record = null;
		
		Connection connection = null;
		ResultSet rst = null;
		PreparedStatement pstmt = null;
		
		/*System.out.println(form.getUserIdFlag() + "::" + form.getUserId() + "::" + form.getEnrolled() + "::" +
				form.getNotErolled() + "::" + form.getLoginSuccess() + "::" + form.getLoginFailure());*/
		
		try{
			connection = DBUtil.getInstance().getConnection();
			pstmt = connection.prepareStatement(MISDao.SEARCH);
			
			pstmt.setString(1, form.getUserIdFlag());
			pstmt.setString(2, form.getUserId());
			
			pstmt.setString(3, form.getEnrolled());
			pstmt.setString(4, form.getNotErolled());
			pstmt.setString(5, form.getLoginSuccess());
			pstmt.setString(6, form.getLoginFailure());
			
			/* ENROLL_VALID_TYPE */
			pstmt.setString(7, form.getEnrolled());
			pstmt.setString(8, form.getNotErolled());
			pstmt.setString(9, form.getLoginSuccess());
			pstmt.setString(10, form.getLoginFailure());
			pstmt.setString(11, form.getEnrolled());
			pstmt.setString(12, form.getLoginSuccess());
			pstmt.setString(13, form.getEnrolled());
			pstmt.setString(14, form.getLoginFailure());
			
			/* STATUS_IN */
			pstmt.setString(15, form.getEnrolled());
			pstmt.setString(16, form.getNotErolled());
			pstmt.setString(17, form.getLoginSuccess());
			pstmt.setString(18, form.getLoginFailure());
			
//			pstmt.setString(19, form.getEnrolled());
			pstmt.setString(19, form.getNotErolled());
			pstmt.setString(20, form.getLoginSuccess());
			pstmt.setString(21, form.getLoginFailure());
			
//			pstmt.setString(23, form.getEnrolled());
//			pstmt.setString(24, form.getNotErolled());
			pstmt.setString(22, form.getLoginSuccess());
			pstmt.setString(23, form.getLoginFailure());
			
			pstmt.setString(24, form.getLoginFailure());
			
			pstmt.setString(25, form.getLoginFailure());
			pstmt.setString(26, form.getNotErolled());
			
			/*pstmt.setString(21, form.getUserIdFlag());*/
			
			/*pstmt.setString(22, form.getLoginSuccess());
			pstmt.setString(23, form.getEnrolled());*/
			
			
			/*pstmt.setString(21, form.getUserId());*/
			
			pstmt.setString(27, form.getFromDateFlag());
			pstmt.setString(28, form.getFromDate());
			
			pstmt.setString(29, form.getToDateFlag());
			pstmt.setString(30, form.getToDate());
			
			
			rst = pstmt.executeQuery();
			
			while (rst.next()) {
				
				record = new MisRecord();
				record.setUserId(rst.getString("Customer_ID"));
				record.setEnrollmentPath(rst.getString("Image_Path"));
				
//				record.setEnrollType(rst.getString("Enroll_Type"));
				
				record.setEnrollmentStatus(rst.getString("Enrollment_status")) ;
				
				String enrollmentType = rst.getString("Enroll_Type");
				enrollmentType = enrollmentType.equals("E") ? "Enrollment" : "Authentication";
				record.setEnrollType(enrollmentType);
				

				String status = rst.getString("Record_status");
				
				if(status != null && status.equals("S")){
					
					status = "Success";
					
				}else{
					
					status = "Failure";
					
				}
				
				record.setStatus(status);
				record.setDataTime(rst.getString("Record_date"));
				
				recordList.add(record);
				
			}
			
//			System.out.println("Retrived Record ::"+recordList.size());
						
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
		
		return recordList;
	}
}
