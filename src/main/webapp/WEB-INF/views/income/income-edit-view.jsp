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
                            <custom:breadcrumb url="/income" name1="income.page.title" name2="income.init.page.title"/>
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="income.init.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-5">

                            <form:form modelAttribute="incomeEditForm" action="/income/edit" method="post">

                                <form:hidden path="id"/>
                                <form:hidden path="userId"/>

                                <div class="field">
                                    <form:label path="initialAmount" cssClass="label">
                                        <fmt:message key="money"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="field has-addons has-mb-10">
                                        <p class="control is-expanded">
                                            <form:input path="initialAmount" type="number" cssClass="input" step="0.01" min="0.00"/>
                                        </p>

                                        <p class="control">
                                            <span class="select">
                                                <form:select path="initialCurrency" cssClass="select" disabled="true">
                                                    <c:forEach items="${currencies}" var="currency">
                                                        <form:option value="${currency.ordinal()}"
                                                                     label="${currency.sign}"
                                                                     selected="${currency == userCurrency ? 'selected' : ''}" />
                                                    </c:forEach>
                                                </form:select>
                                                <input type="hidden" name="initialCurrency" value="${userCurrency.ordinal()}">
                                            </span>
                                        </p>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="initialAmount" cssClass="has-text-danger"/>
                                        <form:errors path="initialCurrency" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <custom:required-field-info/>

                                <div class="field is-grouped is-grouped-centered">
                                    <div class="control mt-5">
                                        <button type="submit" class="button is-link is-rounded">
                                            <fmt:message key="save.button"/>
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
</div>

<custom:footer/>

</body>
</html>
