<%@page import="model.Gender"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register</title>
	</head>
	<body>
			<h1>Register</h1>
			<form action="register" method="post">
				<table>
					<tr>
						<td>
							Username
						</td>
						<td>
							<input type="text" name="username" required>
						</td>
					</tr>
					<tr>
						<td>
							Password
						</td>
						<td>
							<input type="password" name="password1" required>
						</td>
					</tr>
					<tr>
						<td>
							Confirm password
						</td>
						<td>
							<input type="password" name="password2" required>
						</td>
					</tr>
					<tr>
						<td>
							Email
						</td>
						<td>
							<input type="email" name="email" required>
						</td>
					</tr>
					<tr>
						<td>
							Address
						</td>
						<td>
							<input type="text" name="address" required>
						</td>
					</tr>
					<tr>
						<td>
							Number
						</td>
						<td>
							<input type="text" name="number" required>
						</td>
					</tr>
					<tr>
						<td>
							Gender
						</td>
						<td>
							<select style="width: 132px" name="gender" required>
								<% 
								List<Gender> genders = (List<Gender>) request.getAttribute("genders");
								for(Gender g : genders){ 
								%>
									<option value="<%= g.getId()%>"><%= g.getName()%></option>
								<%} %>
							</select>
						</td>
					</tr>
				</table>
				
				<input type="submit" value="Register"><br>
				Already have an account? <a href="index.jsp">Login here</a>
			</form>
	</body>
</html>