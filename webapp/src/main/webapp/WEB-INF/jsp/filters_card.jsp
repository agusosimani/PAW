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
    <c:url value="/" var="filter"/>
    <form:form autocomplete="off" modelAttribute="filterForm" action="${filter}" method="get"
               enctype="multipart/form-data">
        <c:forEach var="order" items="${allOrders}">
            <div class="custom-control custom-radio">
                <form:radiobutton path="order" value="${order}" class="custom-control-input" id="${order}" name="groupOrderFilter"/>
                <form:label class="custom-control-label" path="order" for="${order}">${order}</form:label>
            </div>
        </c:forEach>

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
