<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
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

<body>
<%@include file="sidebar.jsp" %>

<section class="main_container">
    <%@include file="userbar.jsp" %>

    <h3>My ingredients</h3>

    <br/>

    <div class="browse">

        <ul class="list-group">
            <c:forEach var="ingredient" items="${ingredientsList}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                        ${ingredient.ingredient.name}
                    <span class="badge badge-primary badge-pill">${ingredient.amount}</span>
                </li>
            </c:forEach>
        </ul>
    </div>

</section>

<button type="button" class="btn btn-green add" data-toggle="modal" id="add-ingredient-modal" data-target="#add-ingredient">+</button>
<%@include file="add_ingredients.jsp" %>


<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>

<%--TODO no deberia estar aca    --%>
<script>
    autocomplete(document.getElementById("Item"), ingredients);
    autocomplete(document.getElementById("cuisine"), cuisine);
    autocomplete(document.getElementById("special_consideration"), special_considerations);
</script>

</body>

</html>
