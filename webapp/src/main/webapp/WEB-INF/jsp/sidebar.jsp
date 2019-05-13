
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
    <section class="navigation">

        <!-- Main -->
        <div class="navigation__list">
            <c:url value="/" var="homeUrl"/>
            <a class="bg-transparent" href="${homeUrl}">
                <img class="nav_logo" src="<c:url value="/resources/img/foodify_inline.png"/>" alt="LOGO">
            </a>

            <div>
                <a class="float_left" href="${homeUrl}">
                    <label>Home</label>
                </a>
            </div>

        </div>
        <!-- / -->

        <!-- Your Music -->
        <div class="navigation__list">

            <div>
                <c:url value="/my_account" var="myAccountUrl"/>
                <a class="float_left" href="${myAccountUrl}">
                    <label>My account</label>
                </a>

                <div class="bar_arrow navigation__list__header"
                     role="button"
                     data-toggle="collapse"
                     href="#myAccount"
                     aria-expanded="true"
                     aria-controls="yourMusic">
                </div>
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



    </section>
    <%--<div class="row">--%>
    <%--<div class="col-2">--%>
    <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-home"></i> Inicio</button>--%>
    <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-search"></i> Search</button>--%>
    <%--<button type="button" class="btn btn-nav btn-lg btn-block"><i class="fas fa-book-open"></i> Biblioteca</button>--%>
    <%--</div>--%>
    <%--<div class="col-10">--%>

    <%--</div>--%>
    <%--</div>--%>
</body>
</html>
