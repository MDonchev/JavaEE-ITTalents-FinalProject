<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			
			<form:form commandName = "product" action = "/nargilemag/order/decrease" method = "post">
				<input type = "submit" value = "decrease"/>
				<form:input type = "hidden" path = "id" value = "${item.key.id }"/>
				<form:input type = "hidden" path = "name" value = "${item.key.name }"/>
				<form:input type = "hidden" path = "description" value = "${item.key.description }"/>
				<form:input type = "hidden" path = "price" value = "${item.key.price }"/>
				<form:input type = "hidden" path = "ammountInStock" value = "${item.key.ammountInStock }"/>
				<form:input type = "hidden" path = "categoryId" value = "${item.key.categoryId }"/>
				<form:input type = "hidden" path = "discountPercent" value = "${item.key.discountPercent }"/>
			</form:form>
			 
			
		</td>
		
		<td>
			
			<form:form commandName = "product" action = "/nargilemag/order/increase" method = "post">
				<input type = "submit" value = "increase"/>
				<form:input type = "hidden" path = "id" value = "${item.key.id }"/>
				<form:input type = "hidden" path = "name" value = "${item.key.name }"/>
				<form:input type = "hidden" path = "description" value = "${item.key.description }"/>
				<form:input type = "hidden" path = "price" value = "${item.key.price }"/>
				<form:input type = "hidden" path = "ammountInStock" value = "${item.key.ammountInStock }"/>
				<form:input type = "hidden" path = "categoryId" value = "${item.key.categoryId }"/>
				<form:input type = "hidden" path = "discountPercent" value = "${item.key.discountPercent }"/>
			</form:form>
			 
			
		</td>
		
		</tr>
		</c:forEach>
	</table>
	
	<td><h5>Total Price:</h5> <h3 style = "color: green">${totalPrice }</h3></td>
	
	<c:if test = "${not empty cart}">
	<form:form action = "/nargilemag/order/finalize">
		<input type="submit" value="Finalize Order">
	</form:form>
	</c:if>

	
</body>
</html>