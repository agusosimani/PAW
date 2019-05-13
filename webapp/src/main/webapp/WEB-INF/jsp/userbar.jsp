<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <section class="side_card">
        <div class="card">
            <div class="card-body">
                <img class="user_image" src="<c:url value="/resources/img/user.png"/>">
                <h5 class="user_title">${user.username}</h5>
                <br/><br/></br></br>
                <p class="card-text">${recipes_amount} recetas subidas</p>
            </div>
        </div>
    </section>
    </body>
</html>
