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
                 <card/>
            </section>

            <section class="side_card">
                <div class="card">
                    <div class="card-body">
                        <img class="user_image" src="<c:url value="/resources/img/user.png"/>">
                        <h5 class="user_title">${user}</h5>
                        <br/><br/></br></br>
                        <p class="card-text">1/5 estrellas en sus recetas</p>
                        <p class="card-text">232 recetas</p>
                        <p class="card-text">100 comentarios</p>
                    </div>
                </div>
            </section>
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
