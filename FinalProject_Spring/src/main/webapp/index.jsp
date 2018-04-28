<%@page import="com.nargilemag.model.Product"%>
<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.nargilemag.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NargileMag.bg</title>
</head>
<body>
	
	<center>
		<h1>Welcome to Nargilemag.bg</h1>
		<% User user = (User) session.getAttribute("user");
			if (user != null) { %>
				<h1>Welcome, <%= user.getUsername() %></h1>
				<form action="logout" method="post">
					<input type='submit' value='Logout' />
				</form>
				<% if (user.isAdmin()) {  %>
				<form action="addproduct" method="get">
					<input type='submit' value='Add Product' />
				</form>
				<%} %>
				
		<%} else {%>	
			<form action="login">
				<input type='submit' value='Login' />
			</form>
			<form action="register">
				<input type='submit' value='Register' />
			</form>
			
		<%} %>
		<% List<Product> products = ProductDao.INSTANCE.getAllProducts();%>
		<table>
			<tr>
				<td>Name:</td>
				<td>Description:</td>
				<td>Price:</td>
				<td>Amount:</td>
				<td>Characteristics:</td>
			</tr>
			<% for (Product p : products) {%>
				<tr>
					<td><%= p.getName() %></td>
					<td><%= p.getDescription() %></td>
					<td><%= p.getPrice() %></td>
					<td><%= p.getAmmountInStock() %></td>
					<td><%= p.getCharacrteristics().get(0).getName()%> : <%=p.getCharacrteristics().get(0).getValue()%></td>
					<% if (user != null) {%>
						<td>
							<form action="/addToCart" method="POST">
								<input type="submit" value="Add to cart">
							</form>
						</td>
					<%} %>
				</tr>
			<%} %>
		</table>
	</center>
</body>
</html>