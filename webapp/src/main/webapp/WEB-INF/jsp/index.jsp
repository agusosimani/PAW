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

  <body>
    <section class="content">

      <div class="content__left">

        <section class="navigation">

          <!-- Main -->
          <div class="navigation__list">
            <img class="nav_logo" src="<c:url value="/resources/img/foodify_inline.png"/>" alt="LOGO">
            <div class="navigation__list__header"
                 role="button"
                 data-toggle="collapse"
                 href="#main"
                 aria-expanded="true"
                 aria-controls="main">
              Main
            </div>

            <div class="collapse in" id="main">

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-browsers"></i>
                <span>Browse</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-person-stalker"></i>
                <span>Activity</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-radio-waves"></i>
                <span>Radio</span>
              </a>

            </div>

          </div>
          <!-- / -->

          <!-- Your Music -->
          <div class="navigation__list">

            <div class="navigation__list__header"
                 role="button"
                 data-toggle="collapse"
                 href="#yourMusic"
                 aria-expanded="true"
                 aria-controls="yourMusic">
              Your Music
            </div>

            <div class="collapse in" id="yourMusic">

              <a href="#" class="navigation__list__item">
                <i class="ion-headphone"></i>
                <span>Songs</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Albums</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-person"></i>
                <span>Artists</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-document"></i>
                <span>Local Files</span>
              </a>

            </div>

          </div>
          <!-- / -->

          <!-- Playlists -->
          <div class="navigation__list">

            <div class="navigation__list__header"
                 role="button"
                 data-toggle="collapse"
                 href="#playlists"
                 aria-expanded="true"
                 aria-controls="playlists">
              Playlists
            </div>

            <div class="collapse in" id="playlists">

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Doo Wop</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Pop Classics</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Love $ongs</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Hipster</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>New Music Friday</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Techno Poppers</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Summer Soothers</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Hard Rap</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Pop Rap</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>5 Stars</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Dope Dancin</span>
              </a>

              <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Sleep</span>
              </a>

            </div>

          </div>
          <!-- / -->

        </section>

        <section class="playlist">

          <a href="#">

            <i class="ion-ios-plus-outline"></i>

            New Playlist

          </a>

        </section>
      </div>

    </section>

    <div class="row">
      <div class="col-2">
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>
        <button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>
      </div>
      <div class="col-10">

      </div>
    </div>


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
