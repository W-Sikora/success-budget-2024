<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<section class="section has-text-centered has-medium-padding">
    <hr class="mt-1">
    <div class="columns is-centered">

        <div class="column mr-6">
            <button class="button is-link is-outlined is-rounded" id="previousStepButton"
                    <c:if test="${not empty backButtonDisabled && backButtonDisabled}">disabled</c:if>>
                <span class="mr-2">
                    <i class="fas fa-chevron-left"></i>
                </span>
                <span>
                    <fmt:message key="back.button"/>
                </span>
            </button>
            <form id="previousStepForm" class="is-hidden" action="<c:url value="/workflow/previous"/>" method="get">
                <sec:csrfInput/>
                <label>
                    <input hidden="hidden" name="currentStep" value="${currentStep}"/>
                </label>
            </form>
        </div>

        <c:if test="${empty hideMiddleButton || not hideMiddleButton}">
            <div class="column">
                <c:if test="${empty showEditButton || not showEditButton}">
                    <custom:add-button buttonUrl="${addButtonUrl}"/>
                </c:if>

                <c:if test="${not empty showEditButton && showEditButton}">
                    <custom:edit-button buttonUrl="${addButtonUrl}"/>
                </c:if>
            </div>
        </c:if>

        <div class="column ml-6">
            <button class="button is-link is-outlined is-rounded" id="nextStepButton"
                    <c:if test="${not empty nextButtonDisabled && nextButtonDisabled}">disabled</c:if>>
                <span class="mr-2">
                    <fmt:message key="next.button"/>
                </span>
                <span>
                    <i class="fas fa-chevron-right"></i>
                </span>
            </button>
            <form id="nextStepForm" class="is-hidden" action="<c:url value="/workflow/next"/>" method="post">
                <sec:csrfInput/>
                <label>
                    <input hidden="hidden" name="currentStep" value="${currentStep}"/>
                </label>
            </form>
        </div>

    </div>
    <hr class="mb-1">
</section>
