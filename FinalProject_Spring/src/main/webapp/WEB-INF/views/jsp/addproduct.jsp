<%@page import="com.nargilemag.model.Category"%>
<%@page import="com.nargilemag.model.Characteristic"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add Product</title>
	</head>
	<body>
			<h1>Add Product</h1>
			<form action="addproduct" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							Product name
						</td>
						<td>
							<input type="text" name="name" required>
						</td>
					</tr>
					<tr>
						<td>
							Description
						</td>
						<td>
							<input type="text" name="desc" required>
						</td>
					</tr>
					<tr>
						<td>
							Product image
						</td>
						<td>
							<input type="file" name="fail">
						</td>
					</tr>
					<tr>
						<td>
							Price
						</td>
						<td>
							<input type="text" name="price" required>
						</td>
					</tr>
					<tr>
						<td>
							Ammount
						</td>
						<td>
							<input type="text" name="ammount" required>
						</td>
					</tr>
					<tr>
						<td>
							Category
						</td>
						<td>
							<select style="width: 132px" name="category" required>
								<% 
								List<Category> categories = (List<Category>) request.getAttribute("categories");
								for(Category c : categories){ 
								%>
									<option value="<%= c.getId()%>"><%= c.getName()%></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Characteristics
						</td>
						<td>
							<select style="width: 132px" name="characteristics" required>
								<% 
								List<Characteristic> characteristics = (List<Characteristic>) request.getAttribute("character");
								for(Characteristic ch : characteristics){ 
								%>
									<option value="<%= ch.getId()%>"><%= ch.getName()%></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Characteristics ammount
						</td>
						<td>
							<input type="text" name="ch_ammo" required>
						</td>
					</tr>
				</table>
				
				<input type="submit" value="Add Product"><br>
			</form>
	</body>
</html>