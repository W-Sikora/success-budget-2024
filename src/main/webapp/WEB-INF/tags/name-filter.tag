<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="field">
    <label class="label" for="name">
        <fmt:message key="name"/>
    </label>
    <div class="control">
        <input class="input is-small" type="text" name="name" id="name">
    </div>
</div>