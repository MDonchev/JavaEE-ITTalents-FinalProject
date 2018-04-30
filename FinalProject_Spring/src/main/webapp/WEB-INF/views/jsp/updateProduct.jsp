<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="com.nargilemag.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% List<Product> products = ProductDao.INSTANCE.getAllProducts();%>
	<table>
		<% for (Product p : products) {%>
				<tr>
					<td><%= p.getName() %></td>
					<td><%= p.getDescription() %></td>
					<td><%= p.getPrice() %></td>
					<td><%= p.getAmmountInStock() %></td>
					<td><%= p.getCharacteristics().get(0).getName()%> : <%=p.getCharacteristics().get(0).getValue()%></td>
					<td><form action="updateProduct" method="GET">
						<input type="hidden" name="changed_product" value="<%= p.getId()%>">
						<input type="submit" value="Update Product">
					</form></td>
				</tr>
			<%} %>
	</table>
</body>
</html>