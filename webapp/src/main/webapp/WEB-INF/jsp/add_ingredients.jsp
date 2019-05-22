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

            <div class="modal-body">
                <c:url value="/add_ingredient_user" var="addIngredient"/>
                <form:form autocomplete="off" modelAttribute="addIngredientForm" action="${addIngredient}"
                           method="post">


                <form:errors path="ingredients" cssClass="form-text text-muted" element="small"/>
                <div id="cloneInput1" class="cloneInput_1 flex">
                    <div class="new-recipe-ingredient-select">
                        <form:label class="ingredientLabel" path="ingredients[0].ingredient.id"><spring:message code="addIngredient.select"/>
                        </form:label>
                        <form:select path="ingredients[0].ingredient.id" class="ingredientSelect form-control mb-4">
                            <c:forEach var="ingredient_type" items="${allIngredients}">
                                <form:option value="${ingredient_type.id}"><spring:message code="${ingredient_type.name}"/> (${ingredient_type.typeOfServing})</form:option>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="new-recipe-ingredient-amount">
                        <form:label class="ingredientAmountLabel" path="ingredients[0].amount"><spring:message code="addIngredient.amount"/>
                        </form:label>
                        <form:input type="number" cssClass="ingredientAmountInput form-control mb-4" path="ingredients[0].amount" value="0"/>
                    </div>

                    <button type="button" id="0" onclick="delete_ingredient2(this)" name="btnDel" class="bg-transparent new-recipe-ingredient-btn delete-ingredient-button">
                        <i class="fas fa-trash fa-2x red-ic"></i>
                    </button>
                </div>

                <button type="button" id="btnAdd_2" name="btnAdd" class="btn btn-green new-recipe-ingredient-btn">Add
                    ingredienttt
                </button>
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
