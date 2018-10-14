<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Page</title>



<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div style="background-color: lightblue; text-align: center">
		<a class="navbar-brand" href="./index.jsp">Toya Air</a>
		
	</div>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a class="nav-link"
					href="./CustomerFlightSearch.jsp">Find flight</a></li>

			</ul>

			
			<input type="hidden" name="isAdmin" value="true"> <a
				class="nav-link" href="./navaccount">My Accounts <span
				class="sr-only">(current)</span></a> <a class="nav-link" href="./logout">
				Logout<span class="sr-only">(current)</span>
			</a>


		</div>

	</nav>



	<form action="./flightbook" id="payment_form" method="post"
		style="width: 800px; margin-left: 100px">


		<h4 class="mb-3">Payment</h4>
		<hr />

		<div class="d-block my-3">
			<div class="custom-control custom-radio">
				<input id="credit" name="paymentMethod" type="radio"
					class="custom-control-input" checked required> <label
					class="custom-control-label" for="credit">Credit card</label>
			</div>
			<div class="custom-control custom-radio">
				<input id="debit" name="paymentMethod" type="radio"
					class="custom-control-input" required> <label
					class="custom-control-label" for="debit">Debit card</label>
			</div>
			<div class="custom-control custom-radio">
				<input id="paypal" name="paymentMethod" type="radio"
					class="custom-control-input" required> <label
					class="custom-control-label" for="paypal">Paypal</label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 mb-3">
				<label for="full_name">Name on card</label> <input type="text"
					class="form-control" id="full_name" name="full_name" placeholder=""
					required> <small class="text-muted">Full name as
					displayed on card</small>
				<div class="invalid-feedback">Name on card is required</div>
			</div>
			<div class="col-md-6 mb-3">
				<label for="card_number">Credit card number</label> <input
					type="text" class="form-control" id="card_number"
					name="card_number" placeholder="" required>
				<div class="invalid-feedback">Credit card number is required</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 mb-3">
				<label for="cc-expiration">Expiration</label> <input type="text"
					class="form-control" id="cc-expiration" placeholder="" required>
				<div class="invalid-feedback">Expiration date required</div>
			</div>
			<div class="col-md-3 mb-3">
				<label for="cc-expiration">CVV</label> <input type="text"
					class="form-control" id="cc-cvv" placeholder="" required>
				<div class="invalid-feedback">Security code required</div>
			</div>
		</div>
		<hr class="mb-4">

		<button class="btn btn-primary btn-lg btn-block" type="button"
			onclick="backButton()">Back</button>

		<button class="btn btn-primary btn-lg btn-block" type="submit">Pay</button>



		<input type="hidden" name="foward_jsp" id="foward_jsp"
			value="CustomerFlightBooking.jsp">

	</form>








	<script type="text/javascript">
		function backButton() {
			document.getElementById('payment_form').action = "/jsptojsp";
			payment_form.submit();
		}
	</script>


</body>
</html>