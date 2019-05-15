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
    <section class="browse text-center">
        <!-- Card -->
        <div class="card">

            <div class="flex">
                <div class="recipe-body">
                    <h4 class="card-title">${recipe.name}</h4>
                    <p class="card-text">${recipe.description}</p>
                    <br/>
                    <%--
                    <c:forEach var="ingredient" items="${recipe.ingredients}">
                        <div>
                            <p class="float-left">${ingredient.ingredient.name}</p>
                            <p class="card-text">${ingredient.amount}</p>
                        </div>
                    </c:forEach>
                    --%>
                    <br/>
                    <p class="card-text">${recipe.instructions}</p>
                </div>
                <div class="recipe-image-container">
                    <img class="recipe-image" src="data:image/png;base64,${recipe.encodedImage}" alt="${recipe.name}"/>
                </div>
            </div>
            <c:url value="/cook_recipe" var="cookRecipe">
                <c:param name="recipeId" value="${recipe.id}"/>
            </c:url>
            <form:form action="${cookRecipe}" method="post">
                <button type="submit" class="btn btn-unique waves-effect waves-light">Cook!</button>
            </form:form>
        </div>
        <!-- Card -->
    </section>

    <%@include file="userbar.jsp" %>

</section>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>
</body>
</html>
