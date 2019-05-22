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
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="<c:url value="/resources/css/mdb.css"/>" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery-ui.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/style_spotify.css"/>" rel="stylesheet">
</head>

<body>
<c:set var="isIndex" value="true" scope="request"/>
<%@include file="sidebar.jsp" %>

<section class="main_container">

    <h4 class="navigation-title"><spring:message code="myStatistics"/></h4>
    <section class="browse">

        <c:url value="/statistics" var="statisticsUrl"/>
        <form:form modelAttribute="dateForm" action="${statisticsUrl}" method="get">
            <form:input path="from" id="datepicker" maxlength="10"/>
            <form:input path="to" id="datepicker2" maxlength="10"/>
            <button class="btn btn-green" type="submit">
                <spring:message code="getStatistics"/>
            </button>
        </form:form>


        <canvas id="barChart"></canvas>
    </section>

    <section class="side_card">
        <div class="card">
            <div class="card-body" id="user-big-card">
                <%@include file="userbar.jsp" %>
            </div>
        </div>
    </section>
</section>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/spotify.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/chart.js"/>"></script>
<script>
    var list = ${list};
</script>

</body>

</html>
