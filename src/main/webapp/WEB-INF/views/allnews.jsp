<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
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
            <form:form action="/news/delete" method="POST" modelAttribute="idsDTO">
                <c:forEach items="${newsList}" var="news">
                    <div class="news-comment-li">
                        <h4 class="news-li__title">${news.title}</h4>
                        <spring:message code='date.pattern' var="datePattern"/>
                        <javatime:format value="${news.creationDate}" pattern="${datePattern}" var="parsedDate"/>
                        <a class="news-li__date align-right">${parsedDate}</a>
                        <a class="news-li__brief">${news.brief}</a>
                        <div class="news-li__buttons">
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                    onclick="window.location.href='${news.id}'"><spring:message code="view"/>
                            </button>
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                    onclick="window.location.href='showForm?newsId=${news.id}'">
                                <spring:message code="edit"/>
                            </button>
                            <sec:authorize access="hasAuthority('MODERATOR')">
                                <form:checkbox path="ids" value="${news.id}"/>
                            </sec:authorize>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty newsList}">
                    <sec:authorize access="hasAuthority('MODERATOR')">
                        <div class="info-btns">
                            <button type="submit" class="btn btn-outline-danger">
                                <spring:message code="delete"/>
                            </button>
                        </div>
                    </sec:authorize>
                </c:if>
            </form:form>
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
