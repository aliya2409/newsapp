<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/news/allnews"><spring:message code="logo"/></a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="?lang=en"><spring:message code="lang.en"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?lang=ru"><spring:message code="lang.ru"/></a>
            </li>
            <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
                <a class="nav-link" href="/logout"><spring:message code="logout"/></a>
            </li>
            </sec:authorize>
        </ul>
    </nav>
</header>