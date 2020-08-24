<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h5 align=center>Product List - Add to Kit</h5>
	<hr />
	
	<c:choose>
		<c:when test="${productslist==null || productslist.isEmpty() }">
			<p>No Products Found is this</p>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<th>Product id</th>
					<th>Product Name</th>
					<th>Product Cost</th>
					<th>Product Desc</th>
					<th>Quantity</th>
					
				</tr>
				<c:forEach items="${productslist}" var="product">

					<tr>
						<td>${product.id}</td>
						<td>${product.productName}</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
<!-- 						<td><input type="text" name="quantity" /></td>
 -->						<td><a
							href="user?action=addnewitem&id=${product.id}&cost=${product.cost}"
							style="font-size: 10px; text-decoration: none">ADD TO KIT</a></td>
							<c:out value="${param.msg}"/>
					</tr>
				</c:forEach>
			</table>
					
		</c:otherwise>
	</c:choose>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>