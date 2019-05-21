<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>
    <h4>Filtros de busqueda</h4>
    <br/>

    <div id="filters-card">
        <div class="navigation__list">

            <div class="navigation__list__header"
                 role="button"
                 data-toggle="collapse"
                 href="#sortBy"
                 aria-expanded="true"
                 aria-controls="sortBy">
                Ordenar por
            </div>

            <div class="collapse in" id="sortBy">

                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="defaultGroupExample1"
                           name="groupOfDefaultRadios">
                    <label class="custom-control-label" for="defaultGroupExample1">Rising</label>
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
        </div>
        <div class="navigation__list">

        <div class="navigation__list__header"
             role="button"
             data-toggle="collapse"
             href="#foodType"
             aria-expanded="true"
             aria-controls="foodType">
            <spring:message code="cuisineType"/>
        </div>

        <div class="collapse in" id="foodType">
            <c:forEach var="tag" items="${allTags}">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="${tag}">
                    <label class="custom-control-label" for="${tag}"><spring:message code="${tag}"/></label>
                </div>
            </c:forEach>

        </div>
    </div>
    </div>
</body>
</html>
