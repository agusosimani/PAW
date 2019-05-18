<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
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

    <div class="card card-new-recipe">
        <div class="card-body">
            <h3><spring:message code="addNewRecipe"/></h3>
            <c:url value="/create_recipe" var="createRecipe"/>
            <form:form autocomplete="off" modelAttribute="recipeForm" action="${createRecipe}" method="post"
                       enctype="multipart/form-data">
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
                <form:input path="description" type="text" id="recipe_description" cssClass="form-control mb-4"
                            placeholder="${descriptionPlaceholder}"/>

                <spring:message code="instructions.title" var="instructionsTitle"/>
                <form:label path="instructions" for="recipe_description"><spring:message code="Recipe.instructions"/>
                    <a href="#" class="tooltip-test" title="${instructionsTitle}">
                        <i class="fas fa-info-circle"></i>
                    </a>
                </form:label>
                <spring:message code="instructions.placeholder" var="instructionsPlaceholder"/>
                <form:textarea cssClass="comment-textarea" path="instructions" type="text" id="recipe_description"
                               class="form-control mb-4"
                               placeholder="${instructionsPlaceholder}"/>


                <div id="clonedInput1" class="clonedInput_1 flex">
                    <div class="new-recipe-ingredient-select">
                        <form:label path="ingredients"><spring:message code="addIngredient.select"/>
                        </form:label>
                        <form:select path="ingredients" cssClass="form-control" multiple="">
                            <c:forEach var="ingredient_type" items="${allIngredients}">
                                <form:option value="${ingredient_type.id}">${ingredient_type.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="new-recipe-ingredient-amount">
                        <form:label path="ingredientsAmount"><spring:message code="addIngredient.amount"/>
                        </form:label>
                        <form:input class="form-control mb-4" path="ingredientsAmount"/>
                    </div>
                </div>

                <button type="button" id="btnAdd_1" name="btnAdd" class="btn btn-green new-recipe-ingredient-btn">Add ingredient</button>
                <button type="button" id="btnDel_1" name="btnDel" class="btn btn-danger new-recipe-ingredient-btn">Delete ingredient</button>


                <p class="new-recipe-image">
                    <form:input path="image" type="file"/>
                </p>

                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
                            aria-expanded="false" aria-controls="collapseExample">
                        <spring:message code="tags.select"/>
                    </button>
                </p>
                <div class="collapse" id="collapseExample">
                    <form:checkboxes path="tags" element="span class='custom-control custom-checkbox'" items="${allTags}"/>
                </div>
                <div class="float-right">
                    <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                    <button type="submit" class="btn btn-green"><spring:message code="saveChangesButton"/></button>
                </div>
            </form:form>
        </div>
    </div>
</section>

<!-- Modal --><%--
<div class="modal fade" id="add-new-recipe" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3><spring:message code="addNewRecipe"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:url value="/create_recipe" var="createRecipe"/>
            <form:form autocomplete="off" modelAttribute="recipeForm" action="${createRecipe}" method="post" enctype="multipart/form-data">
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

                    <form:label path="ingredientOne"><spring:message code="addIngredient.select"/>
                    </form:label>
                    <form:select path="ingredientOne" class="form-control mb-4">
                        <c:forEach var="ingredient_type" items="${allIngredients}">
                            <form:option value="${ingredient_type.id}">${ingredient_type.name}</form:option>
                        </c:forEach>
                    </form:select>

                    <form:label path="ingredientOneAmount"><spring:message code="addIngredient.amount"/>
                    </form:label>
                    <form:input class="form-control mb-4" path="ingredientOneAmount" />

                    <p>
                        <form:input path="image" type="file"/>
                    </p>

                    <p>
                        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            Button with data-target
                        </button>
                    </p>
                    <div class="collapse" id="collapseExample">
                        <form:checkboxes path="tags" items="${allTags}" />
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                    <button type="submit" class="btn btn-green"><spring:message code="saveChangesButton"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>--%>

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
