<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending order</title>
</head>
<body>

	<table>
	<tr>
		<td>Name:</td>
		<td>Description:</td>
		<td>Price:</td>
		<td>Amount:</td>
	</tr>
		<c:forEach items = "${cart }" var = "item">
		<tr>
		<td> <c:out value="${item.key.name }"></c:out> </td>
		<td> <c:out value="${item.key.description }"></c:out> </td>
		<td> <c:out value="${item.key.price }"></c:out> </td>
		<td> <c:out value="${item.value }"></c:out> </td>
		<td>
			
			<form action = "${pageContext.request.contextPath}/order/decrease" method = "post">
				<input type = hidden name = "productName" value = "${item.key.name  }">
				<input type = "submit" value = "decrease">
			</form>
			 
			
		</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:if test = "${not empty cart}">
	<form:form action = "${pageContext.request.contextPath}/order/finalize">
		<input type="submit" value="Finalize Order">
		
	</form:form>
	</c:if>

	
</body>
</html>