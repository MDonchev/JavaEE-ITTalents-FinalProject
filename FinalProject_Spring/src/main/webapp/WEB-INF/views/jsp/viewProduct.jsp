<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<img src="download/${product.imgURL}" height="100" width="100"/><br>
	Product name: <c:out value="${product.name }"></c:out><br>
	Description: <c:out value="${product.description }"></c:out><br>
	Price: <c:out value="${product.price }"></c:out><br>
	Amount in stock: <c:out value="${product.ammountInStock }"></c:out><br>
	Category: <c:out value="${category }"></c:out><br>
	Discount %: <c:out value="${product.discountPercent }"></c:out><br>
	Characteristics: <c:out value="${characteristic } ${characteristicValue }"></c:out>
	



</body>
</html>