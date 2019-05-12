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
                  <a class="btn btn-unique waves-effect waves-light">Read more</a>
                </div>
                <!--/.Card content-->

              </div>
            </div>
          </c:forEach>
        </div>


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
                   aria-controls="yourMusic">
                Ordenar por
              </div>

              <div class="collapse in" id="sortBy">

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios">
                  <label class="custom-control-label" for="defaultGroupExample1">Rising</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" checked>
                  <label class="custom-control-label" for="defaultGroupExample2">New</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios">
                  <label class="custom-control-label" for="defaultGroupExample3">Top</label>
                </div>

                <div class="custom-control custom-radio">
                  <input type="radio" class="custom-control-input" id="defaultGroupExample4" name="groupOfDefaultRadios">
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
                   aria-controls="yourMusic">
                Tipo de cocina
              </div>

              <div class="collapse in" id="foodType">

                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked">
                  <label class="custom-control-label" for="defaultUnchecked">Italiana</label>
                </div>
                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked2">
                  <label class="custom-control-label" for="defaultUnchecked2">China</label>
                </div>
                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked3">
                  <label class="custom-control-label" for="defaultUnchecked3">Española</label>
                </div>

                <div class="custom-control custom-checkbox">
                  <input type="checkbox" class="custom-control-input" id="defaultUnchecked4">
                  <label class="custom-control-label" for="defaultUnchecked4">Vegana</label>
                </div>

              </div>
            </div>

          </div>
        </div>
      </section>
    </section>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-green add" data-toggle="modal" id="add_recipe" data-target="#add-new-recipe">+</button>
    <%@include file="new_recipe.jsp" %>

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
