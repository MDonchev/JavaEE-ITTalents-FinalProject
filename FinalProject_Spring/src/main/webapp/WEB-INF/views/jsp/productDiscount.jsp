<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Discount Page</title>
</head>
<body>

	<table>
	<tr>
		<td>Name:</td>
		<td>Description:</td>
		<td>Price:</td>
		<td>Discount %:</td>
	</tr>
		<c:forEach items = "${products }" var = "item">
		<tr>
		<td> <c:out value="${item.name }"></c:out> </td>
		<td> <c:out value="${item.description }"></c:out> </td>
		<td> <c:out value="${item.price }"></c:out> </td>
		<td> <c:out value="${item.discountPercent }"></c:out> </td>
		<td>
			<form:form commandName = "product" action = "discount" method = "post">
				<form:input type = "hidden" path = "id" value = "${item.id }" />
				<form:input type = "hidden" path = "name" value = "${item.name }" />
				<form:input type = "text" path = "discountPercent" style = "width : 10%" value = "0"/>
				<input type = "submit" value = "set" />
			</form:form>
		</td>
			
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>