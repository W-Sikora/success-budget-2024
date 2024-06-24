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
                            <custom:breadcrumb url="/category" name1="category.page.title" name2="category.${param.id ? 'edit' : 'init'}.page.title"/>
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="category.${param.id ? 'edit' : 'init'}.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-5">

                            <form:form modelAttribute="categoryEditForm" action="/category/edit" method="post">

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
                                    <form:label path="priority" cssClass="label">
                                        <fmt:message key="priority"/>
                                        <fmt:message key="required.field.sign"/>
                                    </form:label>

                                    <div class="select is-fullwidth">
                                        <form:select path="priority" cssClass="select">
                                            <custom:emptyOption/>
                                            <c:forEach items="${priorities}" var="priorityId">
                                                <form:option value="${priorityId.ordinal()}">
                                                    <fmt:message key="priority.${priorityId.ordinal()}"/>
                                                </form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                    <p class="validation-message">
                                        <form:errors path="priority" cssClass="has-text-danger"/>
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
