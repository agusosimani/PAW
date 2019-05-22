<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="modal fade" id="ban-user" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <h3><spring:message code="user.banWarning" arguments="${user.username}"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <c:if test="${isAdmin}">
                    <c:url var="banUserUrl" value="/ban_user">
                        <c:param name="userId" value="${user.id}"/>
                    </c:url>
                    <form:form action="${banUserUrl}" method="post">
                        <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                        <button type="submit" class="btn btn-red float-right"><spring:message code="confirm"/></button>
                    </form:form>
                </c:if>

            </div>
        </div>
    </div>
</div>
</body>
</html>
