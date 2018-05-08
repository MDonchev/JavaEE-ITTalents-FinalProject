<%@page import="com.nargilemag.model.Gender"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/owl.theme.default.css">
	<link rel="stylesheet" type="text/css" href="css/animate.css">
	<link rel="stylesheet" type="text/css" href="css/main_styles.css">
	<link rel="stylesheet" type="text/css" href="css/responsive.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/inputform.css">
	<title>Sign In</title>
	</head>
	<body>
	<div class="super_container">
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
														<li><a href="logout">logout</a></li>
														<li><a href="order">Favourites</a></li>
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
										<li><a href="#">home</a></li>
										<li><a href="#">shop</a></li>
										<li><a href="#">promotion</a></li>
										
									</ul>
									<ul class="navbar_user">
										<f:form class="example" action="search" style="margin:auto;max-width:300px" method="POST">
		  									<input type="text" placeholder="Search.." name="name">
		 									<button type="submit" style="height:45px;"><i class="fa fa-search"></i></button>
										</f:form>
									</ul>
		
									
								</nav>
							</div>
						</div>
					</div>
				</div>
		
			</header>
		<div class="container" style="padding:200px;">
			
		            <f:form class="form-horizontal" role="form" action = "addToCart" method = "post" commandName="product">
		                <h2>Add to cart</h2>
		                <div class="form-group">
		                    <label for="amount" class="col-sm-3 control-label">Product</label>
		                    <div class="col-sm-9">
		                    	<input type="hidden">
		                        <label style="margin-top:7px; font-size:22px;"><c:out value="${product.name }"></c:out></label>
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label for="amount" class="col-sm-3 control-label">Amount to add</label>
		                    <div class="col-sm-9">
		                    	<f:input type="hidden" path="id" value="${product.id }"/>
								<f:input type="hidden" path="name" value="${product.name }"/>
								<f:input type="hidden" path="description" value="${product.description }"/>
								<f:input type="hidden" path="price" value="${product.price }"/>
								<f:input type="hidden" path="ammountInStock" value="${product.ammountInStock }"/>
								<f:input type="hidden" path="discountPercent" values="${product.discountPercent }"/>
		                        <input style="color:black;font-size:17px;margin-top:15px;" type="text" name="ammount" class="form-control" autofocus required>
		                    </div>
		                </div>
		                
		                <div class="form-group">
		                    <div class="col-sm-9 col-sm-offset-3">
		                        	<button style="color:white;font-size:17px;" type="submit" class="btn btn-primary btn-block">Add</button>
		                    </div>
		                </div>
		            </f:form> <!-- /form -->
		        </div> <!-- ./container -->
			</div> 
	</body>
</html>