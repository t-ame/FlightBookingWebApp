<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>


<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="./index.jsp">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link"
					href="./CustomerFlightSearch.jsp">Find flight</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="./CustomerFlightSearch.jsp">Action</a>
						<a class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
				</li>

			</ul>


			<div class="dropdown">

				<c:if test="${sessionScope.userdetails != null}">

					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">Profile</button>

					<div class="dropdown-menu">
						<a class="dropdown-item" href="./AdminHome.jsp">My Account</a> <a
							class="dropdown-item" href="./logout">Logout</a>
					</div>

				</c:if>

				<c:if test="${sessionScope.userdetails == null}">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">Login</button>

					<div class="dropdown-menu">
						<a class="dropdown-item" href="./AdminLogin.jsp">Admin Login</a> <a
							class="dropdown-item" href="./CustomerLogin.jsp">Customer
							Login</a>

					</div>
				</c:if>

			</div>

		</div>
	</nav>



<h2 style="color: Green"><b>${requestScope.successMsg}</b></h2>





</body>
</html>