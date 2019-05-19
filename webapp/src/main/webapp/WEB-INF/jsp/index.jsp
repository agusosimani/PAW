<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
</head>

<body>
<%@include file="sidebar.jsp" %>

<section class="main_container">

    <section class="browse text-center">
        <%@include file="recipe_cards.jsp" %>
    </section>

    <section class="side_card">
        <div class="card">
            <div class="card-body">

                <h4>Filtros de busqueda</h4>
                <br/>

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
        </div>
    </section>
</section>

<!-- Button trigger modal -->
<c:url value="/new_recipe" var="newRecipeUrl"/>
<a href="${newRecipeUrl}">
    <button class="btn btn-green add">+</button>
</a>

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>

<%--TODO no deberia estar aca
<script>
    autocomplete(document.getElementById("Item"), ingredients);
    autocomplete(document.getElementById("cuisine"), cuisine);
    autocomplete(document.getElementById("special_consideration"), special_considerations);
</script>
--%>
</body>

</html>
