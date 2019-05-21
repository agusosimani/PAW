<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
<%@include file="sidebar.jsp" %>

<section class="main_container">
    <h3>${title}</h3>
    <section class="browse">
        <c:if test="${empty cookList}">
            <c:choose>
                <c:when test="${editable}">
                    <p class="empty-warning"><spring:message code="noCookListsMy"/></p>
                </c:when>
                <c:otherwise>
                    <p class="empty-warning"><spring:message code="noCookLists" arguments="${user.username}"/></p>
                </c:otherwise>
            </c:choose>
        </c:if>
        <div class="card-deck">

            <c:forEach var="cooklist" items="${cookList}">

                <div class="col-lg-4 col-md-12 mb-lg-0 mb-4 columns-cards-recipe">
                    <div class="card card-cascade narrower mb-4">

                        <div class="card-body card-body-cascade">

                            <h4 class="card-title"><strong><c:out value="${cooklist.name}"/></strong></h4>

                            <c:url value="/cooklist" var="cooklistUrl">
                                <c:param name="cookListId" value="${cooklist.id}"/>
                                <c:param name="userId" value="${user.id}"/>
                            </c:url>
                            <a href="${cooklistUrl}" class="stretched-link"></a>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

    <section class="side_card">
        <div class="card">
            <div class="card-body">
                <%@include file="userbar.jsp" %>
            </div>
        </div>
    </section>
</section>

<c:if test="${editable}">
    <button class="btn btn-green add" data-toggle="modal" id="add-recipe-cooklist-modal"
            data-target="#new-cooklist">+
    </button>
</c:if>
<%@include file="cooklists_add_modal.jsp" %>
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
