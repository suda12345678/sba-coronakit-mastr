<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3>${product.id==null?"New Product":"Edit Product" }</h3>
	
	<form action='${product.id==null}&admin?action=newproduct:action=saveproduct' method="POST">
		<div>
			<label>Product ID: </label>
			<input type="number" value="${product.id }" name="id" required ${product.id==null?"":"readonly"}/>
		</div>
		<div>
			<label>Product Name: </label>
			<input type="text" value="${product.productName }" name="title" minlength="3" maxlength="20" required />
		</div>
		<div>
			<label>Product Cost: </label>
			<input type="date" value="${product.cost }" name="cost" required />
		</div>
		<div>
			<label>product Description:</label>
			<input type="text" value="${product.productDescription}" name="desc" required />
		</div>
<!-- 		<input type="button" value="save" onClick="admin?document.getElementId('action').value=saveproduct" />
 --><!-- 		document.getElementById("myBtn").formAction = "/action_page2.php";
 -->		<button type="submit" formaction="admin?action=saveproduct">SAVE</button>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>