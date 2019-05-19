<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>">
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
                    <h4 class="recipe-title"><c:out value="${recipe.name}"/></h4>
                    <p class="recipe-description"><c:out value="${recipe.description}"/></p>

                    <br/>
                    <p class="recipe-instructions"><c:out value="${recipe.instructions}"/></p>
                </div>

                <div class="recipe-image-container">
                    <div class="recipe-image-container-sub">
                        <img class="recipe-image"
                             src="<c:choose>
                                <c:when test="${recipe.encodedImage == ''}"><c:url value="/resources/img/no_recipe_image.png"/></c:when>
                                <c:otherwise>data:image/png;base64,${recipe.encodedImage}</c:otherwise>
                              </c:choose>" alt="<c:out value="${recipe.name}"/>"/>
                        <div class="ingredients-tags-div">
                            <br/>
                            <c:url var="rateUrl" value="/rate_recipe"/>

                            <p class="ingredients-title"><spring:message code="rating.general"/></p>
                            <fieldset class="rating rating-recipe disabled">
                                <input type="radio" id="fiveGeneral" name="" value="5"
                                       <c:if test="${recipe.rating == 5}">checked</c:if>/><label class="full"
                                                                                                 for="fiveGeneral"
                                                                                                 title="Awesome - 5 stars"></label>
                                <input type="radio" id="fourhalfGeneral" value="4 and a half"
                                       <c:if test="${recipe.rating >= 4.5}">checked</c:if>/><label class="half"
                                                                                                   for="fourhalfGeneral"
                                                                                                   title="Pretty good - 4.5 stars"></label>
                                <input type="radio" id="fourGeneral" name="" value="4"
                                       <c:if test="${recipe.rating >= 4}">checked</c:if>/><label class="full"
                                                                                                 for="fourGeneral"
                                                                                                 title="Pretty good - 4 stars"></label>
                                <input type="radio" id="threehalfGeneral" name="" value="3 and a half"
                                       <c:if test="${recipe.rating >= 3.5}">checked</c:if>/><label class="half"
                                                                                                   for="threehalfGeneral"
                                                                                                   title="Meh - 3.5 stars"></label>
                                <input type="radio" id="threeGeneral" name="" value="3"
                                       <c:if test="${recipe.rating >= 3}">checked</c:if>/><label class="full"
                                                                                                 for="threeGeneral"
                                                                                                 title="Meh - 3 stars"></label>
                                <input type="radio" id="twohalfGeneral" name="" value="2 and a half"
                                       <c:if test="${recipe.rating >= 2.5}">checked</c:if>/><label class="half"
                                                                                                   for="twohalfGeneral"
                                                                                                   title="Kinda bad - 2.5 stars"></label>
                                <input type="radio" id="twoGeneral" name="" value="2"
                                       <c:if test="${recipe.rating >= 2}">checked</c:if>/><label class="full" for="two"
                                                                                                 title="Kinda bad - 2 stars"></label>
                                <input type="radio" id="onehalfGeneral" name="" value="1 and a half"
                                       <c:if test="${recipe.rating >= 1.5}">checked</c:if>/><label class="half"
                                                                                                   for="onehalfGeneral"
                                                                                                   title="Meh - 1.5 stars"></label>
                                <input type="radio" id="oneGeneral" name="" value="1"
                                       <c:if test="${recipe.rating >= 1}">checked</c:if>/><label class="full"
                                                                                                 for="oneGeneral"
                                                                                                 title="Sucks big time - 1 star"></label>
                                <input type="radio" id="halfGeneral" name="" value="half"
                                       <c:if test="${recipe.rating >= 0.5}">checked</c:if>/><label class="half"
                                                                                                   for="halfGeneral"
                                                                                                   title="Sucks big time - 0.5 stars"></label>
                            </fieldset>
                            <br/>
                            <p class="ingredients-title"><spring:message code="rating.user"/></p>
                            <fieldset class="rating rating-recipe">
                                <input onclick="add_rate(5)" type="radio" id="five" name="" value="5"
                                       <c:if test="${previous_rate == 5}">checked</c:if>/><label class="full" for="five"
                                                                                                 title="Awesome - 5 stars"></label>
                                <input onclick="add_rate(4.5)" type="radio" id="fourhalf" value="4 and a half"
                                       <c:if test="${previous_rate >= 4.5}">checked</c:if>/><label class="half"
                                                                                                   for="fourhalf"
                                                                                                   title="Pretty good - 4.5 stars"></label>
                                <input onclick="add_rate(4)" type="radio" id="four" name="" value="4"
                                       <c:if test="${previous_rate >= 4}">checked</c:if>/><label class="full" for="four"
                                                                                                 title="Pretty good - 4 stars"></label>
                                <input onclick="add_rate(3.5)" type="radio" id="threehalf" name="" value="3 and a half"
                                       <c:if test="${previous_rate >= 3.5}">checked</c:if>/><label class="half"
                                                                                                   for="threehalf"
                                                                                                   title="Meh - 3.5 stars"></label>
                                <input onclick="add_rate(3)" type="radio" id="three" name="" value="3"
                                       <c:if test="${previous_rate >= 3}">checked</c:if>/><label class="full"
                                                                                                 for="three"
                                                                                                 title="Meh - 3 stars"></label>
                                <input onclick="add_rate(2.5)" type="radio" id="twohalf" name="" value="2 and a half"
                                       <c:if test="${previous_rate >= 2.5}">checked</c:if>/><label class="half"
                                                                                                   for="twohalf"
                                                                                                   title="Kinda bad - 2.5 stars"></label>
                                <input onclick="add_rate(2)" type="radio" id="two" name="" value="2"
                                       <c:if test="${previous_rate >= 2}">checked</c:if>/><label class="full" for="two"
                                                                                                 title="Kinda bad - 2 stars"></label>
                                <input onclick="add_rate(1.5)" type="radio" id="onehalf" name="" value="1 and a half"
                                       <c:if test="${previous_rate >= 1.5}">checked</c:if>/><label class="half"
                                                                                                   for="onehalf"
                                                                                                   title="Meh - 1.5 stars"></label>
                                <input onclick="add_rate(1)" type="radio" id="one" name="" value="1"
                                       <c:if test="${previous_rate >= 1}">checked</c:if>/><label class="full" for="one"
                                                                                                 title="Sucks big time - 1 star"></label>
                                <input onclick="add_rate(0.5)" type="radio" id="half" name="" value="half"
                                       <c:if test="${previous_rate >= 0.5}">checked</c:if>/><label class="half"
                                                                                                   for="half"
                                                                                                   title="Sucks big time - 0.5 stars"></label>
                            </fieldset>

                            <br/>
                            <p class="ingredients-title"><spring:message code="ingredients"/></p>
                            <c:forEach var="ingredient" items="${recipe.ingredients}">
                                <div>
                                    <p class="ingredients-item"><c:out value="${ingredient.ingredient.name}"/></p>
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
            </div>
        </div>

        <div class="card card-comments">
            <div class="card-body">
                <c:url var="addCommentUrl" value="/add_comment">
                    <c:param name="recipeId" value="${recipe.id}"/>
                </c:url>
                <form:form modelAttribute="commentForm" action="${addCommentUrl}" method="post">
                    <form:label cssClass="comment-add-label" path="comment"><spring:message
                            code="comment.Add"/></form:label>
                    <form:textarea cssClass="comment-textarea" path="comment" type="text" maxlength="500"/>
                    <form:errors path="comment" cssClass="formError" element="p"/>
                    <button type="submit" class="btn btn-green"><spring:message code="comment.Send"/></button>
                </form:form>

                <c:forEach var="comment" items="${recipe.comments}">
                    <div class="card-body-comment">
                        <div class="card-body-inside">
                            <c:url value="/user_recipes" var="userRecipesUrl">
                                <c:param name="userId" value="${comment.userId}"/>
                            </c:url>
                            <a class="bg-transparent" href="${userRecipesUrl}">
                                <img class="user-image-commentary" src="<c:url value="/resources/img/user.png"/>">
                            </a>
                            <div class="user-date-comment">
                                <a class="bg-transparent" href="${userRecipesUrl}">
                                    <h5 class="user-title-commentary">${comment.username}</h5>
                                </a>
                                <p class="card-comments-date"> ${comment.date}</p>
                            </div>
                            <div class="float-right">
                                <p class="comment-vote">23</p>
                                <i class="fas fa-arrow-up arrow-up-enabled"></i>
                                <i class="fas fa-arrow-down arrow-down-enabled"></i>
                            </div>
                        </div>
                        <div class="card-comments-text">
                            <p class="card-text">${comment.message}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <%@include file="userbar.jsp" %>

</section>

<c:url value="/cook_recipe" var="cookRecipe">
    <c:param name="recipeId" value="${recipe.id}"/>
</c:url>
<form:form action="${cookRecipe}" method="post">
    <button type="submit" title="<spring:message code="cookButton"/>" class="btn btn-green btn-cook"><i
            class="fas fa-utensils fa-2x"></i></button>
</form:form>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/utils.js"/>"></script>

</body>
</html>
