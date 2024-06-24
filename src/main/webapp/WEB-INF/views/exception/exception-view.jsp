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
                    <div class="columns is-centered is-multiline">
                        <div class="column is-full has-text-centered">
                            <p class="mb-6">
                                <c:choose>
                                    <c:when test="${statusCode == '500'}">
                                        <i class="fas fa-bomb fa-4x"></i>
                                    </c:when>
                                    <c:when test="${statusCode == '403'}">
                                        <i class="fas fa-exclamation-triangle fa-4x"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-exclamation fa-4x"></i>
                                    </c:otherwise>
                                </c:choose>
                            </p>

                            <p class="subtitle is-3">
                                <fmt:message key="exception.message.${statusCode}"/>
                            </p>

                            <button class="button is-link is-outlined is-rounded mt-4" onclick="history.back()">
                                <span>
                                    <i class="fas fa-chevron-left mr-3"></i>
                                </span>
                                <span>
                                    <fmt:message key="back.to.previous.page.button"/>
                                </span>
                            </button>
                        </div>

                        <c:if test="${not empty message}">
                            <div class="column mt-5">
                                <div class="container">
                                    <div class="notification is-size-4">
                                        ${message}
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <custom:footer/>

</div>
</body>
</html>
