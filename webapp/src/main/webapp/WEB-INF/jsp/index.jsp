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
<c:set var="isIndex" value="true" scope="request"/>
<%@include file="sidebar.jsp" %>

<section class="main_container">

        <section class="browse">
            <%@include file="recipe_cards.jsp" %>
        </section>

        <section class="side_card">
            <div class="card">
                <div class="card-body" id="filters-big-card">
                    <%@include file="filters_card.jsp" %>
                </div>
            </div>
        </section>
</section>

<!-- Button trigger modal -->
<c:url value="/new_recipe" var="newRecipeUrl"/>
<a href="${newRecipeUrl}">
    <button class="btn btn-green add">+</button>
</a>

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/utils.js"/>"></script>

<%--TODO no deberia estar aca
<script>
    autocomplete(document.getElementById("Item"), ingredients);
    autocomplete(document.getElementById("cuisine"), cuisine);
    autocomplete(document.getElementById("special_consideration"), special_considerations);
</script>
--%>
</body>

</html>
