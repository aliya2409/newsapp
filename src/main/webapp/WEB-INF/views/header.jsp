<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/news/allnews"><spring:message code="logo"/></a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
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
<script type="text/javascript">
    const url_string = window.location.href;
    const url = new URL(url_string);
    let lang = url.searchParams.get('lang');
    if(lang == null) {lang = 'en'}
    const language = document.querySelector('[href="?lang=' + lang + '"]').parentNode;
    language.classList.add("active");
</script>