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
            <div class="news-table">
                <a class="news-field__title">Title</a>
                <a class="news-field__date">Creation date</a>
                <a class="news-field__brief">Brief</a>
                <a class="news-field__content">Content</a>
                <h6 class="news-li__title">${news.title}</h6>
                <a class="news-li__date">${news.creationDate}</a>
                <a class="news-li__brief">${news.brief}</a>
                <a class="news-li__content">${news.content}</a>
            </div>
            <div class="btn-group info-btns" role="group">
                <button type="button" class="btn btn-outline-primary"
                        onclick="window.location.href='showForm?newsId=${id}'">Edit
                </button>
                <button type="button" class="btn btn-outline-danger"
                        onclick="window.location.href='delete/${id}'">Delete
                </button>
            </div>

            <form:form action="/comments/save" method="post" modelAttribute="comment" cssClass="new_comment_form">
                <h3>Add your comment</h3>
                <table>
                    <tr>
                        <td><form:label path="author">Author</form:label></td>
                        <td><form:input path="author" cssClass="form-control"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="content">Text</form:label></td>
                        <td><form:input path="content" cssClass="form-control"/></td>
                    </tr>
                </table>
                <form:hidden path="id"/>
                <form:hidden path="news.id" value="${news.id}"/>
                <form:hidden path="news.content" value="${news.content}"/>
                <form:hidden path="news.creationDate" value="${news.creationDate}"/>
                <form:hidden path="news.brief" value="${news.brief}"/>
                <form:hidden path="news.title" value="${news.title}"/>
                <form:hidden path="creationDate"/>
                <div class="btn-group info-btns">
                    <button type="submit" class="btn btn-outline-primary btn-sm">Save</button>
                    <button type="button" class="btn btn-outline-danger btn-sm">Cancel</button>
                </div>
            </form:form>

            <div id="comments">
                <h3>Comments</h3>
                <c:forEach items="${comments}" var="commentary">
                    <div class="news-comment-li">
                        <a class="comment-li-author">${commentary.author}</a>
                        <a class="comment-li-date">${commentary.creationDate}</a>
                        <a class="comment-li-content">${commentary.content}</a>
                        <a class="comment-li-rating">Rating: ${commentary.rating}</a>
                        <div class="btn-group comment-li-buttons">
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                    data-toggle="collapse" data-target="#editCollapse-${commentary.id}"
                                    aria-expanded="false" aria-controls="editCollapse-${commentary.id}">edit
                            </button>
                            <button type="button" class="btn btn-outline-danger btn-sm"
                                    onclick="window.location.href='/comments/delete/${news.id}/${commentary.id}'">delete
                            </button>
                        </div>
                    </div>
                    <div class="collapse" id="editCollapse-${commentary.id}">
                        <div>
                            <form:form action="/comments/save" method="post" modelAttribute="comment">
                                <table>
                                    <tr>
                                        <td><form:label path="author">Author</form:label></td>
                                        <td><form:input path="author" value="${commentary.author}"
                                                        cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path="content">Text</form:label></td>
                                        <td><form:input path="content" value="${commentary.content}"
                                                        cssClass="form-control"/></td>
                                    </tr>
                                </table>
                                <form:hidden path="id" value="${commentary.id}"/>
                                <form:hidden path="rating" value="${commentary.rating}"/>
                                <form:hidden path="news.id" value="${news.id}"/>
                                <form:hidden path="news.content" value="${news.content}"/>
                                <form:hidden path="news.creationDate" value="${news.creationDate}"/>
                                <form:hidden path="news.brief" value="${news.brief}"/>
                                <form:hidden path="news.title" value="${news.title}"/>
                                <form:hidden path="creationDate" value="${commentary.creationDate}"/>
                                <div class="btn-group">
                                    <button type="submit" class="btn btn-outline-primary btn-sm">Save</button>
                                    <button type="button" class="btn btn-outline-danger btn-sm">Cancel</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </c:forEach>
            </div>
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