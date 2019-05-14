<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

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
                 aria-controls="yourMusic">
            </div>
        </div>

        <div class="collapse in" id="myAccount">

            <%--                <c:url value="/user_recipes" var="userRecipesUrl">--%>
            <%--                    <c:param name="userId" value="${user.id}"/>--%>
            <%--                </c:url>--%>
            <%--                <a href="${userRecipesUrl}" class="navigation__list__item">--%>
            <a href="#" class="navigation__list__item">

                <i class="ion-headphone"></i>
                <span><spring:message code="myRecipes"/></span>
            </a>

            <c:url value="/my_ingredients" var="myIngredientsUrl"/>
            <a href="${myIngredientsUrl}" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span><spring:message code="myIngredients"/></span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-person"></i>
                <span><spring:message code="myLists"/></span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-document"></i>
                <span><spring:message code="myFavouriteRecipes"/></span>
            </a>

        </div>

    </div>
    <!-- / -->


</section>
<%--<div class="row">--%>
<%--<div class="col-2">--%>
<%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>--%>
<%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>--%>
<%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>--%>
<%--</div>--%>
<%--<div class="col-10">--%>

<%--</div>--%>
<%--</div>--%>
</body>
</html>
