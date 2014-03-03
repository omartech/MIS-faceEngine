<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7 lt-ie10"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8 lt-ie10"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9 lt-ie10"> <![endif]-->
<!--[if IE 9]>         <html class="no-js lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!-->
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Login</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/public/css/92bc1fe4.css">
<!-- Ajman CSS: -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/aaf5c053.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/public/css/animate.css">
<body class="login-page">
<html:form action="login.do">
<html:hidden property="method" value="login"/>
	<!--[if lt IE 8]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<form role="form">
		<section class="wrapper scrollable animated fadeInDown">
			<section class="panel panel-default" style="top: 20%;">
				<div class="panel-heading">
					<div>
						<img src="<%=request.getContextPath()%>/public/images/logo.png"
							alt="">
						<h1>
							<span class="title"> </span> <span class="subtitle"> </span>
						</h1>
					</div>
				</div>
				<ul class="list-group">
					<li>
						<div class="form-group" align="center">
							<div class="eid">
								<!-- <input class="inbox1" id="email" placeholder="Email" type=""
									style="font-size: 24px; height: 70px; margin-right: 5px; margin-top: 23px; width: 602px;" /> -->
									
									<html:text styleClass="inbox1" styleId="email" property="userName" 
										style="font-size: 24px; height: 70px; margin-right: 5px; margin-top: 23px; width: 602px;"/>
							</div>
						</div>
						<div class="form-group" align="center">

							<div class="pwd">
								<!-- <input class="inbox" id="password" placeholder="Password"
									type="password"
									style="font-size: 24px; height: 70px; margin-right: 5px; margin-top: 23px; width: 602px;" /> -->
								
								<html:password styleClass="inbox"  styleId="password" property="password"
									style="font-size: 24px; height: 70px; margin-right: 5px; margin-top: 23px; width: 602px;"/>	
								
							</div>
						</div>
					</li>
				</ul>
				<div class="panel-footer">
					<html:submit styleClass="btn btn-lg btn-success">LOGIN</html:submit>
					<br>
					<!-- <a class="forgot" href="javascript:;">Forgot Your Password?</a> -->
				</div>

			</section>
			<div class="footer">copyright &copy; Ajman land &amp; property
				department.All rights reserved</div>
		</section>

	</form>
</html:form>
</body>


</html>
