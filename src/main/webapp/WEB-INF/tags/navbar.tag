<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<c:set var="currentLocale" value="${pageContext.response.locale.toString()}"/>

<div class="hero-head ">
    <nav class="navbar navbar-fixed-top">
        <div class="navbar-brand">
            <a class="is-size-4	has-text-weight-bold ml-6 mt-3 has-text-dark" href="<c:url value="/"/>">
                <fmt:message key="app.name"/>
            </a>
        </div>
        <div id="navbarBasic" class="navbar-menu">
            <div class="navbar-end mt-3 mr-4">
                <div class="navbar-item">
                    <div class="buttons">
                        <div class="dropdown mr-2" id="navbarDropdown">
                            <div class="dropdown-trigger">
                                <button class="button is-small is-link is-rounded is-outlined" aria-haspopup="true" aria-controls="dropdown-menu6">
                                    <span>
                                        <fmt:message key="navbar.language.selection"/>
                                    </span>
                                    <span class="ml-2">
                                        <i class="fas fa-language fa-2x"></i>
                                    </span>
                                </button>
                            </div>
                            <div class="dropdown-menu is-narrow-dropdown ml-6" id="dropdown-menu6" role="menu">
                                <div class="dropdown-content has-text-centered">
                                    <c:if test="${!currentLocale.equals('pl')}">
                                        <a class="change-locale" data-code="pl">
                                            <img alt="pl" width="32px" src="<c:url value="/static/icons/pl.png"/>"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${not currentLocale.equals('en')}">
                                        <a class="change-locale" data-code="en">
                                            <img alt="en" width="32px" src="<c:url value="/static/icons/en.png"/>"/>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                            <form id="localeForm" class="is-hidden" action="<c:url value="/set-locale"/>" method="post">
                                <sec:csrfInput/>
                                <label>
                                    <input name="language">
                                </label>
                                <label>
                                    <input name="originalUrl">
                                </label>
                            </form>
                        </div>

                        <sec:authorize access="isAnonymous()">
                            <button id="navbarLoginButton" class="button is-small is-success is-rounded is-outlined">
                                <span>
                                    <fmt:message key="navbar.log.in"/>
                                </span>
                                <span class="ml-2">
                                    <i class="fas fa-sign-in-alt fa-lg"></i>
                                </span>
                            </button>
                        </sec:authorize>

                        <sec:authorize access="isAuthenticated()">
                            <form id="navbarLogoutForm" class="is-hidden" action="<c:url value="/logout"/>" method="post">
                                <sec:csrfInput/>
                            </form>

                            <button id="navbarLogoutButton" class="button is-small is-danger is-rounded is-outlined">
                                <span>
                                    <fmt:message key="navbar.log.out"/>
                                </span>
                                <span class="ml-2">
                                    <i class="fa fa-sign-out-alt fa-lg"></i>
                                </span>
                            </button>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>