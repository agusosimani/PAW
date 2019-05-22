<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
  </head>
  <body class="error">
    <div class="offset_error"></div>
    <div class="centered_error text-center border border-light p-5 col-xl-4 col-lg-6 col-md-6 col-sm-8 col-xs-10 container">
      <h1><spring:message code="error"/></h1>
      <h2><spring:message code="apology"/> <spring:message code="error_explanation"/></h2>
      <h4 class="text-left"><spring:message code="details_error"/>
       <ul class="text-left">
         <li>Exception: <c:out value="${requestScope['javax.servlet.error.exception']}" /></li>
         <li>Exception type: <c:out value="${requestScope['javax.servlet.error.exception_type']}" /></li>
         <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
         <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}" /></li>
         <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></li>
         <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>
       </ul>
      </h4>
    </div>
  </body>
</html>
