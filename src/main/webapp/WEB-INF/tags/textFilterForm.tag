<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="name" required="false"%>

<div class="has-text-centered mb-6">
    <form class="is-filter js-keyword-filter" method="get">
        <sec:csrfMetaTags/>

        <label class="label has-text-weight-normal" for="keyword">
            ${name}
        </label>

        <div class="field is-grouped">

            <p class="control is-expanded">
                <input class="input is-small" type="text" name="name" id="keyword">
            </p>

            <p class="control">
                <button class="button is-small" type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </p>

            <p class="control">
                <button class="button is-small js-clear">
                    <i class="fas fa-eraser"></i>
                </button>
            </p>
        </div>
    </form>
</div>