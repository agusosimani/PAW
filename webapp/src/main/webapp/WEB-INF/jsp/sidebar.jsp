<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<section class="navigation">

    <!-- Main -->
    <div class="navigation__list">
        <c:url value="/" var="homeUrl"/>
        <a class="bg-transparent" href="${homeUrl}">
            <img class="nav_logo" src="<c:url value="/resources/img/foodify_inline.png"/>" alt="LOGO">
        </a>

        <div>
            <a class="float_left" href="${homeUrl}">
                <label><spring:message code="Home"/></label>
            </a>
        </div>

    </div>
    <!-- / -->

    <!-- Your Music -->
    <div class="navigation__list">

        <div>
            <c:url value="/my_account" var="myAccountUrl"/>
            <a class="float_left" href="${myAccountUrl}">
                <label><spring:message code="myAccount"/></label>
            </a>

            <div class="bar_arrow navigation__list__header"
                 role="button"
                 data-toggle="collapse"
                 href="#myAccount"
                 aria-expanded="true"
                 aria-controls="myAccount">
            </div>
        </div>

        <div class="collapse in" id="myAccount">

            <c:url value="/user_recipes" var="userRecipesUrl"/>
            <a href="${userRecipesUrl}" class="navigation__list__item">
                <!-- <i class="ion-headphone"></i> -->
                <span><spring:message code="myRecipes"/></span>
            </a>

            <c:url value="/my_ingredients" var="myIngredientsUrl"/>
            <a href="${myIngredientsUrl}" class="navigation__list__item">
                <!-- <i class="ion-ios-musical-notes"></i> -->
                <span><spring:message code="myIngredients"/></span>
            </a>

            <%--
            <a href="#" class="navigation__list__item">
                <i class="ion-person"></i>
                <span><spring:message code="myLists"/></span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-document"></i>
                <span><spring:message code="myFavouriteRecipes"/></span>
            </a>
            --%>
        </div>

    </div>
    <!-- / -->


</section>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>

</body>
</html>
