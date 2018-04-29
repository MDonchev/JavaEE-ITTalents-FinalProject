<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="com.nargilemag.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add cart</title>
</head>
<body>
	<% Product p = ProductDao.INSTANCE.getProductBtID((Integer)request.getAttribute("productID")); %>
	<table>	
		<tr>
			<td><%= p.getName() %></td>
			<td><%= p.getDescription() %></td>
			<td><%= p.getPrice()%></td>
			<td><%= p.getAmmountInStock()%></td>
			
		</tr>
	</table>
	
	<form action="addToCart" method="POST">
		Amount to order : <input type="text" name="amount">
		<input type="submit" value="Product to cart">
	</form>
</body>
</html>