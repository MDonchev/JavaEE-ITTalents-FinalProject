<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
			<h1>Login</h1>
			<form action="login" method="post">
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
							<input type="password" name="password" required>
						</td>
					</tr>
					
				</table>
				
				<input type="submit" value="Login"><br>
			</form>
	</body>
</html>