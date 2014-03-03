<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<bean:include id="navigation" page="/pages/navigation.jsp" />

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

<script type="text/javascript">

	function clearfrom(oForm) {

		var frm_elements = oForm.elements;

		for (i = 0; i < frm_elements.length; i++) {
			field_type = frm_elements[i].type.toLowerCase();

			switch (field_type) {
			case "text":
				frm_elements[i].value = "";
				break;
			case "checkbox":
				if (frm_elements[i].checked) {
					frm_elements[i].checked = false;
				}
				break;
			case "select-one":
			case "select-multi":
				frm_elements[i].selectedIndex = -1;
				break;
			default:
				break;
			}
		}
	}
	
</script>



</head>

<body>
	<html:form action="mis.do">
		<html:hidden property="method" value="misSearch" />

		<!--[if lt IE 8]>
        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

		<bean:write name="navigation" filter="false" />
		
		<section class="sidebar extended"
			style="max-height: none; opacity: 1;">
			<script>
				if ($.cookie('protonSidebar') == 'retracted') {
					$('.sidebar').removeClass('extended').addClass('retracted');
					$('.wrapper').removeClass('retracted').addClass('extended');
				}
				if ($.cookie('protonSidebar') == 'extended') {
					$('.wrapper').removeClass('extended').addClass('retracted');
					$('.sidebar').removeClass('retracted').addClass('extended');
				}
			</script>
			<div class="panel panel-default">
				<div class="panel-body" style="background-color: #76b6ec;">
					<div class="title">
						<i class="icon-search"></i> <span style="color: white;">
							MIS </span>
						<!-- <a href="javascript:;" class="add">
                            <i class="icon-plus-sign"></i>
                            <span>
                                ADD NEW
                            </span>
                        </a> -->
					</div>
					<div class="input-group"></div>

				</div>
				<div class="panel-body tree-body">
					<div class="scrollable">

						<div class="list-group">
							<div class="list-group-item">


								<div class="form-group">
									<label>User ID</label>
									<div>
										<!--  <input type="text" value="User ID" class="datetimepicker-search form-control"> -->

										<html:text styleClass="datetimepicker-search form-control"
											property="userId" />
									</div>

								</div>

								<div class="form-group">
									<label><i class="icon-exchange"></i> From Date & time</label>
									<div>
										<!--  <input type="text" value="2012-05-15 21:05" class="datetimepicker-search form-control"> -->

										<html:text styleClass="datetimepicker-search form-control"
											property="fromDate" />

									</div>
									<div class="form-group">
										<label><i class="icon-exchange"></i> To Date & time</label>
										<div>
											<!-- <input type="text" value="2012-05-15 21:05" class="datetimepicker-search form-control"> -->

											<html:text styleClass="datetimepicker-search form-control"
												property="toDate" />

										</div>
									</div>

									<div class="form-group">
										<div>
											<label class="checkbox-inline"> <!-- <input type="checkbox" value=""> -->
												<html:multibox property="status" value="success" /> Success

											</label> <label class="checkbox-inline"> <!-- <input type="checkbox" checked=""> -->
												<html:multibox property="status" value="failure" /> Failure

											</label>
										</div>


									</div>

									<div class="form-group">
										<div>
											<label class="checkbox-inline"> <!-- <input type="checkbox" value=""> -->
												<html:multibox property="enrollStatus" value="Enrolled" />
												Enrolled
											</label> <label class="checkbox-inline"> <!--  <input type="checkbox" checked=""> -->
												<html:multibox property="enrollStatus" value="notEnrolled" />
												Not Enrolled
											</label>
										</div>


									</div>

									<div class="list-group-item text-right">
										<html:submit
											styleClass="btn btn-sm btn-success close-advanced-search"
											onclick="javascript: document.forms[0].submit();">
												Search
											</html:submit>
										<%-- <html:submit styleClass="btn btn-sm btn-success close-advanced-search">Search</html:submit>
                                            <button type="button" class="btn btn-sm btn-success close-advanced-search">Search</button> --%>
										<button type="button" class="btn btn-sm btn-default" onclick="clearfrom(this.form);">Reset</button>
									</div>
								</div>
							</div>

						</div>
						<div class="sidebar-handle">
							<i class="icon-caret-right"></i> <i class="icon-caret-left"></i>
						</div>
		</section>

		<section class="wrapper retracted scrollable" style="opacity: 1;">

			<script>
				if (!($('body').is('.dashboard-page') || $('body').is(
						'.login-page'))) {
					if ($.cookie('protonSidebar') == 'retracted') {
						$('.wrapper').removeClass('retracted').addClass(
								'extended');
					}
					if ($.cookie('protonSidebar') == 'extended') {
						$('.wrapper').removeClass('extended').addClass(
								'retracted');
					}
				}
			</script>
			<div id="top"
				class="panel panel-default panel-block panel-title-block">
				<div class="panel-heading">
					<div>
						<i class="icon-edit"></i>
						<h1>
							<span class="page-title">MIS</span> <small>Search Results</small>
						</h1>

					</div>
					<div class="mis_logo" style="float: right;">
						<img
							src="<%=request.getContextPath()%>/public/images/footer_logo.png"
							style="width: 75px; height: 50px;">
					</div>
				</div>

			</div>

			<div class="row">



				<div class="panel panel-default panel-block">
					<div id="data-table" class="panel-heading datatable-heading">
						<h4 class="section-title">User Details</h4>
					</div>
					<table class="table table-bordered table-striped"
						id="tableSortable">
						<thead>
							<tr>
								<th>User ID</th>
								<th>Type</th>
								<th>Path</th>
								<!-- <th>Enrollment Status</th> -->
								<th>Status</th>
								<th>Date & Time</th>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="recordList">
								<logic:iterate id="misRecord" name="recordList">
									<tr class="gradeX">

										<td>
											<%-- <a class="add-quick-launch" href="#" 
		                                 		onclick="javascript:window.open('<%=request.getContextPath()%>/pages/model.jsp','myWindow',
		                                 		status=1,width=320,height=200,resizable=0)"> --%>

											<a class="add-quick-launch" href="#"
											onclick="javascript:window.open('<%=request.getContextPath()%>/userInfo.do?method=userInit&userId=<bean:write name="misRecord" property="userId" />','mywindow',
		                                 			'left=150,top=100,width=800,height=600,toolbar=0,resizable=0')">

												<bean:write name="misRecord" property="userId" />
										</a>
										</td>

										<td><bean:write name="misRecord" property="enrollType" />
										</td>

										<td><bean:write name="misRecord"
												property="enrollmentPath" /></td>

										<%-- <td class="center"><bean:write name="misRecord"
												property="enrollmentStatus" /></td> --%>

										<td class="center"><bean:write name="misRecord"
												property="status" /></td>

										<td class="center"><bean:write name="misRecord"
												property="dataTime" /></td>

									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
			</div>
			</div>
		</section>

		<div aria-hidden="true" style="display: none;" id="quickLaunchModal3"
			tabindex="-1" role="dialog" class="modal fade">
			<div class="modal-dialog" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h4 class="modal-title">
							<i class="icon-user"></i><span>USER DETAILS</span>
						</h4>
					</div>
					<div class="modal-body">
						<div class="profile_picture" id="profile">
							<img src="images/omar.png">
						</div>
						<div class="profile_picture" id="enroll" style="display: none;">
							<span><img src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>
						</div>
						<div class="profile_picture" id="auth" style="display: none;">
							<div class="form_right" style="flaot: right;">
								Authendication History : <select class="select2"
									style="border: 1px solid #E2E2E2; border-radius: 3px; font-family: sans-serif; height: 24px; vertical-align: middle; width: 180px;">
									<option selected="" value="price">27/02/2013 11:50 AM</option>
									<option value="spread">12/01/2013 10:10 AM</option>

								</select>
							</div>
							<br> <span><img src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>&nbsp;<span><img
								src="images/omar.png"></span>
						</div>
						<div class="add_user">
							<div class="user_left">
								<label><b>User ID</b></label>&nbsp;:
							</div>
							<div class="user_right">154825</div>

							<div class="user_left">
								<label><b>Enrollment Status</b></label>&nbsp;:
							</div>
							<div class="user_right">Approved</div>

							<div class="user_left">
								<label><b>Number of Attempts</b></label>&nbsp;:
							</div>
							<div class="user_right">28</div>

							<div class="user_left">
								<label><b>Last login status</b></label>&nbsp;:
							</div>
							<div class="user_right">12/05/2014 15:55 AM</div>


						</div>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-lg btn-success"
							onclick="profile();">View Enrollment Images</button>
						<button type="button" class="btn btn-lg btn-default"
							onclick="auth();">View Authentication Images</button>

					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>


	</html:form>
	<script
		src="<%=request.getContextPath()%>/public/js/e1d08589.bootstrap.min.js"></script>

	<!-- Proton base scripts: -->
	<script
		src="<%=request.getContextPath()%>/public/js/9f7a46ed.proton.js"></script>


	<!-- Page-specific scripts: -->
	<script
		src="<%=request.getContextPath()%>/public/js/ba45a7b8.sidebar.js"></script>
	<script
		src="<%=request.getContextPath()%>/public/js/5558cd34.tables.js"></script>
	<!-- jsTree -->

	<!-- Data Tables -->
	<!-- http://datatables.net/ -->
	<script
		src="<%=request.getContextPath()%>/public/js/jquery.dataTables.min.js"></script>

	<!-- Data Tables for BS3 -->
	<!-- https://github.com/Jowin/Datatables-Bootstrap3/ -->
	<!-- NOTE: Original JS file is modified -->
	<script src="<%=request.getContextPath()%>/public/js/datatables.js"></script>
	<!-- Select2 Required To Style Datatable Select Box(es) -->
	<!-- https://github.com/fk/select2-bootstrap-css -->
	<script src="<%=request.getContextPath()%>/public/js/select2.min.js"></script>


</body>
</html>