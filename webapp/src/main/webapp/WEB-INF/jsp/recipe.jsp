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
    <section class="browse text-center">
        <!-- Card -->
        <div class="card">

            <div class="flex">
                <div class="recipe-body">
                    <h4 class="recipe-title">${recipe.name}</h4>
                    <p class="recipe-description">${recipe.description}</p>

                    <br/>
                    <p class="recipe-instructions">${recipe.instructions}</p>
                </div>
                <div class="recipe-image-container">
                    <img class="recipe-image" src="data:image/png;base64,${recipe.encodedImage}" alt="${recipe.name}"/>
                    <div class="ingredients-tags-div">
                        <br/>
                        <c:url var="rateUrl" value="/rate_recipe"/>

                                <fieldset class="rating rating-recipe">
                                    <input onclick="add_rate(5)" type="radio" id="five" name="" value="5" <c:if test="${previous_rate == 5}">checked</c:if>/><label class = "full" for="five" title="Awesome - 5 stars"></label>
                                    <input onclick="add_rate(4.5)" type="radio" id="fourhalf" value="4 and a half" <c:if test="${previous_rate >= 4.5}">checked</c:if>/><label class="half" for="fourhalf" title="Pretty good - 4.5 stars"></label>
                                    <input onclick="add_rate(4)" type="radio" id="four" name="" value="4" <c:if test="${previous_rate >= 4}">checked</c:if>/><label class = "full" for="four" title="Pretty good - 4 stars"></label>
                                    <input onclick="add_rate(3.5)" type="radio" id="threehalf" name="" value="3 and a half" <c:if test="${previous_rate >= 3.5}">checked</c:if>/><label class="half" for="threehalf" title="Meh - 3.5 stars"></label>
                                    <input onclick="add_rate(3)" type="radio" id="three" name="" value="3" <c:if test="${previous_rate >= 3}">checked</c:if>/><label class = "full" for="three" title="Meh - 3 stars"></label>
                                    <input onclick="add_rate(2.5)" type="radio" id="twohalf" name="" value="2 and a half" <c:if test="${previous_rate >= 2.5}">checked</c:if>/><label class="half" for="twohalf" title="Kinda bad - 2.5 stars"></label>
                                    <input onclick="add_rate(2)" type="radio" id="two" name="" value="2" <c:if test="${previous_rate >= 2}">checked</c:if>/><label class = "full" for="two" title="Kinda bad - 2 stars"></label>
                                    <input onclick="add_rate(1.5)" type="radio" id="onehalf" name="" value="1 and a half" <c:if test="${previous_rate >= 1.5}">checked</c:if>/><label class="half" for="onehalf" title="Meh - 1.5 stars"></label>
                                    <input onclick="add_rate(1)" type="radio" id="one" name="" value="1" <c:if test="${previous_rate >= 1}">checked</c:if>/><label class = "full" for="one" title="Sucks big time - 1 star"></label>
                                    <input onclick="add_rate(0.5)" type="radio" id="half" name="" value="half" <c:if test="${previous_rate >= 0.5}">checked</c:if>/><label class="half" for="half" title="Sucks big time - 0.5 stars"></label>
                                </fieldset>


                        <br/>
                        <p class="ingredients-title"><spring:message code="ingredients"/></p>
                        <c:forEach var="ingredient" items="${recipe.ingredients}">
                            <div>
                                <p class="ingredients-item">${ingredient.ingredient.name}</p>
                                <p class="ingredients-amount">${ingredient.amount}</p>
                            </div>

                        </c:forEach>
                        <br/>

                        <c:if test="${!empty recipe.tags}">
                            <p class="ingredients-title"><spring:message code="categories"/></p>

                            <div>
                                <c:forEach var="tag" items="${recipe.tags}">
                                    <p class="categories-names">${tag.tag}</p>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>


            <c:url value="/cook_recipe" var="cookRecipe">
                <c:param name="recipeId" value="${recipe.id}"/>
            </c:url>
            <form:form action="${cookRecipe}" method="post">
                <button type="submit" class="btn btn-unique waves-effect waves-light"><spring:message code="cookButton"/></button>
            </form:form>


        </div>
        <!-- Card -->
    </section>

    <%@include file="userbar.jsp" %>

</section>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>

<script type="text/javascript"  src="<c:url value="/resources/js/utils.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>
</body>
</html>
