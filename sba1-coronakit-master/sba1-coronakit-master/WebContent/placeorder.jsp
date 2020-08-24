<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h5 align=center>Confirm Order</h5>

<hr/>

<form action="user?action=saveorder" method="post">

<c:set var = "now" value = "<%=new java.util.Date()%>" />

			<table align="center">
				<tr>
					<td>CoronaKit ID</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contact" /></td>
				</tr>
				<!-- <tr>
					<td>Total Amount</td>
					<td><input type="text" name="amount" value=${totalamount}/></td>
				</tr> -->
				<tr>
					<td>Delivery Address</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>Order Date</td>
					<td><input type="date" name="date" value = "<fmt:formatDate pattern = "yyyy-MM-dd" value="${now}"/>"/></td>
				</tr>
				<tr>
					<td>Order Finalised</td>
					<td><input type="checkbox" name="final" /></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Submit"></input></td>
				</tr>
				<tr>
					<td>
					<td>
					<td></td>
				</tr>
			</table>

		</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>