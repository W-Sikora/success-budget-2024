<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="buttonUrl" required="true" %>

<form action="${buttonUrl}" method="get">
    <sec:csrfInput/>
    <button type="submit" class="button is-warning is-rounded"
        <c:if test="${not empty editButtonDisabled && editButtonDisabled}">disabled</c:if>>
        <span class="mr-2">
            <i class="far fa-edit fa-sm"></i>
        </span>
        <span>
            <fmt:message key="edit.button"/>
        </span>
    </button>
</form>