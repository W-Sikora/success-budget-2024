<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="${cookie['language'].value}">
<%@ include file="../imports/head-imports.jsp" %>
<body>
<div>
    <div class="page-container">
        <div class="hero is-fullheight is-fullheight-with-navbar">

            <custom:navbar/>

            <div class="hero-body">
                <div class="container main-section">
                    <div class="columns is-centered">
                        <div class="column">
                            <custom:breadcrumb url="/" name1="landing.page.title" name2="registration.page.title"/>
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="registration.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-5">

                            <form:form modelAttribute="registrationForm" action="/registration" method="post">
                                <div class="field">
                                    <form:label path="username" cssClass="label">
                                        <fmt:message key="username"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="control">
                                        <form:input path="username" type="text" cssClass="input"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="username" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="password" cssClass="label">
                                        <fmt:message key="password"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="control">
                                        <form:input path="password" type="password" cssClass="input"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="password" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="repeatedPassword" cssClass="label">
                                        <fmt:message key="repeated.password"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="control">
                                        <form:input path="repeatedPassword" type="password" cssClass="input"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="repeatedPassword" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="primaryCurrency" cssClass="label">
                                        <fmt:message key="major.currency"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="select is-fullwidth">
                                        <form:select path="primaryCurrency" cssClass="select">
                                            <custom:emptyOption/>
                                            <c:forEach items="${currencies}" var="currency">
                                                <form:option value="${currency.ordinal()}">
                                                    <fmt:message key="currency.${currency.ordinal()}"/>
                                                </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="primaryCurrency" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <custom:required-field-info/>

                                <div class="field is-grouped is-grouped-centered">
                                    <div class="control mt-5">
                                        <button type="submit" class="button is-link is-outlined is-rounded">
                                            <fmt:message key="registration.form.button"/>
                                        </button>
                                    </div>
                                </div>
                            </form:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <custom:footer/>

</div>
</body>
</html>
