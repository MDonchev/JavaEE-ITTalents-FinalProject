<%@page import="java.util.ArrayList"%>
<%@page import="com.nargilemag.model.Product"%>
<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.nargilemag.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Добре дошли в НаргилеМаг</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Colo Shop Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="css/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="css/animate.css">
<link rel="stylesheet" type="text/css" href="css/main_styles.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
										<c:when test="${not empty user}">
											<a href="#">
												<c:out value="${user.username }"></c:out>
											</a>
											<ul class="account_selection">
												<li><a href="logout">Logout</a></li>
												<li><a href="favourites">Favourites</a></li>
												<li><a href="order">Cart</a></li>
											</ul>
										</c:when>
										<c:otherwise>
											<a href="#">
												My Account
											</a>
											<ul class="account_selection">
												<li><a href="login"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
												<li><a href="register"><i class="fa fa-user-plus" aria-hidden="true"></i>SignUp</a></li>
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
								<li><a href="#">HOME</a></li>
								<c:forEach items="${categories }" var="p">
									<li><a href="#"><c:out value="${p.name }"></c:out></a></li>
								</c:forEach>
								
								
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

	

	<div class="main_slider"></div>

	<!-- Banner -->
	<div></div>
	

	<!-- New Arrivals -->
	
	<div class="new_arrivals">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title new_arrivals_title">
						<h2>favourites</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
						<c:forEach items="${favourites }" var="f">
							<div class="product-item men">
							<div class="product discount product_filter">
								<div class="product_image">
									<a href="product/${f.id }"><img src="download/${f.imgURL}" alt=""/></a>
								</div>
								<c:if test="${f.discountPercent > 0}">
									<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-<c:out value="${f.getDiscountPercent()}"></c:out>%</span></div>
								</c:if>
								<div class="product_info">
									<h6 class="product_name"><a href="#"><c:out value="${f.name }"></c:out></a></h6>
									<c:choose>
										<c:when test="${f.discountPercent > 0}">
											<div class="product_price"><c:out value="${f.getDiscountPrice()} лв."></c:out><span><c:out value="${f.price} лв."></c:out></span></div>
										</c:when>
										<c:otherwise>
											<div class="product_price"><c:out value="${f.price} лв."></c:out></div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<c:if test="${not empty user}">
								<f:form action="addToCart" class="cartbutton" method="GET">
									<div class="red_button add_to_cart_button">
										<input type="hidden" name="ordered_product" value="${f.getId() }">
										<input type="submit" value="Add to cart">
									</div>
								</f:form>
							</c:if>
						</div>
						
						
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>

	
	<!-- Best Sellers -->

	<div class="benefit"></div>
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/isotope.pkgd.min.js"></script>
<script src="js/owl.carousel.js"></script>
<script src="js/easing.js"></script>
<script src="js/custom.js"></script>
</body>

</html>
