<%@ page contentType="text/html;charset=UTF-8" %>

<section class="section my-4">
    <div class="container center-content">
        <div class="columns is-vcentered">
            <div class="column is-three-quarters has-text-centered">
                <h3 class="subtitle is-3"><fmt:message key="landing.page.cta.text"/></h3>
            </div>
            <div class="column has-text-centered">
                <sec:authorize access="isAnonymous()">
                    <a class="button is-large is-link is-outlined is-rounded" href="<c:url value="/registration"/>">
                        <fmt:message key="landing.page.registration.button"/>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="button is-large is-link is-outlined is-rounded" href="<c:url value="/workflow"/>">
                    <fmt:message key="landing.page.next.button"/>
                </sec:authorize>
                </a>
            </div>
        </div>
    </div>
</section>
