<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<!-- Modal -->
<div class="modal fade" id="add-ingredient" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Add ingredients</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:url value="/create_recipe" var="createRecipe"/>
            <form:form autocomplete="off" modelAttribute="addIngredientForm" action="${createRecipe}">
                <div class="modal-body">
                    <div>
                        <spring:message code="addIngredient.name" var="addIngredient.name"/>
                        <form:label path="name" for="recipe_title"><spring:message code="Recipe.name"/>
                        </form:label>
                        <form:select path="ingredientId" class="browser-default custom-select">
                            <c:forEach var="ingredient_type" items="${allIngredients}">
                                <form:option value="${allIngredients.id}">${allIngredients.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div>
                        <spring:message code="addIngredient.amount" var="recipeNameTitle"/>
                        <form:input path="amount" />
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                    <button type="submit" class="btn btn-green"><spring:message code="addIngredient"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
