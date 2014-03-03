package com.omar.hubino.global;

import com.omar.hubino.util.ConfigProperties;

/**
 * The Class Constants.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class Constants Created
 * 
 */

public class Constants {

	private static final ConfigProperties objProbs = new ConfigProperties(
			Constants.CONFIG_PROPERTIES);

	public static final String SSN_VERIFICATION = objProbs
			.getPropertyValues("SSN_VERIFICATION");

	public static final String IS_SSN_RQUIRED = "RQUIRED";

	public static final String SSN_MODE = objProbs
			.getPropertyValues("SSN_MODE");

	public static final String SSN_CO_CODE = objProbs
			.getPropertyValues("SSN_CO_CODE");

	public static final String SSN_PASSWORD = objProbs
			.getPropertyValues("SSN_PASSWORD");

	public static final String SSN_TYPE = objProbs
			.getPropertyValues("SSN_TYPE");

	/** The Constant FILE_EXT. - Mention with out '.' */

	private static final String CONFIG_PROPERTIES = "ApplicationResource.properties";

	public static final String FILE_EXT_JPG = "jpg";

	public static final String FILE_EXT_PNG = "png";
	
	public static final String IBSS = "IBSS";

	public static final String SECURITY_MAP = "securityMap";

	public static final String FORM_VALUE = "formValue";

	public static final String READ_ACCESS = "R";
	
	public static final String SSN_KEY_MODE = "Test";
	
	public static final String RESET_SUCCESS = "Password has been reset and sent to your e-MailId";
	
	public static final String RESET_FAILURE = "Please enter valid User Name";

	/**
	 * Action mapping Returns
	 */

	public static final String FORWARD_SUCCESS = "success";
	
	public static final String FORWARD_SUCCESS_LOGIN = "successLogin";

	public static final String CLIENT_SUCCESS = "clientSuccess";

	public static final String USER_ADDED = "userAdded";

	public static final String SEARCH_PASSWORD_COMPLETE = "searchPasswordComplete";

	public static final String SEARCH_COMPLETE = "searchComplete";

	public static final String SEARCH_INIT = "searchInit";
	
	public static final String LOG_TRACK_INIT = "logTrackInit";

	public static final String SEARCH_SUPER_CLIENT_INIT = "searchSuperClientInit";

	public static final String SEARCH__SUPER_INIT = "searchSuperInit";
	
	public static final String MY_PROFILE_INIT = "myProfileInit";
	
	public static final String MY_PROFILE_CHANGE_PWD_INIT = "myProfileChangePwdInit";

	public static final String SEARCH_CLIENT_COMPLETE = "searchClientCompleted";

	public static final String CLIENT_ADDED = "clientAdded";

	public static final String NOT_READY = "Not Ready";

	public static final String READY_FOR_REGITRATION = "Ready For Registration";

	public static final String COMPLETED = "Completed";

	public static final String USER_EDIT_COMPLETED = "UserEditCompleted";

	public static final String CLIENT_EDIT_COMPLETED = "ClientEditCompleted";

	public static final String CLIENT_UPDATE_COMPLETED = "ClientUpdateCompleted";

	public static final String PERCENTAGE_100 = "100%";

	public static final String PERCENTAGE_30 = "30%";

	public static final String PERCENTAGE_0 = "0%";

	public static final String VIEW = "view";

	public static final String EDIT = "edit";

	public static final String UPDATED_SUCCESSFUL = "updated successful";

	public static final String REGISTER_COMPLETE = "registerSuccess";

	public static final String CAPTURE_FACE_SCREEN = "captureFaceScreen";

	public static final String LOGIN_VALIDATED = "loginSuccessfulValidation";

	public static final String USER_SESSION_OBJECT = "loginSuccessfulValidation";

	public static final String LOGIN_VALIDATED_CHANGE_PASSWORD_FORWARD = "loginSuccessfulToChangePassword";

	public static final String PASSWORD_VALIDATED_REGISTER_FORWARD = "changePasswordToRegister";

	public static final String USRM_REGISTRATION_STATUS_NOR = "NOR";

	public static final String USRM_REGISTRATION_STATUS_RFR = "RFR";

	public static final String USRM_STATUS_CMP = "CMP";

	public static final String USRM_VALIDATE_FACE = "faceVerification";
	
	public static final String USRM_VALIDATE_FACE_LIVE = "faceVerificationLive";
	
	public static final String USRM_VALIDATE_FACE_LIVE_CREDIT = "faceVerificationLiveCredit";
	
	public static final String USRM_VALIDATE_FACE_LIVE_MEDICAL = "faceVerificationMedRec";

	public static final String INVALID_LOGIN = "logonFailure";
	
	public static final String INVALID_LOGIN_LIVE = "logonFailureLive";

	public static final String INVALID_PASSWORD = "invalidPassword";

	public static final String INVALID_REGISTER = "invalidRegister";

	public static final String INVALID_REGISTER_FIRST_NAME = "invalidFirstName";

	public static final String INVALID_FACE_COUNT = "invalidFaceCount";
	
	public static final String INVALID_CLIENT_NAME = "invalidClientName";

	public static final String INVALID_SSN_FAILED = "invalidSSNFailed";

	public static final String INVALID_SSN_ATTEMPT_THREE = "invalidSSNAttemptThree";

	public static final String INVALID_SSN_ATTEMPT_TWO = "invalidSSNAttemptTwo";

	public static final String USER_LOCKED_FOR_SSN = "invalidSSNLocked";

	public static final String CHANGE_PWD_VALIDATION_ERROR_MESSAGE = "Please fill all the mandatory fields.";

	public static final String USER_REG_ERROR = "userRegisterError";

	public static final String USER_REG_ERROR_SPACE = "userRegisterErrorSpace";

	public static final String REQUEST_ERROR_MESSAGE = "VALIDATION_ERROR";

	public static final String READY_FOR_VALIDATION = "readyForValidation";

	public static final String ERROR_FORWARD = "failure";

	public static final String CLIENT_DELETED = "clientDeleted";

	public static final String VIEW_USER = "viewUser";

	public static final String USER_UPDATED = "userUpdated";

	public static final String USER_DELETED = "userDeleted";

	public static final String VIEW_IMAGE_VALIDATION = "imageValidation";

	public static final String VIEW_IMAGE_VALIDATION_USER = "imageValidationUser";

	public static final String REGISTER_COMPLETED = "registerCompleted";

	public static final String FORWARD_CHANGE_PASSWORD = "changePassword";
	
	public static final String FORWARD_FORGOT_PASSWORD = "forgotPassword";

	public static final String IS_MANDATORY = "isMandatory";

	public static final String FORWARD_CHANGE_PASSWORD_CLIENT = "changePasswordClient";
	
	public static final String LIVE_DEMO_APP = "liveDemoApp";
	
	public static final String LIVE_DEMO_APP_INDEX = "liveDemoAppIndex";
	
	public static final String LIVE_DEMO_APP_CREDIT_INDEX = "liveDemoAppCreditIndex";
	
	public static final String LIVE_DEMO_APP_MEDREC_INDEX = "liveDemoAppMedRecIndex";
	
	public static final String LIVE_DEMO_APP_CREDIT_PAYMENT = "liveDemoAppCreditPayment";
	
	public static final String LIVE_DEMO_APP_CREDIT_LOGIN = "liveDemoAppCreditLogin";
	
	public static final String LIVE_DEMO_APP_LOGIN_DET = "liveDemoAppLoginDet";
	
	public static final String LIVE_DEMO_APP_ACCOUNT_DET = "liveDemoAppAccountDet";
	
	public static final String LIVE_DEMO_APP_CREDIT_SUCCESS = "liveDemoAppCreditSuccess";
	
	public static final String LIVE_DEMO_APP_MEDREC_SUCCESS = "liveDemoAppMedRecSuccess";

	public static final String FORWARD_USER_REGISTRATION = "customerRegistraion";

	public static final String FORWARD_USER_LOCKED = "forwardUserLockedForSSN";

	public static final String ADMIN_LOGIN = "adLogin";

	public static final String CUSTOMER_LOGIN = "cuLogin";

	public static final String CLIENT_ADD = "goToClientAdd";

	public static final String USER_ADD = "goToUserAdd";

	public static final String ERRORS = "errors";

	public static final String STR_FORMAT = "%%0%dd";

	public static final String CLIENT_PREFIX = "CL";

	public static final String USER_PREFIX = "USR";

	public static final String CLIENT_ID = "clientID";

	public static final String CLIENT_NAME = "clientName";
	
	public static final String ADMIN = "Admin";

	public static final String SSN_FAILURE = "ssnFailure";
	
	public static final String SSN_SUCCESS = "ssnSuccess";

	/*
	 * Tag Util Variables
	 */
	public static final String SESSION = "session";

	public static final String REQUEST = "request";

	public static final String WRITE_ACCESS = "W";

	public static final String DELETE_ACCESS = "D";

	public static final String INVALID_SESSION = "errors.inValidSession";

	public static final String INVALID_ACCESS = "errors.inValidAccess";

	public static final String SSN_ATTEMPT_TWO = "errors.inValidSSNTwo";

	public static final String SSN_ATTEMPT_THREE = "errors.inValidSSNThree";

	public static final String INVALID_INPUT = "errors.inMandatory";

	public static final String REGISTER_MANDATORY = "errors.inMand";

	public static final String REGISTER_FIRSTNAME_SPACE = "errors.invalidFirstName";

	public static final String FACE_COUNT_MANDATORY = "errors.faceCount";

	public static final String SSN_FAILED_ERROR = "errors.ssnFailed";

	public static final String ERROR_INVALID_PWD = "errors.invalidPwd";

	public static final String ERRORS_ISEXIST = "errors.isExist";

	public static final String ERRORS_MODFIED = "errors.isModified";

	public static final String ERRORS_EXCEPTION = "errors.exception";

	public static final String ISEXIST = "isExist";

	public static final String ID_MODIFIED = "idModified";

	public static final String ERRORS_IS_NOT_EXIST = "errors.isNotExist";

	public static final String FORWARD_INVALID_ACCESS = "invalidAccess";
	
	public static final String LOG_TRACK_INDEX = "logTrackIndex";

	public static final String ERROR_INVALID_ACCESS = "invalidAccess";

	public static final String ERROR_INVALID_SSNNO = "invalidSSNNO";

	public static final String LOGIC_ERROR = "logicError";

	public static final String EXCEPTION = "exception";

	public static final String ERRORS_UPDATE = "errorsUpdate";
	
	public static final String ERROR_CLIENT_NAME = "errors.clientName";

	public static final String FORWARD_FAILURE = "failure";

}
