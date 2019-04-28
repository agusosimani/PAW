<%--
  Created by IntelliJ IDEA.
  User: Miguel
  Date: 28/4/2019
  Time: 07:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
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

            <a href="#" class="navigation__list__item item_active">
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
             href="#myAccount"
             aria-expanded="true"
             aria-controls="yourMusic">
            Mi cuenta
        </div>

        <div class="collapse in" id="myAccount">

            <a href="#" class="navigation__list__item">
                <i class="ion-headphone"></i>
                <span>Mis recetas</span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-ios-musical-notes"></i>
                <span>Mis ingredientes</span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-person"></i>
                <span>Mis listas</span>
            </a>

            <a href="#" class="navigation__list__item">
                <i class="ion-document"></i>
                <span>Recetas favoritas</span>
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
</body>
</html>
