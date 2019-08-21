<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>All News</title>
</head>
<body>
<table>
    <tr>
        <th>Title</th>
        <th>Brief</th>
        <th>Content</th>
        <th>Creation Date</th>
    </tr>
    <c:forEach items="${newsList}" var="element">
        <tr>
            <td>${element.title}</td>
            <td>${element.brief}</td>
            <td>${element.content}</td>
            <td>${element.creationDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
