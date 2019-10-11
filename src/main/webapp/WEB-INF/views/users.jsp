<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><spring:message code="logo"/></title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container content">
    <div class="row">
        <div class="col-2">
            <%@ include file="sideNav.jsp" %>
        </div>
        <div class="col-10">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="username"/></th>
                    <th scope="col"><spring:message code="authorities"/></th>
                    <th scope="col"><spring:message code="edit"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td scope="row">${user.username}</td>
                        <td>${user.authorities}</td>
                        <td>
                            <form:form action="/admin/users/${user.id}/authorities" method="post"
                                       modelAttribute="authorities">
                                <div class="dropdown">
                                    <button class="btn btn-secondary btn-sm  dropdown-toggle" type="button"
                                            id="dropdownMenu1" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="true">
                                        <spring:message code="authorities"/>
                                    </button>
                                    <ul class="dropdown-menu checkbox-menu allow-focus" aria-labelledby="dropdownMenu1">
                                        <li>
                                            <label>
                                                <spring:message code="admin"/><form:checkbox path="roles"
                                                                                             value="ADMIN"/>
                                            </label>
                                        </li>

                                        <li>
                                            <label>
                                                <spring:message code="user"/><form:checkbox path="roles" value="USER"/>
                                            </label>
                                        </li>
                                        <li>
                                            <label>
                                                <spring:message code="moderator"/><form:checkbox path="roles"
                                                                                                 value="MODERATOR"/>
                                            </label>
                                        </li>
                                        <li>
                                            <button type="submit" class="btn btn-outline-primary btn-sm"><spring:message
                                                    code="submit"/></button>
                                        </li>
                                    </ul>
                                </div>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/admin/users/delete/${user.id}" method="post">
                                <button type="submit" class="btn btn-sm btn-outline-danger">
                                    <spring:message code="delete"/></button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>