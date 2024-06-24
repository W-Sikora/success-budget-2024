<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="url" required="true" %>
<%@ attribute name="name1" required="true" %>
<%@ attribute name="name2" required="true" %>

<nav class="breadcrumb breadcrumb-margin" aria-label="breadcrumbs">
    <ul>
        <li>
            <a href="${url}">
                <fmt:message key="${name1}"/>
            </a>
        </li>
        <li class="is-active">
            <a href="#" aria-current="page">
                <fmt:message key="${name2}"/>
            </a>
        </li>
    </ul>
</nav>
