<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.List"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.java.components.FlightTemplate"%>

<%-- <%! FlightTemplate flight; %> --%>

<html>
<head>
<meta charset="UTF-8">
<title>All Scheduled flights</title>
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






	<%
		List<FlightTemplate> flights = (List<FlightTemplate>) session.getAttribute("flights");
		/*   System.out.println(flights); */
	%>
    
    
	<h6 style="color: Red">${requestScope.errorMsg}</h6>
	<% request.setAttribute("errorMsg", ""); %>
	
	<c:if test="${sessionScope.flights !=null}">
		<div class="card mb-3">
			<div class="card-header" style="text-align: center">
				<h2 ><i class="fas fa-table"> Flight Information</i></h2>
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
										<td><a href="./editflight/${flights.get(i).getId() } ">Edit</a> <a href="./deleteflight/${flights.get(i).getId() } ">Delete</a></td>
									</tr>
									<%} %>
								<%-- </c:forEach> --%>
							</tbody>
						</table>
					</div>
					<%} %>
				<%-- </c:if> --%>
			</div>
		</div>
	</c:if> 





</body>
</html>