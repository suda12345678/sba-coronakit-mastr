
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<title>${username}-Manage</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	<hr />

	<%
		if (session.getAttribute("username") == null)
			response.sendRedirect("index.jsp");
	%>
	
	Welcome ${username} ! Manage your products
	<nav>
		<a href="index.jsp">Home</a> <span>|</span> 
		<a href="admin?action=list">List All products</a> <span>|</span> 
		<a	href="admin?action=newproduct">Add New product</a> <span>|</span> 
		<a	href="admin?action=logout">Logout</a>
	</nav>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>