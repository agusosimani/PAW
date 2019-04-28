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

            <div class="card">
              <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
              <a>
                <div class="mask rgba-white-slight"></div>
              </a>

              <div class="card-body">
            <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit
                  quo minus id quod maxime placeat facere possimus voluptas.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>

            </div>
          </div>

          <!-- Grid column -->
          <div class="col-lg-4 col-md-6 mb-md-0 mb-4">

            <div class="card">
              <img class="card-img-top" src="<c:url value="/resources/img/recipe_1.png"/>" alt="Sample image">
              <a>
                <div class="mask rgba-white-slight"></div>
              </a>

              <div class="card-body">
                <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit
                  quo minus id quod maxime placeat facere possimus voluptas.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>

            </div>
          </div>
          <!-- Grid column -->

          <!-- Grid column -->
          <div class="col-lg-4 col-md-6 mb-0">

            <div class="card">
              <!-- Featured image -->
              <div class="view overlay rounded z-depth-2 mb-4">
                <img class="card-img-top" src="<c:url value="/resources/img/recipe_3.png"/>" alt="Sample image">
                <a>
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <div class="card-body">
                <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed
                  quia consequuntur magni dolores eos qui ratione.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>
            </div>

          </div>
          <!-- Grid column -->

        </div>

        <!-- Grid row -->
        <div class="row">

          <!-- Grid column -->
          <div class="col-lg-4 col-md-12 mb-lg-0 mb-4">

            <!-- Featured image -->
            <div class="card">
              <div class="view overlay rounded z-depth-2 mb-4">
                <img class="card-img-top" src="<c:url value="/resources/img/recipe_4.png"/>" alt="Sample image">
                <a>
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <div class="card-body">
                <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit
                  quo minus id quod maxime placeat facere possimus voluptas.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>
            </div>

          </div>
          <!-- Grid column -->

          <!-- Grid column -->
          <div class="col-lg-4 col-md-6 mb-md-0 mb-4">

            <div class="card">
            <!-- Featured image -->
              <div class="view overlay rounded z-depth-2 mb-4">
                <img class="card-img-top" src="<c:url value="/resources/img/recipe_5.png"/>" alt="Sample image">
                <a>
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <div class="card-body">
                <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis
                  voluptatum deleniti atque corrupti quos dolores.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>
            </div>

          </div>
          <!-- Grid column -->

          <!-- Grid column -->
          <div class="col-lg-4 col-md-6 mb-0">

            <div class="card">
              <!-- Featured image -->
              <div class="view overlay rounded z-depth-2 mb-4">
                <img class="card-img-top" src="<c:url value="/resources/img/recipe_6.png"/>" alt="Sample image">
                <a>
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <div class="card-body">
                <!-- Post title -->
                <h4 class="card-title"><strong>Title of the recipe</strong></h4>
                <!-- Excerpt -->
                <p class="card-text">Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed
                  quia consequuntur magni dolores eos qui ratione.</p>
                <!-- Read more button -->
                <a class="btn btn-green btn-rounded btn-md">Read more</a>
              </div>
            </div>
          </div>
          <!-- Grid column -->

        </div>
        <!-- Grid row -->

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
    <!-- Section: Blog v.2 -->
    <%--<div class="row">--%>
      <%--<div class="col-2">--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>--%>
        <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>--%>
      <%--</div>--%>
      <%--<div class="col-10">--%>

      <%--</div>--%>
    <%--</div>--%>


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
