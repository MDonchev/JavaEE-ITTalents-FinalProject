<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Cart</title>
	
	<base href="http://localhost:8080/MyProject/order">
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="Colo Shop Template">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/owl.theme.default.css">
	<link rel="stylesheet" type="text/css" href="css/animate.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/single_styles.css">
	<link rel="stylesheet" type="text/css" href="css/single_responsive.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Pending order</title>
</head>
<body>

	<div class="super_container">

	<!-- Header -->

	<header class="header trans_300">

		<!-- Top Navigation -->

		<div class="top_nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-6 text-right">
						<div class="top_nav_right">
							<ul class="top_nav_menu">

								<!-- My Account -->
								<li class="account">
									<c:choose>
										<c:when test="${not empty loggedUser}">
											<a href="#">
												<c:out value="${loggedUser.username }"></c:out>
											</a>
											<ul class="account_selection">
												<li><a href="../logout">logout</a></li>
												<li><a href="../order">Favourites</a></li>
											</ul>
										</c:when>
										<c:otherwise>
											<a href="#">
												My Account
											</a>
											<ul class="account_selection">
												<li><a href="../login"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
												<li><a href="../register"><i class="fa fa-user-plus" aria-hidden="true"></i>SignUp</a></li>
											</ul>
										</c:otherwise>
									</c:choose>	
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Main Navigation -->

		<div class="main_nav_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-right">
						<div class="logo_container">
							<a href="#">наргиле<span>mag</span></a>
						</div>
						<nav class="navbar">
							<ul class="navbar_menu">
								<li><a href="#">home</a></li>
								<li><a href="#">shop</a></li>
								<li><a href="#">promotion</a></li>
								
							</ul>
							<ul class="navbar_user">
								<f:form class="example" action="search" style="margin:auto;max-width:300px" method="POST">
  									<input type="text" placeholder="Search.." name="name">
 									<button type="submit"><i class="fa fa-search"></i></button>
								</f:form>
							</ul>

							
						</nav>
					</div>
				</div>
			</div>
		</div>

	</header>

	<div class="container single_product_container">
	
		<c:forEach items="${cart }" var="item">
		<div class="row">
			
			<div class="col-lg-5">
			
				<div class="product_details">
					<div class="product_details_title" style="padding-top:60px;">
						<h2><c:out value="${item.key.name }"></c:out></h2>
						<p>&nbsp;PRICE: &nbsp;<c:out value="${item.key.price}"></c:out></p>
						<p>&nbsp;AMOUNT: &nbsp;<c:out value="${item.value }"></c:out></p>
					</div>
				</div>
					<f:form commandName="product" action="/MyProject/order/increase" class="cartbutton" method="POST">
							<div class="red_button add_to_cart_button">
								<input type="submit" value="Increase">
								<f:input type = "hidden" path = "id" value = "${item.key.id }"/>
								<f:input type = "hidden" path = "name" value = "${item.key.name }"/>
								<f:input type = "hidden" path = "description" value = "${item.key.description }"/>
								<f:input type = "hidden" path = "price" value = "${item.key.price }"/>
								<f:input type = "hidden" path = "ammountInStock" value = "${item.key.ammountInStock }"/>
								<f:input type = "hidden" path = "categoryId" value = "${item.key.categoryId }"/>
								<f:input type = "hidden" path = "discountPercent" value = "${item.key.discountPercent }"/>
							</div>
					</f:form>
					<f:form commandName="product" action="/MyProject/order/decrease" class="cartbutton" method="POST">
							<div class="red_button add_to_cart_button">
								<input type="submit" value="Decrease">
								<f:input type = "hidden" path = "id" value = "${item.key.id }"/>
								<f:input type = "hidden" path = "name" value = "${item.key.name }"/>
								<f:input type = "hidden" path = "description" value = "${item.key.description }"/>
								<f:input type = "hidden" path = "price" value = "${item.key.price }"/>
								<f:input type = "hidden" path = "ammountInStock" value = "${item.key.ammountInStock }"/>
								<f:input type = "hidden" path = "categoryId" value = "${item.key.categoryId }"/>
								<f:input type = "hidden" path = "discountPercent" value = "${item.key.discountPercent }"/>
							</div>
					</f:form>
					
				
			</div>
		</div>
					
		</c:forEach>
											
					<c:if test="${not empty loggedUser}">
						<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
							<f:form action="../addToCart" class="cartbutton" method="GET">
									<div class="red_button add_to_cart_button">
										<input type="hidden" name="ordered_product" value="${product.getId() }">
										<input type="submit" value="Add to cart">
									</div>
								</f:form>
							<c:choose>
								<c:when  test="${contains }">
									<f:form action="../removeFavourite" class="cartbutton" method="POST">
											<div class="red_button add_to_cart_button">
												<input type="hidden" name="fav_product" value="${product.getId() }">
												<input type="submit" value="Remove from favs">
											</div>
									</f:form>
								</c:when>
								<c:otherwise>
									<f:form action="../addToFavourites" class="cartbutton" method="POST">
											<div class="red_button add_to_cart_button">
												<input type="hidden" name="fav_product" value="${product.getId() }">
												<input type="submit" value="Add to favs">
											</div>
									</f:form>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</div>
			</div>
		</div>

	</div>
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/isotope.pkgd.min.js"></script>
<script src="js/owl.carousel.js"></script>
<script src="js/easing.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/single_custom.js"></script>
</body>
</html>