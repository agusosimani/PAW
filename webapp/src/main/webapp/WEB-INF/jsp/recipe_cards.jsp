<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

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

                        <c:url value="/recipe" var="recipeUrl">
                            <c:param name="recipeId" value="${recipe.id}"/>
                        </c:url>
                        <a class="btn btn-unique waves-effect waves-light" href="${recipeUrl}">Read more</a>

                    </div>
                    <!--/.Card content-->

                </div>
            </div>
        </c:forEach>
    </div>


</section>
</body>
</html>
