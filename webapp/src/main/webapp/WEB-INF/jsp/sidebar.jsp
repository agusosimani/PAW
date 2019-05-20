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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link href="<c:url value="/resources/css/fontawesome-all.css"/>" rel="stylesheet">
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

        <div class="collapse in collapse-sidebar" id="myAccount">

            <c:url value="/my_recipes" var="userRecipesUrl"/>
            <a href="${userRecipesUrl}" class="navigation__list__item">
                <span><spring:message code="myRecipes"/></span>
            </a>

            <c:url value="/my_ingredients" var="myIngredientsUrl"/>
            <a href="${myIngredientsUrl}" class="navigation__list__item">
                <span><spring:message code="myIngredients"/></span>
            </a>

            <c:url var="cooklistsUrl" value="/your_cooklists"/>
            <a href="${cooklistsUrl}" class="navigation__list__item">
                <span><spring:message code="myCooklists"/></span>
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

    <div class="navigation__list">
        <div>
            <c:url value="/logout" var="logOutB"/>
            <a href="${logOutB}" class="navigation__list__item">
                <!-- <i class="ion-ios-musical-notes"></i> -->
                <span><spring:message code="logOut"/></span>
            </a>
        </div>
    </div>

    <c:if test="${not empty user}">
        <div class="navigation__list navigation_list_modal">
            <div>
                <button type="button" class="bg-transparent" data-toggle="modal" id="user-modal-btn" data-target="#user-modal">
                    <i class="fas fa-user fa-2x green-ic"></i>
                </button>
            </div>
        </div>
    </c:if>

    <c:if test="${isIndex eq true}">
        <div class="navigation__list navigation_list_modal">
            <div>
                <button type="button" class="bg-transparent" data-toggle="modal" id="filters-modal-btn" data-target="#filters-modal">
                    <i class="fas fa-filter fa-2x green-ic"></i>
                </button>
            </div>
        </div>
    </c:if>
</section>

<div class="modal fade" id="user-modal" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <%@include file="userbar.jsp" %>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="filters-modal" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <%@include file="filters_card.jsp" %>
            </div>
        </div>
    </div>
</div>

</body>
</html>
