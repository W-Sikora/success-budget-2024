<section>
    <div class="has-text-centered mb-6">
        <form class="is-filter">
            <sec:csrfMetaTags/>
            <custom:name-filter/>
            <custom:priority-filter/>
            <custom:filter-buttons/>
        </form>
    </div>

    <c:if test="${empty page || page.isEmpty()}">

        <custom:no-elements/>

    </c:if>
    <c:if test="${not empty page && not page.isEmpty()}">
        <div class="table-container">
            <table class="table is-fullwidth is-hoverable">
                <thead>
                <tr>
                    <th><fmt:message key="no"/></th>
                    <th><fmt:message key="name"/></th>
                    <th class="has-text-centered"><fmt:message key="priority"/></th>
                    <th class="has-text-centered"><fmt:message key="operations"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${page.toList()}" varStatus="i">
                    <tr>
                        <td>
                            ${i.count}
                        </td>
                        <td>
                            ${row.name().name}
                        </td>
                        <td class="has-text-centered">
                            <custom:priority ordinal="${row.priority().ordinal()}"/>
                        </td>
                        <custom:page-row-options editButtonUrl="/category/edit?id=${row.id()}"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <custom:pagination page="${page}"/>

        </div>
    </c:if>
</section>