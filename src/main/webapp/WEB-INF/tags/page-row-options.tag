<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="editButtonUrl" required="true" %>

<td class="has-text-centered">
    <a href="${editButtonUrl}" class="button is-small is-warning is-rounded">
        <span class="mr-1">
            <i class="far fa-edit fa-sm"></i>
        </span>
        <fmt:message key="edit.button"/>
    </a>
</td>