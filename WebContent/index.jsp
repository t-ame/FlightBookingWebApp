<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>


<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


<!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<style>
body, html {
	height: 100%;
}

.bg {
	/* The image used */
	background-image: url("./539327295-612x612.jpg");
	/* Half height */
	height: 50%;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

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

				<li class="nav-item"><a class="nav-link"
					href="./CustomerFlightSearch.jsp">Find flight</a></li>
				
			</ul>


			<div class="dropdown">

				<c:if test="${sessionScope.userdetails != null}">

						<a class="dropdown-item" href="./navhome">My Account</a> 
						<a class="dropdown-item" href="./logout">Logout</a>

				</c:if>

				<c:if test="${sessionScope.userdetails == null}">

						<a class="dropdown-item" href="./navlogin">Login</a>

				</c:if>

			</div>

		</div>
	</nav>


	<div class="bg"></div>


	<form class="needs-validation" action="./flightsearch" method="post">
		<div
			style="padding: 2rem 1rem; margin-bottom: 2rem; background-color: #e9ecef; border-radius: .3rem;">
			<h1>Flight Search</h1>
			<div class="row mt-4">
				<div class="col-md-3">
					<label for="departure">From: </label> <select required
						name="departure" class="form-control">
						<option disabled selected>Select</option>
						<option value="AL">Alabama</option>
						<option value="GA">Georgia</option>
						<option value="IL">Illinois</option>
						<option value="CA">California</option>
						<option value="TX">Texas</option>
					</select>
				</div>
				<div class="col-md-3">


					<label for="destination">To: </label> <select required
						name="destination" class="form-control">
						<option disabled selected>Select</option>
						<option value="AL">Alabama</option>
						<option value="GA">Georgia</option>
						<option value="IL">Illinois</option>
						<option value="CA">California</option>
						<option value="TX">Texas</option>
					</select>
				</div>
				<div class="col-md-3">
					<label for="departure_date">Date</label> <input type="date"
						class="form-control" id="departure_date" name="departure_date"
						placeholder="Date" value="" required>
				</div>
				<div class="col-md-3 mt-2">
					<label></label>
					<button class="btn btn-primary btn-lg btn-block"
						style="padding: 1px 7px 2px;" type="submit">Search</button>
				</div>
			</div>
		</div>
	</form>


</body>
</html>