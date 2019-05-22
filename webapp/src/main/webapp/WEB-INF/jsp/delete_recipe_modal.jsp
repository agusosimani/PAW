<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="modal fade" id="delete-recipe" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <h3><spring:message code="recipe.deleteWarning"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <c:url value="/delete_recipe" var="deleteRecipeUrl">
                    <c:param name="recipeId" value="${recipe.id}"/>
                </c:url>
                <form:form action="${deleteRecipeUrl}" method="post">
                    <button type="submit" class="btn btn-red float-right"><spring:message code="confirm"/></button>
                    <a class="btn btn-blue-grey float-right" data-dismiss="modal"><spring:message code="close"/></a>
                </form:form>


            </div>
        </div>
    </div>
</div>
</body>
</html>
