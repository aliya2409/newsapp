<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Index jsp</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar w/ text</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
            </ul>
    </nav>
</header>
<div class="container">
    <div class="row">
        <div class="col-4">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="#">News List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Add News</a>
                </li>
            </ul>
        </div>
        <div class="col-8">
            <c:forEach items="${newsList}" var="news">
            <div class="news-li">
                <h4 class="news-li__title">${news.title}</h4>
                <div class="news-li__date">${news.creationDate}</div>
                <div class="news-li__brief">${news.brief}</div>
                <button type="button" class="btn btn-outline-primary btn-sm">view</button>
                <button type="button" class="btn btn-outline-primary btn-sm">edit</button>
            </div>
            </c:forEach>
        </div>
    </div>
</div>
<footer>Copyright EPAM 2019. All rights reserved.</footer>
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