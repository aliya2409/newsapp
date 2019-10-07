<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><spring:message code="logo"/> - <spring:message code="edit"/></title>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="container content">
    <div class="row">
        <div class="col-2">
            <%@ include file="sideNav.jsp"%>
        </div>
        <div class="col-10">
            <form:form action="/news/saveNews" method="post" modelAttribute="news" class="news-table news-table__update">
                <form:label path="title" class="news-field__title"><spring:message code="title"/></form:label>
                <form:label path="brief" class="news-field__brief"><spring:message code="brief"/></form:label>
                <form:label path="content" class="news-field__content"><spring:message code="content"/></form:label>
                <form:hidden path="id" />
                <form:hidden path="creationDate" />
                <form:input path="title" class="news-li__title form-control"/>
                <form:textarea path="brief" rows="4" cols="50" class="news-li__brief form-control"/>
                <form:textarea path="content" rows="10" cols="50" class="news-li__content form-control"/>
                <div class="info-btns">
                    <button type="submit" class="btn btn-outline-primary"><spring:message code="save"/></button>
                    <button type="button" class="btn btn-outline-danger"><spring:message code="cancel"/></button>
                </div>
            </form:form>
        </div>
    </div>
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
