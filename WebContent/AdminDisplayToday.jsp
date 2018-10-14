<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.List"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.java.components.FlightTemplate"%>

<html>
<head>
<meta charset="UTF-8">
<title>Admin Homepage</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>
<body>


	<%
		List<FlightTemplate> flights = (List<FlightTemplate>) session.getAttribute("flights");
		/*   System.out.println(flights); */
	%>
    
    
	<h4>${requestScope.errorMsg}</h4>
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
							</tbody>
						</table>
					</div>
					<%} %>
			</div>
		</div>
	</c:if> 


</body>
</html>