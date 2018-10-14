<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Booking</title>
</head>
<body>



	<form action="/jsptojsp" name="flight_booking_form" id="flight_booking_form" method="post">
	
		<label for="full_name">Passenger Name: </label>
		<input type="text" name="full_name" required>
		
		<label for="mobile">Mobile: </label>
		<input type="number" name="mobile">
		
		<label for="email">Email: </label>
		<input type="email" name="email">
		<br>
	
		<label for="departure">Departure: </label>
		<input type="text" name="departure" readonly value="${sessionScope.flight.from}">
		
		<label for="departure_time">Time: </label>
		<input type="text" name="departure_time" readonly value="${sessionScope.flight.departureTime}"> 
		<br>
		
		<label for="arrival">Arrival: </label>
		<input type="text" name="arrival" readonly value="${sessionScope.flight.to}">
		
		<label for="arrival_time">Time: </label>
		<input type="text" name="arrival_time" readonly value="${sessionScope.flight.arrivalTime}">
		
		<input type="hidden" name="foward_jsp" id="foward_jsp" value="PaymentPage.jsp">
		
		<button type="button" onclick="backButton()">Back</button>
		<button type="submit">Proceed to pay</button>
		<button type="reset">Clear</button>
	
	</form>






    <script type="text/javascript">
        function backButton()
        {
            document.getElementById('foward_jsp').value = "CustomerFlightSearch.jsp";
            flight_booking_form.submit();
        } 
    </script>

</body>
</html>