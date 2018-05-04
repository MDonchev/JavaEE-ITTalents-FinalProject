<%@page import="com.nargilemag.model.Gender"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
								<c:forEach items="${genders }" var= "g">
									<option value="${g.id }"><c:out value="${g.name }"></c:out></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
				<input type="submit" value="Register"><br>
				Already have an account? <a href="login">Login here</a>
			</form>
	</body>
</html>