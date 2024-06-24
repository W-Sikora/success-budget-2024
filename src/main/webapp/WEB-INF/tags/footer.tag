<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<footer class="footer footer-modified has-background-light">
    <div class="container">
        <div class="columns is-centered">

            <div class="column">
                <p class="is-size-5">
                    <a class="has-text-dark" href="<c:url value="/"/>">
                        <fmt:message key="app.name"/>
                    </a>
                </p>
                <p class="is-size-7 mt-2 has-text-grey line-spacing">
                    <fmt:message key="info.main"/>
                </p>
            </div>

            <div class="column">
                <p class="is-size-6 has-text-dark">
                    <fmt:message key="footer.page.design.text"/>:
                </p>
                <p class="mt-1">
                    <a class="is-size-7 has-text-dark" href="https://bulma.io">
                        <fmt:message key="footer.page.design.by"/>
                    </a>
                </p>
            </div>

            <div class="column">
                <p class="is-size-6 has-text-dark">
                    <fmt:message key="footer.developed.text"/>:
                </p>
                <p class="is-size-7 mt-1">
                    <fmt:message key="footer.developed.by"/>
                </p>
            </div>

            <div class="column">
                <p class="is-size-6 has-text-dark">
                    <fmt:message key="footer.find.us"/>:
                </p>
                <p class="mt-2">
                    <a class="has-text-black" href="https://github.com/W-Sikora/success-budget">
                        <i class="fab fa-github fa-2x"></i>
                    </a>
                </p>
            </div>
        </div>
    </div>
</footer>

<div id="smallScreenModal" class="modal">
    <div class="modal-background modal-background-blur"></div>
    <div class="modal-content">
        <div class="box">
            <p class="has-text-centered">
                <fmt:message key="small.screen.modal.text"/>
            </p>
        </div>
    </div>
</div>