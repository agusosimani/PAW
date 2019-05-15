<%--
  Created by IntelliJ IDEA.
  User: agusosimani
  Date: 29/04/19
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<!-- Modal -->
<div class="modal fade" id="add-new-recipe" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Add New Recipe</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:url value="/create_recipe" var="createRecipe"/>
            <form:form autocomplete="off" modelAttribute="recipeForm" action="${createRecipe}" method="post">
                <div class="modal-body">
                    <spring:message code="recipeName.title" var="recipeNameTitle"/>
                    <form:label path="name" for="recipe_title"><spring:message code="Recipe.name"/>
                        <a href="#" class="tooltip-test"
                           title="${recipeNameTitle}">
                            <i class="fas fa-info-circle"></i>
                        </a>
                    </form:label>
                    <spring:message code="recipeName.placeholder" var="recipeNamePlaceholder"/>
                    <form:input path="name" type="text" id="recipe_title" class="form-control mb-4"
                           placeholder="${recipeNamePlaceholder}"/>
                    <spring:message code="description.title" var="descriptionTitle"/>
                    <form:label path="description" for="recipe_description"><spring:message code="Recipe.description"/>
                        <a href="#" class="tooltip-test" title="${descriptionTitle}">
                            <i class="fas fa-info-circle"></i>
                        </a>
                    </form:label>
                    <spring:message code="description.placeholder" var="descriptionPlaceholder"/>
                    <form:input path="description" type="text" id="recipe_description" class="form-control mb-4"
                                placeholder="${descriptionPlaceholder}"/>

                    <spring:message code="instructions.title" var="instructionsTitle"/>
                    <form:label path="instructions" for="recipe_description"><spring:message code="Recipe.instructions"/>
                        <a href="#" class="tooltip-test" title="${instructionsTitle}">
                            <i class="fas fa-info-circle"></i>
                        </a>
                    </form:label>
                    <spring:message code="instructions.placeholder" var="instructionsPlaceholder"/>
                    <form:input path="instructions" type="text" id="recipe_description" class="form-control mb-4"
                                placeholder="${instructionsPlaceholder}"/>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                    <button type="submit" class="btn btn-green"><spring:message code="saveChangesButton"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
