<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="modal fade" id="new-cooklist" tabindex="-1" role="form">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3><spring:message code="cooklist.addTitle"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <c:url var="createCooklistUrl" value="/create_cooklist">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <form:form modelAttribute="newCookListForm" action="${createCooklistUrl}" method="post">

                <form:label path="name"><spring:message code="cooklist.name"/></form:label>
                <form:input path="name" type="text" class="form-control"/>
                <form:errors path="name" cssClass="form-text text-muted" element="small"/>


            </div>

            <div class="modal-footer">
                <a class="btn btn-blue-grey" data-dismiss="modal"><spring:message code="close"/></a>
                <button type="submit" class="btn btn-green"><spring:message code="confirm"/></button>
            </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
