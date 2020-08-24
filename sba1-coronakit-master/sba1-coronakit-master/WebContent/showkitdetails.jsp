<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h5 align=center>List added to Kit</h5>
<hr/>

<c:choose>
		<c:when test="${kitlist==null || kitlist.isEmpty() }">
			<p>Kit is empty</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>kit id</th>
					<th>carona kit id</th>
					<th>Product id</th>
					<th>quantity</th>
					<th>amount</th>
					
				</tr>
				<c:forEach items="${kitlist}" var="kit">
				
					<tr>
						<td>${kit.id }</td>
						<td>${kit.coronaKitId }</td>
						<td>${kit.productId }</td>
						<td>${kit.quantity }</td>
						<td>${kit.amount }</td>
						<td>
						<a href="user?action=deleteitem&id=${kit.id }">DELETE FORM KIT</a><span>|</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
<hr/>	

	<jsp:include page="footer.jsp"/>
</body>
</html>