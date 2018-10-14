<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

<div class="container">
  <div class="row">
    <div class="span12">
      <div class="hero-unit center">
          <h1>An Error occurred</h1>
          <br />
          <p>${requestScope.exceptionMsg}. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
          <p><b>Or you could go to the home page:</b></p>
          <a href="./index.jsp" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> Take Me Home</a>
        </div>
    </div>
  </div>
</div>


</body>
</html>