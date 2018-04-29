<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="com.nargilemag.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add cart</title>
</head>
<body>

	<table>	
		<tr>
			<td><c:out value = "${product.name }"></c:out></td>
			<td><c:out value = "${product.description }"></c:out></td>
			<td><c:out value = "${product.price }"></c:out></td>
			<td><c:out value = "${product.ammountInStock}"></c:out></td>
			
		</tr>
	</table>
	
	<f:form commandName="product" method="POST" action="addToCart">
		Ammount to order : <input type = "text" name = "ammount">
		<input type="submit" value = "Add to cart">
		<f:input type="hidden" path="id" value="${product.id }"/>
		<f:input type="hidden" path="name" value="${product.name }"/>
		<f:input type="hidden" path="description" value="${product.description }"/>
		<f:input type="hidden" path="price" value="${product.price }"/>
		<f:input type="hidden" path="ammountInStock" value="${product.ammountInStock }"/>
		
	</f:form>
	
	<!-- 
	<form action="addToCart" method="POST">
		Amount to order : <input type="text" name="amount">
		<input type="submit" value="Product to cart">
	</form>
	 -->
	
</body>
</html>