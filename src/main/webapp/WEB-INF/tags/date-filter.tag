<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="field">
    <div class="columns">
        <div class="column">
            <label class="label" for="dateFrom">
                <fmt:message key="date.from"/>
            </label>
            <div class="control">
                <input class="input is-small" type="date" name="dateFrom" id="dateFrom">
            </div>
        </div>
        <div class="column">
            <label class="label" for="dateTo">
                <fmt:message key="date.to"/>
            </label>
            <div class="control">
                <input class="input is-small" type="date" name="dateTo" id="dateTo">
            </div>
        </div>
    </div>
</div>