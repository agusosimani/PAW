<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="modal fade" id="add-recipe-cooklist" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3><spring:message code="cooklist.addRecipeTitle"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <c:url var="addRecipeToCookListUrl" value="/add_recipe_to_cooklist">
                    <c:param name="userId" value="${user.id}"/>
                    <c:param name="recipeId" value="${recipe.id}"/>
                </c:url>
                <form:form modelAttribute="selectCookListForm" action="${addRecipeToCookListUrl}" method="post">

                <form:label path="cooklistId"><spring:message code="cooklist.select"/></form:label>
                <form:select path="cooklistId" class="ingredientSelect form-control mb-4">
                    <c:forEach var="cooklist" items="${cookLists}">
                        <form:option value="${cooklist.id}"><c:out value="${cooklist.name}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="cooklistId" cssClass="form-text text-muted" element="small"/>


            </div>

            <div class="modal-footer">
                <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                <button type="submit" class="btn btn-green"><spring:message code="confirm"/></button>
            </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
