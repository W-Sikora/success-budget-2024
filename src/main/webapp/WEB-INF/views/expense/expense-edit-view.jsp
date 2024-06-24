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
                            <custom:breadcrumb url="/expense" name1="expense.page.title" name2="expense.${param.id ? 'edit' : 'init'}.page.title"/>
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="expense.${param.id ? 'edit' : 'init'}.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-5">

                            <form:form modelAttribute="expenseEditForm" action="/expense/edit" method="post">

                                <form:hidden path="id"/>
                                <form:hidden path="userId"/>

                                <div class="field">
                                    <form:label path="name" cssClass="label">
                                        <fmt:message key="name"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="control">
                                        <form:input path="name" type="text" cssClass="input"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="name" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="description" cssClass="label">
                                        <fmt:message key="description"/>
                                    </form:label>

                                    <div class="control">
                                        <form:textarea path="description" class="textarea"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="description" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="date" cssClass="label">
                                        <fmt:message key="date"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="control">
                                        <form:input path="date" type="date" cssClass="input" dateFormat="dd-MM-yyyy"/>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="date" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                <div class="field">
                                    <form:label path="amount" cssClass="label">
                                        <fmt:message key="expense.amount"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="field has-addons has-mb-10">
                                        <p class="control is-expanded">
                                            <form:input path="amount" type="number" cssClass="input" step="0.01" min="0.00"/>
                                        </p>

                                        <p class="control">
                                            <span class="select">
                                                <form:select path="currency" cssClass="select" disabled="true">
                                                    <c:forEach items="${currencies}" var="currency">
                                                        <form:option value="${currency.ordinal()}"
                                                                     label="${currency.sign}"
                                                                     selected="${currency == userCurrency ? 'selected' : ''}" />
                                                    </c:forEach>
                                                </form:select>
                                                <input type="hidden" name="currency" value="${userCurrency.ordinal()}">
                                            </span>
                                        </p>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="amount" cssClass="has-text-danger"/>
                                        <form:errors path="currency" cssClass="has-text-danger"/>
                                    </p>
                                </div>

                                 <div class="field">
                                    <form:label path="categoryId" cssClass="label">
                                        <fmt:message key="category"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="select is-fullwidth">
                                        <form:select path="categoryId" cssClass="select">
                                            <custom:emptyOption/>
                                            <c:forEach items="${categories}" var="category">
                                                <form:option value="${category.id()}">
                                                    ${category.name().name}
                                                </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="categoryId" cssClass="has-text-danger"/>
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
