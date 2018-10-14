<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Flight</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>
<body>



	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="./index.jsp">Toya Air</a>
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
					
					<div class="dropdown-menu">
						<a class="dropdown-item" href="./AdminHome.jsp">Profile</a> <a
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



	<form class="needs-validation" action="./addflight" method="post">
		<div
			style="padding: 2rem 1rem; margin-bottom: 2rem; background-color: #e9ecef; border-radius: .3rem;">
			<div style="margin-left: 250px">

				<h1>Flight Details</h1>
				<div class="row mt-4">

					<div class="col-md-3">
						<label for="airline">Airline </label> <input type="text"
							class="form-control" id="airline" name="airline"
							placeholder="Airline" required>
					</div>

					<div class="col-md-3">
						<label for="seats">Seats </label> <input type="number"
							class="form-control" id="seats" name="seats" placeholder="Seats"
							required>
					</div>

					<div class="col-md-3">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price" placeholder="Date"
							value="" required>
					</div>

				</div>

				<div class="row mt-4">
					<div class="col-md-3">
						<label for="source">From: </label> <select required name="source"
							class="form-control">
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

				</div>

				<div class="row mt-4">
					<div class="col-md-3">
						<label for="depart_time">Departure Date </label> <input
							type="date" class="form-control" id="depart_time"
							name="depart_time" required>
					</div>

					<div class="col-md-3">
						<label for="arrive_time">Arrival Date </label> <input type="date"
							class="form-control" id="arrive_time" name="arrive_time" required>
					</div>

				</div>

				<div class="row" style="margin-left: 150px">

					<div class="col-md-3 mt-2">
						<label></label>
						<button class="btn btn-primary btn-lg btn-block"
							style="padding: 1px 7px 2px;" type="submit">Save</button>
					</div>
					<div class="col-md-3 mt-2">
						<label></label>
						<button class="btn btn-primary btn-lg btn-block"
							style="padding: 1px 7px 2px;" type="reset">Clear</button>
					</div>

				</div>

			</div>
		</div>
	</form>



</body>
</html>