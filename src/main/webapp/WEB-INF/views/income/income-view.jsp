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
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="income.page.title"/>
                                </h1>
                            </div>
                            <div class="notification">
                                <div class="content">
                                    <strong>
                                        <fmt:message key="info"/>
                                    </strong>

                                    <%@include file="income-hints.jsp" %>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-11">

                            <custom:page-menu/>

                            <%@ include file="income-summary.jsp" %>

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
