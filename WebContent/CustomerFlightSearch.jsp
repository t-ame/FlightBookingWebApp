<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="com.java.components.FlightTemplate"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Flight Search</title>
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

			<div>

				<c:if test="${sessionScope.userdetails != null}">

					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link"
							href="./AdminHome.jsp">Profile</a></li>
					</ul>

				</c:if>

				<c:if test="${sessionScope.userdetails == null}">

					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a href="./CustomerLogin.jsp">Login</a></li>
					</ul>
				</c:if>
			</div>

		</div>
	</nav>





	<main role="main" class="container">
	<form class="needs-validation" action="./flightsearch" method="post">
		<div
			style="padding: 2rem 1rem; margin-bottom: 2rem; background-color: #e9ecef; border-radius: .3rem;">
			<h1>Flight Search</h1>
			<div class="row mt-4">
				<div class="col-md-3">
					<label for="departure">From: </label> <select required
						name="departure" class="form-control" >
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
						name="destination" class="form-control" >
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
						class="form-control" id="departure_date" name="departure_date" placeholder=""yyyy-MM-dd""
						value="" required>
				</div>
				<div class="col-md-3 mt-2">
					<label></label>
					<button class="btn btn-primary btn-lg btn-block"
						style="padding: 1px 7px 2px;" type="submit">Search</button>
				</div>
			</div>
		</div>
	</form>
	 
	 <%
	 
	    List<FlightTemplate> flights = (List<FlightTemplate>) session.getAttribute("flights");
	 
	 %>
	
	
	<h6 style="color: Red">${requestScope.errorMsg}</h6>
	<% request.setAttribute("errorMsg", ""); %>
	
	<c:if test="${sessionScope.flights !=null}">
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Flight Information
			</div>
			<div class="card-body">
				<c:if test="${flightList.size()==0}">
					<div class="alert alert-warning" role="alert">Have not found
						matched records.</div>
				</c:if>
				<%-- <c:if test="${flightList.size()>0}"> --%>
				<%if(flights.size()>0) {%>
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th scope="col">#</th>
									<td scope="col">Airline</td>
									<td scope="col">Departure</td>
									<td scope="col">Destination</td>
									<td scope="col">Departure Time</td>
									<td scope="col">Arrival Time</td>
									<td scope="col">Available Seats</td>
									<td scope="col">Cost</td>
									<td scope="col"></td>
								</tr>
							</thead>
							<tbody>
								<%-- <c:forEach items="${flightList}" var="flight"> --%>
								<%for(int i=0; i<flights.size(); ++i){ %>
									<tr>
										<th scope="row">1</th>
										<td><%= flights.get(i).getAirline() %></td>
										<td><%= flights.get(i).getFrom() %></td>
										<td><%= flights.get(i).getTo()%></td>
										<td><%= flights.get(i).getDepartureTime()%></td>
										<td><%= flights.get(i).getArrivalTime()%></td>
										<td><%= flights.get(i).getAvailableSeats()%></td>
										<td><%= flights.get(i).getPrice()%></td>
										<td><a href="./booking/<%= flights.get(i).getId()%>">Book</a></td>
									</tr>
									<%} %>
								<%-- </c:forEach> --%>
							</tbody>
						</table>
					</div>
					<%} %>
			<%-- 	</c:if> --%>
			</div>
		</div>
	</c:if> 
	
	</main>





<script type="text/javascript" src="WebContent/bootsrap/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="Scripts/jquery-2.1.1.min.js"></script>

</body>


</html>