<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Foodify</title>
  <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>" />
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>

<body class="login">
<!-- Default form login -->
<c:url value="/create/resend-email-verification" var="resendEmail" />
<div class="offset_login"></div>
<form action="${resendEmail}" method="post" enctype="application/x-www-form-urlencoded" class="centered_login text-center border border-light p-5 col-xl-4 col-lg-6 col-md-6 col-sm-8 col-xs-10 container">

  <img class="logo" src="<c:url value="/resources/img/foodify.png"/>" alt="LOGO">
  <input type="hidden" name="token" value="<c:out value="${token}"/>">
  <input type="hidden" name="uri" value="<c:out value="${uri}"/>">
  <h2 class="text-center"><spring:message code="resendEmail.header"/></h2>
  <!-- Resend button -->
  <button class="btn btn-info btn-block my-4" type="submit"><spring:message code="resendEmail.resend"/></button>

</form>
<div class="offset_login"></div>


<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>

</body>

</html>
