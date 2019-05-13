<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
</head>

<body><!--class="row"-->
<%@include file="sidebar.jsp" %>
<!-- Section: Blog v.2 -->
<section class="main_container">
    <section class="browse text-center">
        <!-- Grid row -->
        <div class="row">

            <!-- Grid column -->


            <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">

                <c:url value="/user_recipes" var="userRecipesUrl">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <!-- Featured image -->
                <div class="card" >

                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
                    </div>


                    <div class="card-body">
                        <h4 class="card-title"><strong>Mis recetas</strong></h4>
                        <p class="card-text">Vea las recetas creadas por usted mismo</p>
                        <a href="${userRecipesUrl}" class="stretched-link"></a>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 mb-md-0 mb-4">

                <div class="card">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong>Mis listas</strong></h4>
                        <p class="card-text">Aqui se encuentran las listas creadas con sus recetas y recetas de
                            terceros</p>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 mb-0">

                <div class="card">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong>Mis ingredientes</strong></h4>
                        <p class="card-text">Visualize y modifique sus ingredientes</p>
                    </div>
                </div>

                <!-- Post title -->

            </div>

        </div>
        <!-- Grid row -->
        <div class="row">
            <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">

                <div class="card">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong>Cocinadas recientemente</strong></h4>

                        <p class="card-text">Recetas cocinadas recientemente</p>
                    </div>
                </div>
                <!-- Post title -->

            </div>

            <div class="col-lg-4 col-md-6 mb-md-0 mb-4">

                <div class="card">
                    <div class="view overlay rounded z-depth-2 mb-4">
                        <img class="card-img-top" src="<c:url value="/resources/img/recipe_2.png"/>" alt="Sample image">
                        <a>
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><strong>Favoritas</strong></h4>

                        <p class="card-text">Aqui se muestran las recetas que califico con una calificacion mayor o
                            igual a 4 estrellas</p>
                    </div>
                </div>
                <!-- Post title -->

            </div>

        </div>
        <!-- Grid row -->

    </section>

    <%@include file="userbar.jsp" %>
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
