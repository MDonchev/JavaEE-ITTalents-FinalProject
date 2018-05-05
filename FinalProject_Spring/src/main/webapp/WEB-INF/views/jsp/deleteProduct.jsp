<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="com.nargilemag.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach items="${products }" var="p">
				<tr>
					<td><c:out value="${p.name }"></c:out></td>
					<td><c:out value="${p.description }"></c:out></td>
					<td><c:out value="${p.price }"></c:out></td>
					<td><c:out value="${p.ammountInStock }"></c:out></td>
					<td>
						<c:forEach items="${p.characteristics }" var="ch">
							<c:out value="${ch.name }">:</c:out>
							<c:out value="${ch.value }"></c:out>
						</c:forEach>
					</td>
					<td><form action="deleteProduct" method="POST">
						<input type="hidden" name="deleted_product" value="${p.id }">
						<input type="hidden" name="deleted_product_name" value="${p.name }">
						<input type="submit" value="Delete Product">
					</form></td>
				</tr>
		</c:forEach>
	</table>
</body>
</html>