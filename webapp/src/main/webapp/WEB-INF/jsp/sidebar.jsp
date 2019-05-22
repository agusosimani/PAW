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

    <div class="navigation__list">
        <c:url value="/" var="homeUrl"/>
        <a class="bg-transparent" href="${homeUrl}">
            <img class="nav_logo" src="<c:url value="/resources/img/foodify_inline.png"/>" alt="LOGO">
        </a>

        <div class="main-item-sidebar">
            <a class="float_left" href="${homeUrl}">
                <span><spring:message code="Home"/></span>
            </a>
        </div>

    </div>


    <div class="navigation__list">

        <div class="main-item-sidebar">
            <c:url value="/my_account" var="myAccountUrl"/>
            <a class="float_left" href="${myAccountUrl}">
                <span><spring:message code="myAccount"/></span>
            </a>
        </div>

        <div class="sub-item-sidebar">

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

            <c:url var="statisticsUrl" value="/statistics"/>
            <a href="${statisticsUrl}" class="navigation__list__item">
                <span><spring:message code="myStatistics"/></span>
            </a>
        </div>

    </div>

    <div class="navigation__list">
        <div class="main-item-sidebar log-out-sidebar">
            <c:url value="/logout" var="logOutB"/>
            <a class="float-left" href="${logOutB}">
                <span><spring:message code="logOut"/></span>
            </a>
        </div>
    </div>

    <c:if test="${not empty user}">
        <div class="navigation__list navigation_list_modal">
            <div>
                <button type="button" class="bg-transparent btn-modal-sidebar" data-toggle="modal" id="user-modal-btn"
                        data-target="#user-modal">
                    <i class="fas fa-user fa-2x green-ic"></i>
                </button>
            </div>
        </div>
    </c:if>

    <c:if test="${isIndex eq true}">
        <div class="navigation__list navigation_list_modal">
            <div>
                <button type="button" class="bg-transparent btn-modal-sidebar" data-toggle="modal"
                        id="filters-modal-btn" data-target="#filters-modal">
                    <i class="fas fa-filter fa-2x green-ic"></i>
                </button>
            </div>
        </div>
    </c:if>
</section>

<div class="modal fade" id="user-modal" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body" id="user-modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="filters-modal" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body" id="filters-modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
        </div>
    </div>
</div>

</body>
</html>
