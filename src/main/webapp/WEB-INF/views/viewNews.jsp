<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="com.javalab.newsportal.util.Constants" %>
<spring:message code='date.pattern' var="datePattern"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><spring:message code="logo"/> - <spring:message code="view"/></title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container content">
    <div class="row">
        <div class="col-2">
            <%@ include file="sideNav.jsp" %>
        </div>
        <div class="col-10">
            <div class="news-table">
                <a class="news-field__title"><spring:message code="title"/></a>
                <a class="news-field__date"><spring:message code="creationDate"/></a>
                <a class="news-field__brief"><spring:message code="brief"/></a>
                <a class="news-field__content"><spring:message code="content"/></a>
                <h6 class="news-li__title">${news.title}</h6>
                <a class="news-li__date"><javatime:format value="${news.creationDate}" pattern="${datePattern}"/></a>
                <a class="news-li__brief">${news.brief}</a>
                <a class="news-li__content">${news.content}</a>
            </div>
            <div class="btn-group info-btns" role="group">
                <button type="button" class="btn btn-outline-primary"
                        onclick="window.location.href='showForm?newsId=${id}'"><spring:message code="edit"/>
                </button>
                <sec:authorize access="hasAuthority('MODERATOR')">
                    <button type="button" class="btn btn-outline-danger"
                            onclick="window.location.href='delete/${id}'"><spring:message code="delete"/>
                    </button>
                </sec:authorize>
            </div>
            <div id="comments">
                <h3><spring:message code="comments"/></h3>
                <div class="dropdown">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <spring:message code="sortBy"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="/news/${news.id}?sortBy=date"><spring:message code="byDate"/></a>
                        <a class="dropdown-item"
                           href="/news/${news.id}?sortBy=${Constants.RATING_SORTING_OPTION}"><spring:message
                                code="byRating"/></a>
                    </div>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <form:form action="/comments/save" method="post" modelAttribute="comment"
                               cssClass="new_comment_form">
                        <h5><spring:message code="addComment"/></h5>
                        <table>
                            <tr>
                                <td><form:label path="content"><spring:message code="text"/></form:label></td>
                                <td><form:textarea path="content" rows="3" cols="30" cssClass="form-control"/></td>
                            </tr>
                        </table>
                        <form:hidden path="author"/>
                        <form:hidden path="id"/>
                        <form:hidden path="news.id" value="${news.id}"/>
                        <form:hidden path="news.content" value="${news.content}"/>
                        <form:hidden path="news.creationDate" value="${news.creationDate}"/>
                        <form:hidden path="news.brief" value="${news.brief}"/>
                        <form:hidden path="news.title" value="${news.title}"/>
                        <form:hidden path="creationDate"/>
                        <div class="btn-group info-btns">
                            <button type="submit" class="btn btn-outline-primary btn-sm"><spring:message
                                    code="save"/></button>
                            <button type="button" class="btn btn-outline-danger btn-sm"><spring:message
                                    code="cancel"/></button>
                        </div>
                    </form:form>
                </sec:authorize>
                <c:forEach items="${comments}" var="commentary">
                    <div class="news-comment-li">
                        <a class="comment-li-author">${commentary.author}</a>
                        <a class="comment-li-date"><javatime:format value="${commentary.creationDate}" pattern="${datePattern}"/></a>
                        <a class="comment-li-content">${commentary.content}</a>
                        <a class="comment-li-rating"><spring:message code="rating"/> ${commentary.rating}</a>
                            <sec:authentication var="user" property="principal"/>
                            <c:set var="containsModerator" value="false"/>
                            <c:forEach var="item" items="${user.authorities}">
                                <c:if test="${item eq 'MODERATOR'}">
                                    <c:set var="containsModerator" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${commentary.author == user.username or containsModerator == true}">
                                <form action="/comments/delete/${news.id}/${commentary.id}" method="post" class="comment-li-buttons">
                                    <input  type="hidden" name="username" value="${commentary.author}"/>
                                    <button type="submit" class="btn btn-outline-danger btn-sm btn-block">
                                        <spring:message code="delete"/>
                                    </button>
                                </form>
                            </c:if>
                            <c:if test="${commentary.author == user.username}">
                                <button type="button" class="btn btn-outline-primary btn-sm btn-extra"
                                        data-toggle="collapse" data-target="#editCollapse-${commentary.id}"
                                        aria-expanded="false" aria-controls="editCollapse-${commentary.id}">
                                    <spring:message
                                            code="edit"/>
                                </button>
                            </c:if>
                    </div>
                    <div class="collapse" id="editCollapse-${commentary.id}">
                        <div>
                            <form:form action="/comments/save" method="post" modelAttribute="comment">
                                <table>
                                    <tr>
                                        <td><form:label path="content"><spring:message
                                                code="content"/></form:label></td>
                                        <td><form:textarea path="content" rows="3" cols="30"
                                                           value="${commentary.content}"
                                                           cssClass="form-control"/></td>
                                    </tr>
                                </table>
                                <form:hidden path="id" value="${commentary.id}"/>
                                <form:hidden path="author" value="${commentary.author}"/>
                                <form:hidden path="rating" value="${commentary.rating}"/>
                                <form:hidden path="news.id" value="${news.id}"/>
                                <form:hidden path="news.content" value="${news.content}"/>
                                <form:hidden path="news.creationDate" value="${news.creationDate}"/>
                                <form:hidden path="news.brief" value="${news.brief}"/>
                                <form:hidden path="news.title" value="${news.title}"/>
                                <form:hidden path="creationDate" value="${commentary.creationDate}"/>
                                <div class="btn-group">
                                    <button type="submit" class="btn btn-outline-primary btn-sm"><spring:message
                                            code="save"/></button>
                                    <button type="button" class="btn btn-outline-danger btn-sm"><spring:message
                                            code="cancel"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </c:forEach>
            </div>
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