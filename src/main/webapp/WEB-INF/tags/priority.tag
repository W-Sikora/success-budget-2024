<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="ordinal" required="true" %>

<c:set var="text">
    <fmt:message key="priority.${ordinal}"/>
</c:set>

<c:if test="${not empty ordinal and not empty text}">
    <c:choose>
        <c:when test="${ordinal == 0}">
            <span class="tag is-danger is-light">${text}</span>
        </c:when>
        <c:when test="${ordinal == 1}">
            <span class="tag is-warning is-light">${text}</span>
        </c:when>
        <c:when test="${ordinal == 2}">
            <span class="tag is-primary is-light">${text}</span>
        </c:when>
        <c:otherwise>
            <span class="tag is-light">${text}</span>
        </c:otherwise>
    </c:choose>
</c:if>