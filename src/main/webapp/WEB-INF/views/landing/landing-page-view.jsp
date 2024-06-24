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
                <div class="container has-text-centered">
                    <div class="columns my-4">
                        <div class="column is-8 is-offset-2 has-small-bottom-margin">
                            <div>
                                <h1 class="mb-5 is-size-1 is-size-3-mobile has-text-weight-bold">
                                    <fmt:message key="app.name"/>
                                </h1>

                                <h2 class="subtitle is-3 has-text-weight-light has-text-grey line-spacing">
                                    <fmt:message key="info.main"/>
                                </h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="call-to-action.jsp" %>

        </div>

        <custom:footer/>

    </div>
</div>
</body>
</html>
