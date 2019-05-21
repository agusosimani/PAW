<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Foodify</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/foodify_tab.png"/>"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
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

    <h4 class="navigation-title">${cooklist.name}
        <button data-toggle="modal" id="delete-cooklist-modal"
                data-target="#delete-cooklist" class="bg-transparent">
        <i class="fas fa-trash fa-2x red-ic"></i>
    </button></h4>

    <section class="browse">
        <c:if test="${empty cooklist.list}">
            <c:choose>
                <c:when test="${yourCooklist}">
                    <h3 class="navigation-subtitle"><spring:message code="emptyMyCooklist"/></h3>
                    <c:url value="/" var="index"/>
                    <a href="${index}">
                        <button class="btn btn-green navigation-help-button"><spring:message
                                code="goToSeeRecipes"/></button>
                    </a>
                </c:when>
                <c:otherwise>
                    <h3 class="navigation-subtitle"><spring:message code="emptyCooklist"/></h3>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:set var="RecipeList" value="${cooklist.list}"/>
        <%@include file="recipe_cards.jsp" %>
    </section>
    <section class="side_card">
        <div class="card">
            <div class="card-body">
                <%@include file="userbar.jsp" %>
            </div>
        </div>
    </section>
</section>


<%@include file="delete_cooklist_modal.jsp" %>
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

<%--TODO no deberia estar aca    --%>
<script>
    autocomplete(document.getElementById("Item"), ingredients);
    autocomplete(document.getElementById("cuisine"), cuisine);
    autocomplete(document.getElementById("special_consideration"), special_considerations);
</script>

</body>

</html>
