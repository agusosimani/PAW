<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <section class="side_card">
        <div class="card">
            <div class="card-body">
                <c:url value="/user_recipes" var="userRecipesUrl">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <a class="bg-transparent" href="${userRecipesUrl}">
                    <div>
                        <img class="user_image" src="<c:url value="/resources/img/user.png"/>">
                        <h5 class="user_title">${user.username}</h5>
                    </div>
                </a>
                <br/><br/></br></br>
                <p class="card-text"><spring:message code="Recipe.amount" arguments="${recipes_amount}" /></p>
<%--                ${recipes_amount} recetas subidas--%>
            </div>
        </div>
    </section>
    </body>
</html>
