<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="side-nav">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="/news/allnews"><spring:message code="newsList"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/news/showForm"><spring:message code="addNews"/></a>
        </li>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="/admin/users"><spring:message code="users"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users/showForm"><spring:message code="addUser"/></a>
            </li>
        </sec:authorize>
    </ul>
</div>
