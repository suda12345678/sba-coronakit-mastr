<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="com.wellsfargo.sba.coronakit.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User Registration Form</title>
</head>
<body>

	<h1>New User Register Form</h1>
	<form action="user?action=insertuser" method="post">
		<table style="with: 50%">
			<tr>
				<td>Full Name</td>
				<td><input type="text" value="${user.name}" name="name"
					required /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" value="${user.email}" name="email"
					required /></td>
			</tr>
			<tr>
				<td>UserName</td>
				<td><input type="text" value="${user.uname}" name="username"
					required /></td>

			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" value="${user.password }"
					name="password" required /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" value="${user.contactNumber}"
					name="address" required /></td>
			</tr>
			<tr>
				<td>Contact No</td>
				<td><input type="text" value="${user.address}" name="contact"
					required /></td>
			</tr>
		</table>

		<%-- <%! String s1 = ""; %>
    <% s1  = (String) session.getAttribute("msg");%>
    <% if(s1.equals("exist")){ %>
    <div class="besideemailbox" style="color : red">username Already exist</div>
    <% }else if(s1.equals("something")){ %>
    <div class="besideemailbox" style="color : green">ok or a tick</div>
    <% } %>
				 --%>
		<input type="submit" value="Submit" />
	</form>
	<c:if test="${not empty msg}">
		<c:out value="${msg}" />
	</c:if>
	
	</h2>
</body>
</html>