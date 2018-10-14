<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="java.util.List"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.java.components.FlightTemplate"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>


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

				<li class="nav-item"><a class="nav-link" href="./viewflights">View
						Booked Flights</a></li>
				<li class="nav-item"><a class="nav-link" href="./allflights">View
						All Scheduled Flights</a></li>

			</ul>

			<input type="hidden" name="isAdmin" value="true"> <a
				class="nav-link" href="./navaccount">My Accounts <span
				class="sr-only">(current)</span></a> <a class="nav-link" href="./logout">
				Logout<span class="sr-only">(current)</span>
			</a>


		</div>
	</nav>





	<div style="display: flex; height: 100%">

		<h5 style="color: Green">
			<b>${requestScope.successMsg}</b>
		</h5>
		<%
			request.setAttribute("successMsg", "");
		%>
		<div style="background-color: slateblue" class="floaters">

			<ul style="list-style-type: none">
				<li><a href="./allflights" style="color: white">View All
						Flights</a></li>
				<li><a href="./viewflights" style="color: white">View All
						Bookings</a></li>
				<li><a href="./AdminAddFlight.jsp" style="color: white">Add
						New Flight</a></li>
				<li><a href="./changeflight" style="color: white">Edit
						flights</a></li>
			</ul>

		</div>

		<div class="floaters" style="margin-left: 10px; background-color: lightgray; width: 850px">

			<%
				List<FlightTemplate> flights = (List<FlightTemplate>) session.getAttribute("flights");
				/*   System.out.println(flights); */
			%>


			<h4>${requestScope.errorMsg}</h4>
			<%
				request.setAttribute("errorMsg", "");
			%>

			<c:if test="${sessionScope.flights !=null}">
				<div class="card mb-3">
					<div class="card-header" style="text-align: center">
						<h2>
							<i class="fas fa-table"> Today's Schedule</i>
						</h2>
					</div>
					<div class="card-body">
						<c:if test="${flightList.size()==0}">
							<div class="alert alert-warning" role="alert">Have not
								found matched records.</div>
						</c:if>
						<%
							if (flights.size() > 0) {
						%>
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
									</tr>
								</thead>
								<tbody>
									<%
										for (int i = 0; i < flights.size(); ++i) {
									%>
									<tr>
										<th scope="row">1</th>
										<td><%=flights.get(i).getAirline()%></td>
										<td><%=flights.get(i).getFrom()%></td>
										<td><%=flights.get(i).getTo()%></td>
										<td><%=flights.get(i).getDepartureTime()%></td>
										<td><%=flights.get(i).getArrivalTime()%></td>
										<td><%=flights.get(i).getAvailableSeats()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</c:if>

		</div>



	</div>




</body>
</html>