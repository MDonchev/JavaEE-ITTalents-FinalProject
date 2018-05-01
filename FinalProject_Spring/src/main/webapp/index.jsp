<%@page import="java.util.ArrayList"%>
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
				<h3 style="color:green; text-align: right"><%= user.getBalance() %>$</h3>
				<form action="logout" method="post">
					<input type='submit' value='Logout' />
				</form>
				<% if (user.isAdmin()) {  %>
				<form action="addproduct" method="get">
					<input type='submit' value='Add Product' />
				</form>
				<form action="updateProductAmount" method="get">
					<input type='submit' value='Update Products Amount' />
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
		<% List<Product> userFavouritesProducts = new ArrayList<Product>();
			if (user != null){
				userFavouritesProducts = ProductDao.INSTANCE.getUserFavourites(user);
			}
			%>
		<table>
			<tr>
				<td>Name:</td>
				<td>Description:</td>
				<td>Price:</td>
				<td>Amount:</td>
				<td>Characteristics:</td>
			</tr>
			<% for (Product p : products) {
				request.setAttribute("filename", p.getImgURL());%>
				<tr>
					<td><%= p.getName() %></td>
					<td><%= p.getDescription() %></td>
					<td><%= p.getPrice() %></td>
					<td><%= p.getAmmountInStock() %></td>
					<td><%= p.getCharacteristics().get(0).getName()%> : <%=p.getCharacteristics().get(0).getValue()%></td>
					<td><img src="download/${filename}" height="100" width="100"/></td>
					<% if (user != null) {%>
						<td>
							<form action="addToCart" method="GET">
								<input type="hidden" name="ordered_product" value="<%= p.getId()%>">
								<input type="submit" value="Add to cart">
							</form>
						</td>
						<td>
							<form action="addToFavourites" method="POST">
								<input type="hidden" name="fav_product" value="<%= p.getId()%>">
								<input type="submit" value="Add to favourites">
							</form>
						</td>
					<%} %>
				</tr>
			<%} %>
		</table>
		<% if (user != null) {%>
		<table>
			<% for (Product p : userFavouritesProducts) {
				request.setAttribute("filename", p.getImgURL());%>
				<tr>
					<td><%= p.getName() %></td>
					<td><%= p.getDescription() %></td>
					<td><%= p.getPrice() %></td>
					<td><%= p.getAmmountInStock() %></td>
					<td><%= p.getCharacteristics().get(0).getName()%> : <%=p.getCharacteristics().get(0).getValue()%></td>
					<td><img src="download/${filename}" height="100" width="100"/></td>
					<td>
						<form action="removeFavourite" method="POST">
							<input type="hidden" name="fav_product" value="<%= p.getId()%>">
							<input type="submit" value="Remove to favourites">
						</form>
					</td>
				</tr>
			<%} %>
		</table>
		
		<a href="order"> Show Cart</a>
		<%} %>
	</center>
</body>
</html>