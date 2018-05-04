<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<f:form action = "search" method = "post">
		<input type = "text" name = "name"/>
		<input type = "submit" value = "search"/>
	</f:form>
	<a href = "index">Home</a>
	<table>
	<tr>
		<td>Name:</td>
		<td>Description:</td>
		<td>Price:</td>
		<td>Discount %:</td>
	</tr>
		<c:forEach items = "${searchResult }" var = "item">
		<tr>
		<td> <c:out value="${item.name }"></c:out> </td>
		<td> <c:out value="${item.description }"></c:out> </td>
		<td> <c:out value="${item.price }"></c:out> </td>
		<td> <c:out value="${item.discountPercent }"></c:out> </td>
		<td>
			<f:form action = "view" method = "post">
				<input type = "hidden" name = "productId" value = "${item.id }"/>
			</f:form>
		</td>
			
			
		</tr>
		</c:forEach>
	</table>

</body>
</html>