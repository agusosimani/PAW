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
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
</head>

<body><!--class="row"-->
<%@include file="sidebar.jsp" %>
<!-- Section: Blog v.2 -->
<section class="main_container">
    <h4 class="navigation-title">
        <c:choose>
            <c:when test="${yourAccount}">
                <spring:message code="myAccount"/>
            </c:when>
            <c:otherwise>
                <spring:message code="otherUserAccount" arguments="${user.username}"/>
            </c:otherwise>
        </c:choose>
    </h4>
    <section class="browse">

        <div class="card-deck">

                <c:url value="/user_recipes" var="userRecipesUrl">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>

                <!-- Featured image -->
                <div class="card card-cascade card-recipe ">
                    <div class="view view-cascade">
                        <div class="bg-dark">
                            <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                    </div>

                    <div class="card-body">
                        <h4 class="card-title"><strong><spring:message code="recipes"/></strong></h4>
                        <p class="card-text">
                            <c:choose>
                                <c:when test="${yourAccount}">
                                    <spring:message code="myRecipesExplanation"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="recipesExplanation" arguments="${user.username}"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <a href="${userRecipesUrl}" class="stretched-link"></a>
                    </div>
            </div>



                <div class="card card-cascade card-recipe ">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong><spring:message code="myLists"/></strong></h4>
                        <p class="card-text"><spring:message code="myListsExplanation"/></p>
                        <c:url var="cooklistsUrl" value="/user_cooklists">
                            <c:param name="userId" value="${user.id}"/>
                        </c:url>
                        <a href="${cooklistsUrl}" class="stretched-link"></a>
                    </div>
                </div>

            <c:if test="${yourAccount}">

                    <div class="card card-cascade card-recipe ">
                        <div class="view overlay rounded z-depth-2 mb-4">
                            <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>
                        <div class="card-body">
                            <c:url value="/my_ingredients" var="myIngredientsUrl"/>
                            <a href="${myIngredientsUrl}" class="stretched-link"></a>
                            <h4 class="card-title"><strong><spring:message code="myIngredients"/></strong></h4>
                            <p class="card-text"><spring:message code="myIngredientsExplanation"/></p>
                        </div>
                    </div>
            </c:if>


                <div class="card card-cascade card-recipe ">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong>Cocinadas recientemente</strong></h4>

                        <p class="card-text">Recetas cocinadas recientemente</p>
                    </div>
                </div>
                <!-- Post title -->

                <div class="card card-cascade card-recipe ">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong><spring:message code="myFavouriteRecipes"/></strong></h4>

                        <p class="card-text"><spring:message code="myFavouriteRecipesExplanation"/></p>
                    </div>
                </div>
                <!-- Post title -->


        </div>

    </section>
    <section class="side_card">
        <div class="card">
            <div class="card-body">
                <%@include file="userbar.jsp" %>
            </div>
        </div>
    </section>
</section>


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

</body>

</html>
