
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<!DOCTYPE html>
<html>
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>MIS</title>
<meta name="description" content="Page Description">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/92bc1fe4.bootstrap.css">

<!-- Page-specific Plugin CSS: -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/select2.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/datatables.css"
	media="screen">
<!-- Proton CSS: -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/aaf5c053.proton.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/animate.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/aaf5c053.css">
<!-- adds CSS media query support to IE8   -->
<!--[if lt IE 9]>
            <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
            <script src="scripts/vendor/respond.min.js"></script>
        <![endif]-->

<!-- Fonts CSS: -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/6227bbe5.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/40ff7bd7.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/aaf5c053.css"
	type="text/css">
<!-- Common Scripts: -->
<script>
	(function() {
		var js;
		if (typeof JSON !== 'undefined' && 'querySelector' in document
				&& 'addEventListener' in window) {
			js = '//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js';
		} else {
			js = '//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js';
		}
		document.write('<script src="' + js + '"><\/script>');
	}());
</script>
<script src="<%=request.getContextPath()%>/public/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/public/js/modernizr.js"></script>



</head>

<body>


	<div aria-hidden="true" tabindex="-1" role="dialog">
		<div class="modal-dialog" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title">
						<i class="icon-user"></i><span>Authentication Images</span>
					</h4>
				</div>
				<div class="modal-body">

					<logic:notEmpty name="userDetail">
						<div class="profile_picture" id="auth">
							<div class="form_right" style="flaot: right;">
								Authentication History :

								<logic:notEmpty name="trackList">
									<select class="select2"
										onchange="javascript: location.href = '<%=request.getContextPath()%>/userInfo.do?method=authLog&userId=<bean:write name="userDetail" property="userId" />&trackID='+this.value;"
										style="border: 1px solid #E2E2E2; border-radius: 3px; font-family: sans-serif; height: 24px; vertical-align: middle; width: 180px;">
										<option value="">Select the login time</option>
										<logic:iterate name="trackList" id="logTrack">
											<option
												value="<bean:write name="logTrack" property="trackId"/>">
												<bean:write name="logTrack" property="logDate" />
											</option>
										</logic:iterate>
									</select>
								</logic:notEmpty>
							</div>

							<br>

							<logic:notEmpty name="imgIds">
								<logic:iterate name="imgIds" id="ids">
									<span> <img
										src="<%=request.getContextPath()%>/loadImage.do?id=<bean:write name="ids"/>"
										width="120" height="160" />
									</span>&nbsp;
						 		</logic:iterate>
							</logic:notEmpty>

							<div style="height: 10px;"></div>

							<logic:present name="trackingID" scope="request">
							
								<button type="button" class="btn btn-lg btn-default"
									style="background-color: red;"
									onclick="javascript: location.href ='<%=request.getContextPath()%>/userInfo.do?method=logDelete&userId=<bean:write name="userDetail" property="userId"/>&trackID=<bean:write name="trackingID" scope="request"/>';">

									Delete Images & Record</button>
								
								<button type="button" class="btn btn-lg btn-success"
								
									onclick="javascript: location.href ='<%=request.getContextPath()%>/userInfo.do?method=downloadImages&trackID=<bean:write name="trackingID" scope="request"/>';">

									Download Images</button>
									
							</logic:present>

						</div>



						<div class="add_user">
							<div class="user_left">
								<label><b>User ID</b></label>&nbsp;:
							</div>
							<div class="user_right">
								<bean:write name="userDetail" property="userId" />
							</div>

							<div class="user_left">
								<label><b>Number of Attempts</b></label>&nbsp;:
							</div>
							<div class="user_right">
								<bean:write name="userDetail" property="numberOfAttempts" />
							</div>

							<div class="user_left">
								<label><b>Last login status</b></label>&nbsp;:
							</div>
							<div class="user_right">
								<bean:write name="userDetail" property="lastLogin" />
							</div>
							
							<logic:present name="logStatus">
								<div class="user_left">
									<label><b>Status</b></label>&nbsp;:
								</div>
								<div class="user_right">
									<bean:write name="userDetail" property="errorDesc" />
								</div>
								
								<div class="user_left">
									<label><b>Score</b></label>&nbsp;:
								</div>
								<div class="user_right">
									<bean:write name="userDetail" property="score" />
								</div>
								
								<div class="user_left">
									<label><b>Tracking Date</b></label>&nbsp;:
								</div>
								<div class="user_right">
									<bean:write name="userDetail" property="trackingTime" />
								</div>
								
							</logic:present>
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-lg btn-success"
						onclick="javascript: location.href = '<%=request.getContextPath()%>/userInfo.do?method=userEnroll&userId=<bean:write name="userDetail" property="userId"/>';">
						View Enrollment Images</button>
					<button type="button" class="btn btn-lg btn-default"
						onclick="javascript: location.href = '<%=request.getContextPath()%>/userInfo.do?method=userAuth&userId=<bean:write name="userDetail" property="userId"/>';">

						View Authentication Images</button>

				</div>
				</logic:notEmpty>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>

</html>