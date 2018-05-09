<%@page import="com.nargilemag.model.Gender"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  %>
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
		<link rel="stylesheet" href="css/inputform.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
		<link rel="stylesheet" type="text/css" href="css/owl.theme.default.css">
		<link rel="stylesheet" type="text/css" href="css/animate.css">
		<link rel="stylesheet" type="text/css" href="css/main_styles.css">
		<link rel="stylesheet" type="text/css" href="css/responsive.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<title>Sign Up</title>
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
										<c:when test="${not empty user}">
											<a href="#">
												<c:out value="${user.username }"></c:out>
											</a>
											<ul class="account_selection">
												<li><a href="logout">Logout</a></li>
												<li><a href="favourites">Favourites</a></li>
												<li><a href="order">Cart</a></li>
												<c:if test="${user.admin }">
													<li><a href="../addproduct">Add Product</a></li>
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
	
            <form class="form-horizontal" role="form" action = "register" method = "post">
                <h2>Sign Up</h2>
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">Username</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="text" id="username" name="username" placeholder="Username" class="form-control" autofocus required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">Email</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="email" id="email" name="email" placeholder="Email" class="form-control " required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">Password</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="password" id="password" name="password1" placeholder="Password" class="form-control" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-sm-3 control-label">Password</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="password" id="password" name="password2" placeholder="Confirm Password" class="form-control" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Address" class="col-sm-3 control-label">Address</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="text" id="username" name="address" placeholder="Address" class="form-control" autofocus required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Number" class="col-sm-3 control-label">Phone Number</label>
                    <div class="col-sm-9">
                        <input style="color:black;font-size:17px;" type="number" id="username" name="number" placeholder="Number" class="form-control" autofocus required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="gender" class="col-sm-3 control-label">Gender</label>
                    <div class="col-sm-9">
                        <select style="color:black;font-size:17px; height:35px;" id="gender" name="gender" class="form-control" required>
                        	<c:forEach items="${genders }" var= "gender">
								<option style="color:black;font-size:17px;" value="${gender.id }"><c:out value="${gender.name }"></c:out></option>
							</c:forEach>
                        </select>
                    </div>
                </div> <!-- /.form-group -->
               
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        	<button style="color:white;font-size:17px;" type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                </div>
            </form> <!-- /form -->
        </div> <!-- ./container -->
		</div>	 
	</body>
</html>