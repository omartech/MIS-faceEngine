<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<bean:include id="navigation" page="/pages/navigation.jsp" />

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7 lt-ie10"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8 lt-ie10"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9 lt-ie10"> <![endif]-->
<!--[if IE 9]>         <html class="no-js lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!-->
<html>
<!--<![endif]-->
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Dashboard</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/92bc1fe4.css">
<!-- ajman CSS: -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/aaf5c053.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/animate.css">
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

<!-- Common Scripts: -->
<script src="<%=request.getContextPath()%>/public/js/jquery_003.js"></script>
<script src="<%=request.getContextPath()%>/public/js/modernizr.js"></script>
<script src="<%=request.getContextPath()%>/public/js/jquery.js"></script>
<link href="<%=request.getContextPath()%>/public/css/light.css"
	type="text/css" rel="stylesheet">
</head>

<body class="dashboard-page">

	<!--[if lt IE 8]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->



	<bean:write name="navigation" filter="false" />

	<section style="opacity: 1;" class="wrapper scrollable">

		<section class="title-bar">
			<div>
				<span class="dash_title">&nbsp;&nbsp;&nbsp;&nbsp;Dashboard</span>
			</div>


			<span style="float: right; margin-top: -68px;"><img
				src="<%=request.getContextPath()%>/public/images/footer_logo.png"
				style="width: 75px; height: 50px;"></span>


		</section>
		<logic:present name="userDetail" scope="request">
			<nav class="quick-launch-bar">
				<ul class="ui-sortable">
					<li><a> <span style="width: 95px;">Number of users</span>
					</a></li>
					<li><a> <img
							src="<%=request.getContextPath()%>/public/images/arrow.png">
					</a></li>
					<li style="width: 135px;"><a href="#"> <span
							class="numbers"><bean:write name="userDetail"
									property="totalNumberOfUser" /></span>
					</a></li>
					<li><a> <span style="width: 95px;">Number of user
								enrolled</span>
					</a></li>
					<li><a> <img
							src="<%=request.getContextPath()%>/public/images/arrow.png">
					</a></li>
					<li style="width: 135px;"><a href="javascript:;"> <span
							class="numbers"><bean:write name="userDetail"
									property="enrollmentUser" /></span>
					</a></li>
					<li><a> <span style="width: 105px;">Number of user
								not enrolled</span>
					</a></li>

					<li><a> <img
							src="<%=request.getContextPath()%>/public/images/arrow.png">
					</a></li>

					<li><a href="#"> <span class="numbers"><bean:write
									name="userDetail" property="notEnrollmentUser" /></span>
					</a></li>

				</ul>

			</nav>
		</logic:present>


		<div style="height: 50%;"></div>
		<div class="footer">copyright &copy; Ajman land &amp; property
			department.All rights reserved</div>


		<!-- ajman base scripts: -->
		<script src="<%=request.getContextPath()%>/js/9f7a46ed.js"></script>
		<!-- Page-specific scripts: -->
		<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/js/e9e962ee.js"></script>
</body>
</html>