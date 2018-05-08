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
<title>Single Product</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Colo Shop Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="../css/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="../css/animate.css">
<link rel="stylesheet" href="../css/themify-icons.css">
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../css/single_styles.css">
<link rel="stylesheet" type="text/css" href="../css/single_responsive.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
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
												<li><a href="../logout">Logout</a></li>
												<li><a href="../favourites">Favourites</a></li>
												<li><a href="../order">Cart</a></li>
												<li><c:out value="${user.balance } лв."></c:out></li>
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


	<div class="fs_menu_overlay"></div>

	<!-- Hamburger Menu -->

	

	<div class="container single_product_container">
	
		<div class="row">
			<div class="col-lg-7">
				<div class="single_product_pics">
					<div class="row">
						
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<div class="single_product_image_background" style="background-image:url('download/${product.imgURL}')"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="product_details">
					<div class="product_details_title" style="padding-top:60px;">
						<h2><c:out value="${product.name }"></c:out></h2>
						<p>&nbsp;<c:out value="${product.getDescription() }"></c:out></p>
						<p>&nbsp;ON STOCK: &nbsp;<c:out value="${product.getAmmountInStock()}"></c:out></p>
						<p>&nbsp;CATEGORY: &nbsp;<c:out value="${category}"></c:out></p>
						<p>&nbsp;<c:out value="${characteristic} :"></c:out>&nbsp;
								<c:out value="${characteristicValue}"></c:out>
								<c:if test="${not empty characteristicUnit }">
									<c:out value="${characteristicUnit}"></c:out>
								</c:if>		
						</p>
						
					</div>
					<c:choose>
						<c:when test="${product.getDiscountPercent() > 0}">
							<div class="original_price"><c:out value="${product.price } лв."></c:out></div>
							<div class="product_price"><c:out value="${product.getDiscountPrice() } лв."></c:out></div>
	
						</c:when>
						<c:otherwise>
							<div class="product_price"><c:out value="${product.price } лв."></c:out></div>						
						</c:otherwise>
					</c:choose>
					<c:if test="${not empty user}">
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
							<c:if test="${user.admin  }">
									<f:form action="../updateProduct" class="cartbutton" method="GET">
											<div class="red_button add_to_cart_button" style="margin-top:90px;margin-left:-339px;">
												<input type="hidden" name="changed_product" value="${product.getId() }">
												<input type="submit" value="Update">
											</div>
									</f:form>
									<f:form action="../discount" class="cartbutton" method="GET">
										<div class="red_button add_to_cart_button" style="margin-top:90px;margin-left:-160px;">
											<input type="hidden" name="dicount_prod_id" value="${product.getId() }">
											<input type="submit" value="Discount">
										</div>
									</f:form>
									<f:form action="../deleteProduct" class="cartbutton" method="POST">
										<div class="red_button add_to_cart_button" style="margin-top:180px;margin-left:-339px;">
											<input type="hidden" name="deleted_product" value="${product.getId() }">
											<input type="hidden" name="deleted_product_name" value="${product.name}">
											<input type="submit" value="Delete product">
										</div>
									</f:form>
							</c:if>
						</div>
					</c:if>
				</div>
			</div>
		</div>

	</div>
</div>

<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/popper.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/isotope.pkgd.min.js"></script>
<script src="../js/owl.carousel.js"></script>
<script src="../js/easing.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/single_custom.js"></script>
</body>

</html>