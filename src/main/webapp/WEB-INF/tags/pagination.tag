<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true" %>

<div class="field has-addons has-addons-centered my-6 js-pagination"
     data-current-page="${page.number}" data-last-page="${page.totalPages}">

    <p class="control">
        <button class="button js-previous-page-button" <c:if test="${page.number == 0}">disabled</c:if>>
            <span class="icon is-small">
                <i class="fas fa-chevron-left"></i>
            </span>
        </button>
    </p>

    <p class="control">
        <a class="button" id="paginator" data-current="${page.number}" data-last="${page.totalPages}">
            <span class="has-text-weight-bold">
                ${page.number + 1}
            </span>

            <span>&nbsp;/&nbsp;</span>

            <span>
                ${page.totalPages}
            </span>
        </a>
    </p>

    <p>
        <button class="button js-next-page-button" <c:if test="${page.number == page.totalPages - 1}">disabled</c:if>>
            <span class="icon is-small">
                <i class="fas fa-chevron-right"></i>
            </span>
        </button>
    </p>

    <div class="ml-5 select">
        <select id="sizeSelect">
            <option value="15">15</option>
            <option value="30">30</option>
            <option value="50">50</option>
            <option value="100">100</option>
        </select>
    </div>

    <form class="is-hidden js-pagination-form">
        <sec:csrfMetaTags/>
        <label>
            <input class="js-parameter-page" name="page">
            <input class="js-parameter-size" name="size">
        </label>
    </form>
</div>
