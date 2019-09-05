<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>All news</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="#">NEWS portal</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">English</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Russian</a>
            </li>
        </ul>
    </nav>
</header>
<div class="container content">
    <div class="row">
        <div class="col-2">
            <div class="side-nav">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="allnews">News List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="showForm">Add News</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-10">
            <form:form action="/news/delete" method="POST" modelAttribute="listDTO">
                <c:forEach items="${newsList}" var="news">
                    <div class="news-comment-li">
                        <h4 class="news-li__title">${news.title}</h4>
                        <a class="news-li__date align-right">${news.creationDate}</a>
                        <a class="news-li__brief">${news.brief}</a>
                        <div class="news-li__buttons">
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                    onclick="window.location.href='${news.id}'">view
                            </button>
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                    onclick="window.location.href='showForm?newsId=${news.id}'">edit
                            </button>
                            <form:checkbox path="ids" value="${news.id}"/>
                        </div>
                    </div>
                </c:forEach>
                <div class="info-btns">
                    <button type="submit" class="btn btn-outline-danger">Delete</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="container">
        <span class="text-muted">Copyright © EPAM 2019. All rights reserved.</span>
    </div>
</footer>
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
