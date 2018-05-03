<%@page import="java.util.ArrayList"%>
<%@page import="com.nargilemag.model.Product"%>
<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.nargilemag.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NargileMag.bg</title>
</head>
<body>
	
	<div style = "text-align: center">
		<h1>Welcome to Nargilemag.bg</h1>
		<c:choose>
			<c:when test="${not empty loggedUser}">
				<h1>Welcome, <c:out value="${loggedUser.username}"></c:out></h1>
				<h3 style="color:green; text-align: right"><c:out value="${loggedUser.balance}"/>$</h3>
				<f:form action = "search" method = "post">
					<input type = "text" name = "name"/>
					<input type = "submit" value = "search"/>
				</f:form>

				<form action="logout" method="post">
					<input type='submit' value='Logout' />
				</form>
				<c:if test="${loggedUser.admin }">
					<f:form action="addproduct" method="get">
						<input type='submit' value='Add Product' />
					</f:form>
					<f:form action="updateProductAmount" method="get">
						<input type='submit' value='Update Products Amount' />
					</f:form>
				</c:if>
				<f:form action = "sorted" method = "get">
					<input style = "text-align: left" type = "submit" value = "Sort By Price" />
				</f:form>
			</c:when>
			<c:otherwise>
				<f:form action="login" method="GET">
					<input type='submit' value='Login' />
				</f:form>
				<f:form action="register" method="GET">
					<input type='submit' value='Register' />
				</f:form>
			</c:otherwise>
		</c:choose>
		
		<table align = "center">
			<tr>
				<td>Name:</td>
				<td>Description:</td>
				<td>Price:</td>
				<td>Amount:</td>
				<td>Characteristics:</td>
			</tr>
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
					<td><img src="download/${p.imgURL}" height="100" width="100"/></td>
					<c:if test="${not empty loggedUser}">
						<td>
							<form action="addToCart" method="GET">
								<input type="hidden" name="ordered_product" value="${p.getId() }">
								<input type="submit" value="Add to cart">
							</form>
						</td>
						<td>
							<form action="addToFavourites" method="POST">
								<input type="hidden" name="fav_product" value="${p.id }">
								<input type="submit" value="Add to favourites">
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty loggedUser }">
			<table>
				<c:forEach items="${favourites }" var="p">
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
						<td><img src="download/${p.imgURL}" height="100" width="100"/></td>
						<td>
							<form action="removeFavourite" method="POST">
								<input type="hidden" name="fav_product" value="${p.id }">
								<input type="submit" value="Remove to favourites">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<a href="order"> Show Cart</a>
		</c:if>
	</div>
</body>
</html>