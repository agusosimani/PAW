<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div class="card-deck">
    <c:forEach var="recipe" items="${RecipeList}">

        <div class="col-lg-4 col-md-12 mb-lg-0 mb-4 columns-cards-recipe">
            <div class="card card-cascade narrower mb-4">

                <!--Card image-->
                <div class="view view-cascade">
                    <img class="card-img-top"
                         src="<c:choose>
                                    <c:when test="${recipe.encodedImage == ''}"><c:url value="/resources/img/no_recipe_image.png"/></c:when>
                                    <c:otherwise>data:image/png;base64,${recipe.encodedImage}</c:otherwise>
                                  </c:choose>" alt="<c:out value="${recipe.name}"/>"/>
                    <a>
                        <div class="mask rgba-white-slight"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-body card-body-cascade">
                    <div>
                        <c:forEach var="recipe_tag" items="${recipe.tags}">
                            <p class="pink-text"> ${recipe_tag.tag}</p>
                        </c:forEach>
                    </div>

                    <h4 class="card-title"><strong><c:out value="${recipe.name}"/></strong></h4>
                    <p class="card-text"><c:out value="${recipe.description}"/></p>

                    <c:url value="/recipe" var="recipeUrl">
                        <c:param name="recipeId" value="${recipe.id}"/>
                    </c:url>
                    <a href="${recipeUrl}" class="stretched-link"></a>
                    <fieldset id="rating-${recipe.id}" class="rating">
                        <input type="radio" id="five" name="" value="5"
                               <c:if test="${recipe.rating >= 5}">checked</c:if>/><label class="full" for="five"
                                                                                         title="Awesome - 5 stars"></label>
                        <input type="radio" id="fourhalf" name="" value="4 and a half"
                               <c:if test="${recipe.rating >= 4.5}">checked</c:if>/><label class="half" for="fourhalf"
                                                                                           title="Pretty good - 4.5 stars"></label>
                        <input type="radio" id="four" name="" value="4"
                               <c:if test="${recipe.rating >= 4}">checked</c:if>/><label class="full" for="four"
                                                                                         title="Pretty good - 4 stars"></label>
                        <input type="radio" id="threehalf" name="" value="3 and a half"
                               <c:if test="${recipe.rating >= 3.5}">checked</c:if>/><label class="half" for="threehalf"
                                                                                           title="Meh - 3.5 stars"></label>
                        <input type="radio" id="three" name="" value="3"
                               <c:if test="${recipe.rating >= 3}">checked</c:if>/><label class="full" for="three"
                                                                                         title="Meh - 3 stars"></label>
                        <input type="radio" id="twohalf" name="" value="2 and a half"
                               <c:if test="${recipe.rating >= 2.5}">checked</c:if>/><label class="half" for="twohalf"
                                                                                           title="Kinda bad - 2.5 stars"></label>
                        <input type="radio" id="two" name="" value="2"
                               <c:if test="${recipe.rating >= 2}">checked</c:if>/><label class="full" for="two"
                                                                                         title="Kinda bad - 2 stars"></label>
                        <input type="radio" id="onehalf" name="" value="1 and a half"
                               <c:if test="${recipe.rating >= 1.5}">checked</c:if>/><label class="half" for="onehalf"
                                                                                           title="Meh - 1.5 stars"></label>
                        <input type="radio" id="one" name="" value="1"
                               <c:if test="${recipe.rating >= 1}">checked</c:if>/><label class="full" for="one"
                                                                                         title="Sucks big time - 1 star"></label>
                        <input type="radio" id="half" name="" value="half"
                               <c:if test="${recipe.rating >= 0.5}">checked</c:if>/><label class="half" for="half"
                                                                                           title="Sucks big time - 0.5 stars"></label>
                    </fieldset>
                </div>
                <!--/.Card content-->

            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>
