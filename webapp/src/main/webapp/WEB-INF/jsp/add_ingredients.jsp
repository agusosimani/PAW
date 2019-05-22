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
                <h3><spring:message code="addIngredient.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <c:url value="/add_ingredient_user" var="addIngredient"/>
                <form:form autocomplete="off" modelAttribute="addIngredientForm" action="${addIngredient}"
                           method="post">
                <div id="cloneInput1" class="to_clone cloneInput_1 flex">
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
                        <form:input class="ingredientAmountInput form-control mb-4" path="ingredients[0].amount"/>
                    </div>

                    <div class="new-recipe-ingredient-amount">
                        <label class="ingredientAmountLabel"><spring:message code="addIngredient.type"/></label>
                        <label class="ingredientTypeAmountInput form-control mb-4">Grams</label>
                    </div>

                    <button type="button" onclick="delete_item_add_ingredient(this)" class="btnDel_2 btn btn-danger text-center">X</button>
                </div>

                <button type="button" id="btnAdd_2" name="btnAdd" class="btn btn-green new-recipe-ingredient-btn"><spring:message code="addIngredient.add"/></button>
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
