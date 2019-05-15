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

<section class="browse text-center">
    <div class="row">


        <c:forEach var="recipe" items="${RecipeList}">

            <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">
                <div class="card card-cascade narrower mb-4">

                    <!--Card image-->
                    <div class="view view-cascade">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <!--/.Card image-->


                    <!--Card content-->
                    <div class="card-body card-body-cascade">
                        <h5 class="pink-text"><i class="fas fa-utensils"></i> CategoriaX</h5>
                        <h4 class="card-title"><strong>${recipe.name}</strong></h4>
                        <p class="card-text">${recipe.description}</p>

                        <c:url value="/recipe" var="recipeUrl">
                            <c:param name="recipeId" value="${recipe.id}"/>
                        </c:url>
                        <a href="${recipeUrl}" class="stretched-link"></a>
                        <fieldset class="rating">
                            <input type="radio" id="${recipe.id}five" name="${recipe.id}" value="5" checked /><label class = "full" for="${recipe.id}five" title="Awesome - 5 stars"></label>
                            <input type="radio" id="${recipe.id}fourhalf" name="${recipe.id}" value="4 and a half" /><label class="half" for="${recipe.id}fourhalf" title="Pretty good - 4.5 stars"></label>
                            <input type="radio" id="${recipe.id}four" name="${recipe.id}" value="4" /><label class = "full" for="${recipe.id}four" title="Pretty good - 4 stars"></label>
                            <input type="radio" id="${recipe.id}threehalf" name="${recipe.id}" value="3 and a half" /><label class="half" for="${recipe.id}threehalf" title="Meh - 3.5 stars"></label>
                            <input type="radio" id="${recipe.id}three" name="${recipe.id}" value="3" /><label class = "full" for="${recipe.id}three" title="Meh - 3 stars"></label>
                            <input type="radio" id="${recipe.id}twohalf" name="${recipe.id}" value="2 and a half" /><label class="half" for="${recipe.id}twohalf" title="Kinda bad - 2.5 stars"></label>
                            <input type="radio" id="${recipe.id}two" name="${recipe.id}" value="2" /><label class = "full" for="${recipe.id}two" title="Kinda bad - 2 stars"></label>
                            <input type="radio" id="${recipe.id}onehalf" name="${recipe.id}" value="1 and a half" /><label class="half" for="${recipe.id}onehalf" title="Meh - 1.5 stars"></label>
                            <input type="radio" id="${recipe.id}one" name="${recipe.id}" value="1" /><label class = "full" for="id=${recipe.id}one" title="Sucks big time - 1 star"></label>
                            <input type="radio" id="${recipe.id}half" name="${recipe.id}" value="half" /><label class="half" for="${recipe.id}half" title="Sucks big time - 0.5 stars"></label>
                        </fieldset>
                    </div>
                    <!--/.Card content-->

                </div>
            </div>
        </c:forEach>
    </div>


</section>


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


</body>
</html>
