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
                            <custom:breadcrumb url="/" name1="landing.page.title" name2="login.page.title"/>
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="login.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-5">

                            <form action="<c:url value="/login"/>" method="post">
                                <sec:csrfInput/>
                                <div class="field">
                                    <label for="username" class="label">
                                        <fmt:message key="username"/>
                                        <fmt:message key="required.field.sign"/>
                                    </label>
                                    <div class="control">
                                        <input name="username" id="username" type="text" class="input"/>
                                    </div>
                                </div>

                                <div class="field">
                                    <label for="password" class="label">
                                        <fmt:message key="password"/>
                                        <fmt:message key="required.field.sign"/>
                                    </label>
                                    <div class="control">
                                        <input name="password" id="password" type="password" class="input"/>
                                    </div>
                                    <c:if test="${not empty param.invalid && param.invalid}">
                                        <p class="validation-message has-text-danger">
                                            <fmt:message key="login.form.invalid"/>
                                        </p>
                                    </c:if>
                                </div>

                                <custom:required-field-info/>

                                <div class="field is-grouped is-grouped-centered">
                                    <div class="control mt-5">
                                        <button type="submit" class="button is-link is-outlined is-rounded">
                                            <fmt:message key="login.form.button"/>
                                        </button>
                                    </div>
                                </div>
                            </form>

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
