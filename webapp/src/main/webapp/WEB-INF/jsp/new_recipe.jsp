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
            <form:form autocomplete="off" modelAttribute="recipeForm" action="${createRecipe}">
                <div class="modal-body">

                    <form:label path="name" for="recipe_title">Recipe Title
                        <a href="#" class="tooltip-test"
                           title="Be descriptive — but don't get crazy. Succinct titles with well-chosen adjectives and key ingredients are memorable and they catch our attention, like 'One-Pot Kale and Quinoa Pilaf', 'Aunt Mariah's Lemon Sponge Cups' or 'Tipsy Maple Corn'. (You want to go cook all three, don't you?)">
                            <i class="fas fa-info-circle"></i>
                        </a>
                    </form:label>
                    <form:input path="name" type="text" id="recipe_title" class="form-control mb-4"
                           placeholder="What's the title of your recipe?"/>

                    <form:label path="description" for="recipe_description">Recipe Description
                        <a href="#" class="tooltip-test" title="This will be your recipe's introduction! We love a good story behind a dish, along with helpful tips and variations.
    If you've adapted from someone else's recipe, this is where you should give credit and tell us how you've made it your own. Not sure if your recipe is adapted enough?">
                            <i class="fas fa-info-circle"></i>
                        </a>
                    </form:label>
                    <form:input path="description" type="text" id="recipe_description" class="form-control mb-4"
                           placeholder="Tell us about your recipe."/>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-blue-grey" data-dismiss="modal">Close</a>
                    <button type="submit" class="btn btn-green">Save changes</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
