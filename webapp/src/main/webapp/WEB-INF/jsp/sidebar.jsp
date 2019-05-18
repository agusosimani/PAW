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

            <c:url value="/my_recipes" var="userRecipesUrl"/>
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

        <c:url value="/logout" var="logOutB"/>
        <a href="${logOutB}" class="navigation__list__item">
            <!-- <i class="ion-ios-musical-notes"></i> -->
            <span><spring:message code="logOut"/></span>
        </a>

    </div>
    <!-- / -->


</section>

</body>
</html>
