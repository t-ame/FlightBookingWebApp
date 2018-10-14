<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

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
				
				<li class="nav-item"><a class="nav-link" href="./CustomerFlightSearch.jsp">Search for flights</a></li>

			</ul>

		</div>
	</nav>



<h6 style="color: red">${requestScope.errorMsg}</h6>

	<form class="form-horizontal" action="./login" method="post">
		<div class="form-group">
			<label for="userName" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="userName"
					name="userName" required placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="password"
					name="password" required placeholder="Password">
			</div>
		</div>

		<input type="hidden" name="isAdmin" id="isAdmin" value="false">

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Sign in</button>
			</div>

			<div class="col-sm-offset-2 col-sm-10">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="./CustomerRegistration.jsp">Register new account</a>
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-6">
				<div class="col-sm-offset-2 col-sm-6">
					<a style="font-size: 14px" href="./AdminLogin.jsp">Login as Admin</a>
				</div>
			</div>
		</div>

	</form>



</body>
</html>