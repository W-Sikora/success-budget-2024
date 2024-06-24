<c:if test="${empty categoryGroup}">

    <custom:no-elements/>

</c:if>

<c:if test="${not empty categoryGroup}">
    <section class="section">
        <div class="container">
            <form id="rankingForm" action="<c:url value='/category-preference/${name}'/>" method="post">
                <sec:csrfInput/>
                <table class="table is-fullwidth is-striped is-hoverable">
                    <thead class="has-text-centered">
                    <tr>
                        <th><fmt:message key="no"/></th>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="operations"/></th>
                    </tr>
                    </thead>
                    <tbody id="tableBody">
                    <c:forEach var="category" items="${categoryGroup}" varStatus="i">
                        <tr data-id="${category.id()}">
                            <td class="no">
                                    ${i.count}
                            </td>
                            <td>
                                    ${category.name().name}
                            </td>
                            <td>
                                <div class="buttons is-centered">
                                    <button type="button"
                                            class="button is-small is-outlined is-rounded move-up">
                                        <i class="fas fa-chevron-up"></i>
                                    </button>
                                    <button type="button"
                                            class="button is-small is-outlined is-rounded move-down">
                                        <i class="fas fa-chevron-down"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="orderedIds" id="orderedIds">
            </form>
        </div>
    </section>
</c:if>

<script>
    document.addEventListener('DOMContentLoaded', () => {

        const nextButton = document.getElementById("nextStepButton");
        const rankingForm = document.getElementById("rankingForm");

        if (nextButton && rankingForm) {

            nextButton.addEventListener("click", () => {

                rankingForm.submit();
            });
        }
    });
</script>

