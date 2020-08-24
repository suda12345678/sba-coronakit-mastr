<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corona Kit-Home</title>
</head>
<body>
	<div>
		<jsp:include page="header.jsp" />
		<hr />
		<h2 align=center>Login</h2>

		<form action="admin?action=login" method="post">
			<table align="center">
				<tr>
					<td>Username</td>
					<td><input type="text" name="loginid" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="text" name="password" /></td>
				</tr>
				<tr>
					<td><span style="color: red"><%=(request.getAttribute("msg") == null) ? "" : request.getAttribute("msg")%></span></td>
				</tr>
				<tr>
					<!--         <td><a href="user?action=existinguser"><button>Existing user</button></a></td>
 -->
					<td><input type="submit" value="Admin Login"></input><input
						type="reset" value="Reset"></input></td>
				</tr>
				<tr>
					<td>
					<td>
					<td></td>
				</tr>
			</table>

		</form>

	</div>
	<hr />
	<div>
		<a href="user?action=newuser"><button>New User Register</button></a> 
<!-- 		<a href="user?action=existinguser"><button>Existing user</button></a> 
 -->		<a href="userlogin.jsp"><button>Existing user</button></a>


	</div>
	<hr />


	<jsp:include page="footer.jsp" />
</body>
</html>