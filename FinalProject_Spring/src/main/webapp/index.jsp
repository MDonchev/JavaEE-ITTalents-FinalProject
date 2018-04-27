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
		asdasdasdasdasa
		<h1>Welcome to Nargilemag.bg</h1>
		<% User user = (User) session.getAttribute("user");
			if (user != null) { %>
				<h1>Welcome, <%= user.getUsername() %></h1>
				<form action="logout" method="post">
					<input type='submit' value='Logout' />
				</form>
				
		<%} else {%>	
			<form action="login">
				<input type='submit' value='Login' />
			</form>
			<form action="register">
				<input type='submit' value='Register' />
			</form>
			
		<%} %>
	</center>
</body>
</html>