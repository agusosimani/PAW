<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <body>
    <div class="row">
      <div class="col-2">
        <img style="width: 100%" src="<c:url value="/resources/img/foodify_2.png"/>" alt="LOGO">
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>
      </div>
      <div class="col-10">

      </div>
    </div>


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
