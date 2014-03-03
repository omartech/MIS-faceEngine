
<nav class="main-menu" data-step="2"
	data-intro="This is the extendable Main Navigation Menu."
	data-position="right">
	<ul>
		<li><a href="<%=request.getContextPath()%>/login.do?method=dashboard"> <i class="icon-home nav-icon"></i>

		</a></li>

		<!-- <li><a href="user_management.html"> <i
				class="icon-user nav-icon"></i>

		</a></li> -->

		<li><a href="<%=request.getContextPath()%>/mis.do?method=misInit"> <i class="icon-search nav-icon"></i>

		</a></li>

	</ul>

	<ul class="logout">
		<li><a href="<%=request.getContextPath()%>/login.do?method=logout"> <i class="icon-off nav-icon"></i>

		</a></li>
	</ul>
</nav>