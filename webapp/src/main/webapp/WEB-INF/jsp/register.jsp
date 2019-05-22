<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

  <body class="register">
  <c:url value="/create" var="createUrl" />
  <div class="offset_register"></div>
  <!-- Default form register -->
  <form:form modelAttribute="registerForm" action="${createUrl}" method="post" enctype="application/x-www-form-urlencoded" class="centered_register text-center border border-light p-5 col-xl-4 col-lg-6 col-md-6 col-sm-8 col-xs-10 container">
    <img class="logo" src="<c:url value="/resources/img/foodify.png"/>" alt="LOGO">

    <div class="form-row mb-4">
      <div class="col">
        <!-- First name -->
        <spring:message code="User.name" var="name"/>
        <form:input path="name" type="text"  id="defaultRegisterFormFirstName" class="form-control" placeholder="${name}"/>
        <form:errors path="name" cssClass="form-text text-muted" element="small"/>
      </div>
      <div class="col">
        <!-- Last name -->
        <spring:message code="User.surname" var="surname"/>
        <form:input path="surname" type="text" id="defaultRegisterFormLastName" class="form-control" placeholder="${surname}"/>
        <form:errors path="surname" cssClass="form-text text-muted" element="small"/>
      </div>
    </div>

    <!-- E-mail -->
    <div class="form-row mb-4">
    <spring:message code="User.email" var="email"/>
    <form:input path="email" type="text" id="defaultRegisterFormEmail" class="form-control" placeholder="${email}"/>
    <form:errors path="email" cssClass="form-text text-muted" element="small"/>
    </div>

    <!-- username -->
    <div class="form-row mb-4">
    <spring:message code="User.username" var="username"/>
    <form:input path="username" type="text" id="defaultRegisterFormEmail" class="form-control" placeholder="${username}"/>
    <form:errors path="username" cssClass="form-text text-muted" element="small"/>
    </div>

    <!-- Password -->
    <div class="form-row mb-4">
    <spring:message code="User.password" var="password"/>
    <form:input path="password" type="password" id="defaultRegisterFormPassword" class="form-control" placeholder="${password}" aria-describedby="defaultRegisterFormPasswordHelpBlock"/>
    <form:errors path="password" cssClass="form-text text-muted" element="small"/>
    </div>

    <!-- Repeat password -->
    <div class="form-row mb-4">
    <spring:message code="User.repeatPassword" var="repeatPassword"/>
    <form:input path="repeatPassword" type="password" id="defaultRegisterFormRepeatPassword" class="form-control" placeholder="${repeatPassword}"/>
    <form:errors path="repeatPassword" cssClass="form-text text-muted" element="small"/>
    </div>

    <!-- Sign up button -->
    <button class="btn btn-info my-4 btn-block" type="submit">
      <spring:message code="Register.signUp"/>
    </button>

  </form:form>
  <div class="offset_register"></div>


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