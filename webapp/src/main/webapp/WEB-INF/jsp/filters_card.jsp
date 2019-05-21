<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>

</head>
<body>

<div id="filters-card">
    <h4><spring:message code="searchFilters"/></h4>

    <label class="text-filter"><spring:message code="sortBy"/> </label>
    <c:url value="/filter" var="createRecipe"/>
    <form:form autocomplete="off" modelAttribute="filterForm" action="${createRecipe}" method="post"
               enctype="multipart/form-data">
        <div class="custom-control custom-radio">
            <form:radiobutton path="input1" value="New" class="custom-control-input" id="New" name="groupOrderFilter"/>
            <form:label class="custom-control-label" path="input1" for="New">New</form:label>
        </div>
        <div class="custom-control custom-radio">
            <form:radiobutton path="input1" value="Rising" class="custom-control-input" id="Rising"
                              name="groupOrderFilter"/>
            <form:label class="custom-control-label" path="input1" for="Rising">Rising</form:label>
        </div>
        <div class="custom-control custom-radio">
            <form:radiobutton path="input1" value="Top" class="custom-control-input" id="Top" name="groupOrderFilter"/>
            <form:label class="custom-control-label" path="input1" for="Top">Top</form:label>
        </div>


        <div>
            <label class="text-filter"><spring:message code="cuisineType"/></label>
            <c:forEach var="tag" items="${allTags}">
                <div class="custom-control custom-checkbox">
                    <form:checkbox path="tags" value="${tag}" class="custom-control-input" id="${tag}"
                                      name="groupTagFilter"/>
                    <form:label class="custom-control-label" path="tags" for="${tag}"><spring:message code="${tag}"/></form:label>
                </div>
            </c:forEach>

        </div>

        <button class="btn btn-green btn-apply-filters" type="submit"><spring:message code="confirm"/></button>
    </form:form>
</div>
</body>
</html>
