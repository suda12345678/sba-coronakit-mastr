<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h5 align=center>Order Summary</h5>

<hr/>

<c:choose>
		<c:when test="${ordersummary==null}">
			<p>No order Saved</p>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<th>Coronakit id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact Number</th>
					<th>Total Amount</th>
					<th>Delivery Address</th>
					<th>Order Date</th>
					<th>Order Finalised</th>
					
				</tr>

					<tr>
						<td><c:out value="${ordersummary.coronaKit.id}"/></td>
						<td><c:out value="${ordersummary.coronaKit.personName }"/></td>
						<td><c:out value="${ordersummary.coronaKit.email }"/></td>
						<td><c:out value="${ordersummary.coronaKit.contactNumber }"/></td>
						<td><c:out value="${ordersummary.coronaKit.totalAmount }"/></td>
						<td><c:out value="${ordersummary.coronaKit.deliveryAddress }"/></td>
						<td><c:out value="${ordersummary.coronaKit.orderDate }"/></td>
						<td><c:out value="${ordersummary.coronaKit.orderFinalized }"/></td>
						
						
					</tr>
				
				</table>
				
				<table border="1">
				<tr>
					<th>kit ID</th>
					<th>Coronakit ID</th>
					<th>Product ID</th>
					<th>Quantity</th>
					<th>Amount</th>
					
				</tr>
				<c:forEach items="${ordersummary.kitDetails}" var="kit">

					<tr>
						<td>${kit.id }</td>
						<td>${kit.coronaKitId }</td>
						<td>${kit.productId }</td>
						<td>${kit.quantity }</td>
						<td>${kit.amount }</td>
						
					</tr>
				</c:forEach>
			</table>
					
		</c:otherwise>
	</c:choose>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>