<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="field">
    <label class="label">
        <fmt:message key="priority"/>
    </label>
    <div class="control">
        <div class="select is-small is-fullwidth">
            <label>
                <select name="priority">
                    <custom:emptyOption/>
                    <c:forEach items="${priorities}" var="priority">
                        <option value="${priority.ordinal()}">
                            <fmt:message key="priority.${priority.ordinal()}"/>
                        </option>
                    </c:forEach>
                </select>
            </label>
        </div>
    </div>
</div>