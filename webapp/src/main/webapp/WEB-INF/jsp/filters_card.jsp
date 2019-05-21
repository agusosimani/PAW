<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>

</head>
<body>

<div id="filters-card">
    <h4><spring:message code="searchFilters"/></h4>

    <div>
        <label class="text-filter"><spring:message code="sortBy"/> </label>
        <div class="custom-control custom-radio">
        </div>

        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="defaultGroupExample2"
                   name="groupOfDefaultRadios" checked>
            <label class="custom-control-label" for="defaultGroupExample2">New</label>
        </div>

        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="defaultGroupExample3"
                   name="groupOfDefaultRadios">
            <label class="custom-control-label" for="defaultGroupExample3">Top</label>
        </div>

        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="defaultGroupExample4"
                   name="groupOfDefaultRadios">
            <label class="custom-control-label" for="defaultGroupExample4">Hot</label>
        </div>

    </div>

    <div>
        <label class="text-filter"><spring:message code="cuisineType"/></label>
        <c:forEach var="tag" items="${allTags}">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="${tag}">
                <label class="custom-control-label" for="${tag}"><spring:message code="${tag}"/></label>
            </div>
        </c:forEach>

    </div>

        <button class="btn btn-green btn-apply-filters" type="submit"><spring:message code="confirm"/></button>

</div>
</body>
</html>
