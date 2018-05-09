<%@page import="java.util.ArrayList"%>
<%@page import="com.nargilemag.model.Product"%>
<%@page import="com.nargilemag.model.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.nargilemag.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  %>
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
												<c:if test="${user.admin }">
													<li><a href="addproduct">Add Product</a></li>
												</c:if>
												<li><c:out value="${user.balance } лв."></c:out></li>
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
								<li><a href="index">HOME</a></li>
								<c:forEach items="${categories }" var="p">
									<li><a href="category/${p.id}"><c:out value="${p.name }"></c:out></a></li>
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
	<div class="banner">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="banner_item align-items-center" style="background-image:url(img/nargile-mini-zeleno.jpg)">
						<div class="banner_category">
							<a href="category/1">hookahs</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="banner_item align-items-center" style="background-image:url(img/tobacoo.jpg)">
						<div class="banner_category">
							<a href="category/2">tobacco</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="banner_item align-items-center" style="background-image:url(img/box.jpg)">
						<div class="banner_category">
							<a href="category/3">boxes cubes</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- New Arrivals -->
	
	
	<div class="new_arrivals">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title new_arrivals_title">
						<h2>our products</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
						<c:forEach items="${products }" var="p">
							<div class="product-item men">
							<div class="product discount product_filter">
								<div class="product_image">
									<a href="product/${p.id }"><img src="download/${p.imgURL}" alt=""/></a>
								</div>
								<!-- промоции -->
								<c:if test="${p.discountPercent > 0}">
									<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-<c:out value="${p.getDiscountPercent()}"></c:out>%</span></div>
								</c:if>
								<div class="product_info">
									<h6 class="product_name"><a href="#"><c:out value="${p.name }"></c:out></a></h6>
									<c:choose>
										<c:when test="${p.discountPercent > 0}">
											<div class="product_price"><c:out value="${p.getDiscountPrice()} лв."></c:out><span><c:out value="${p.price} лв."></c:out></span></div>
										</c:when>
										<c:otherwise>
											<div class="product_price"><c:out value="${p.price} лв."></c:out></div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<c:if test="${not empty user}">
								<f:form action="addToCart" class="cartbutton" method="GET">
									<div class="red_button add_to_cart_button">
										<input type="hidden" name="ordered_product" value="${p.getId() }">
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
	<div class="best_sellers">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title new_arrivals_title">
						<h2>promotions</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="product_slider_container">
						<div class="owl-carousel owl-theme product_slider">
							
							<c:forEach items="${promotions }" var="pr">
								<div class="owl-item product_slider_item">
									<div class="product-item">
										<div class="product discount">
											<div class="product_image">
												<a href="product/${pr.id }"><img src="download/${pr.imgURL}" alt=""/></a>
											</div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-<c:out value="${pr.getDiscountPercent()}"></c:out>%</span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="#"><c:out value="${pr.name }"></c:out></a></h6>
												<div class="product_price"><c:out value="${pr.getDiscountPrice()} лв."></c:out><span><c:out value="${pr.price} лв."></c:out></span></div>
											</div>
										</div>
									</div>
								</div>
								
							</c:forEach>
							
						</div>

						<!-- Slider Navigation -->

						<div class="product_slider_nav_left product_slider_nav d-flex align-items-center justify-content-center flex-column">
							<i class="fa fa-chevron-left" aria-hidden="true"></i>
						</div>
						<div class="product_slider_nav_right product_slider_nav d-flex align-items-center justify-content-center flex-column">
							<i class="fa fa-chevron-right" aria-hidden="true"></i>
						</div>
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
