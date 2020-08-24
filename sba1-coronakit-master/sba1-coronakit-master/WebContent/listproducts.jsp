<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<c:choose>
		<c:when test="${productslist==null || productslist.isEmpty() }">
			<p>No Products Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product id</th>
					<th>Product Name</th>
					<th>Product Cost</th>
					<th>Product Desc</th>
					
				</tr>
				<c:forEach items="${productslist }" var="product">
				
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
						<td><a href="admin?action=deleteproduct&id=${product.id }">DELETE</a> <span>|</span>
							<a href="admin?action=editproduct&id=${product.id }">EDIT</a> <span>|</span></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>