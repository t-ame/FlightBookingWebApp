<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


</head>


  <body class="bg-light">

    <div class="container">
      <div class="py-5 text-center">
        <h2>Registration form</h2>
        </div>

		<div class="col-md-8 order-md-1" style="margin-left: 200px">
			<!-- <h4 class="mb-3">Billing address</h4> -->
			<form class="needs-validation" action="./registration" method="post">
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="first_name">First name</label> <input type="text"
							class="form-control" id="first_name" name="first_name"
							placeholder="" value="" required>
						<div class="invalid-feedback">Valid first name is required.
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="last_name">Last name</label> <input type="text"
							class="form-control" id="last_name" name="last_name"
							placeholder="" value="" required>
						<div class="invalid-feedback">Valid last name is required.</div>
					</div>
				</div>

				<div class="mb-3">
					<label for="userName">Email</label> <input type="email"
						class="form-control" required id="userName" name="userName"
						placeholder="you@example.com">
					<div class="invalid-feedback">Please enter a valid email
						address.</div>
				</div>

				<div class="mb-3">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="password" required>
					<div class="invalid-feedback">Please enter a password.</div>
				</div>


				<div class="mb-3">
					<label for="gender">Gender</label><br> <label
						class="radio-inline"> <input type="radio" name="gender"
						id="inlineRadio1" value="option1"> Female
					</label> <label class="radio-inline"> <input type="radio"
						name="gender" id="inlineRadio2" value="option2"> Male
					</label>
				</div>


				<div class="mb-3">
					<label for="date_of_birth">Date of Birth</label> <input type="date"
						class="form-control" id="date_of_birth" name="date_of_birth"
						placeholder="password" required>
					<div class="invalid-feedback">Please enter your date of birth.</div>
				</div>

				<!-- 	<input type="radio" name="gender" value="female">Female
		<input type="radio" name="gender" value="male">Male -->

            <div class="mb-3">
              <label for="street_address">Address</label>
              <input type="text" class="form-control" id="street_address" name="street_address" placeholder="1234 Main St" required>
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>

            <div class="mb-3">
              <label for="address_line_2">Address 2 <span class="text-muted">(Optional)</span></label>
              <input type="text" class="form-control" id="address_line_2" name="address_line_2" placeholder="Apartment or suite">
            </div>

            <div class="row">
              <div class="col-md-5 mb-3">
                <label for="country">Country</label>
                <select class="custom-select d-block w-100" id="country" name="country" required>
                  <option value="" disabled selected>Choose...</option>
                  <option>United States</option>
                </select>
                <div class="invalid-feedback">
                  Please select a valid country.
                </div>
              </div>
              <div class="col-md-4 mb-3">
                <label for="state">State</label>
                <select class="custom-select d-block w-100" id="state" name="state" required>
                  <option value="" disabled selected>Choose...</option>
                  <option>California</option>
                </select>
                <div class="invalid-feedback">
                  Please provide a valid state.
                </div>
              </div>
              <div class="col-md-3 mb-3">
                <label for="zip_code">Zip</label>
                <input type="text" class="form-control" id="zip_code" name="zip_code" placeholder="" required>
                <div class="invalid-feedback">
                  Zip code required.
                </div>
              </div>
            </div>
            <hr class="mb-4">
            <input type="hidden" name="isAdmin" id="isAdmin" value="true">
            
            <button class="btn btn-primary btn-lg btn-block" type="submit">Register</button>
            
            </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-2018 Flight Company</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="#">Privacy</a></li>
          <li class="list-inline-item"><a href="#">Terms</a></li>
          <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
      </footer>
    






    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>


</body>
</html>