<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

                <c:url value="/account" var="userRecipesUrl">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <a class="bg-transparent" href="${userRecipesUrl}">
                    <div>
                        <span><img class="user_image" src="<c:url value="/resources/img/user.png"/>"></span>
                        <span><h5 class="user_title">${user.username}</h5></span>
                    </div>
                </a>
                <div class="user-card-info">
                    <p class="card-text"><spring:message code="Recipe.amount" arguments="${recipes_amount}" /></p>
                </div>
    </body>
</html>
