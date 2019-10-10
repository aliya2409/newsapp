<%--
  Created by IntelliJ IDEA.
  User: Aliya_Altynbekova
  Date: 10/3/2019
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><spring:message code="logo" text="default"/></title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="container content">

<h1><spring:message code="login"/></h1>
<form name='f' action="perform_login" method='POST'>
    <table>
        <tr>
            <td><spring:message code="username"/></td>
            <td><input type='text' name='username' value='' class="form-control"></td>
        </tr>
        <tr>
            <td><spring:message code="password"/></td>
            <td><input type='password' name='password' class="form-control"/></td>
        </tr>
        <tr>
            <td><button type="submit" class="btn btn-outline-primary btn-sm"><spring:message code="submit"/></button></td>
        </tr>
    </table>
</form>
    <a href="/users/showForm"><spring:message code="registration"/></a>
<c:if test="${param.error == true}"><spring:message code="error.login"/></c:if>
</div>
<%@ include file="footer.jsp"%>
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
