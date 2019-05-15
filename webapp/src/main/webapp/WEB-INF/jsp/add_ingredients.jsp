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
            <c:url value="/add_ingredient_user" var="addIngredient"/>
            <form:form autocomplete="off" modelAttribute="addIngredientForm" action="${addIngredient}" method="post">
                <div class="modal-body">

                        <form:label path="ingredient"><spring:message code="addIngredient.select"/>
                        </form:label>
                        <form:select path="ingredient" class="form-control mb-4">
                            <c:forEach var="ingredient_type" items="${allIngredients}">
                                <form:option value="${ingredient_type}">${ingredient_type.name}</form:option>
                            </c:forEach>
                        </form:select>

                        <form:label path="amount"><spring:message code="addIngredient.amount"/>
                        </form:label>
                        <form:input class="form-control mb-4" path="amount" />
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
