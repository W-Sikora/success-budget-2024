<section>
    <div class="has-text-centered mb-6">
        <form class="is-filter">
            <sec:csrfMetaTags/>
            <custom:name-filter/>
            <custom:date-filter/>
            <custom:category-filter/>
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
                    <th class="has-text-centered"><fmt:message key="date"/></th>
                    <th><fmt:message key="category"/></th>
                    <th class="has-text-right"><fmt:message key="expense.amount"/> </th>
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
                            ${row.date().asString()}
                        </td>
                        <td>
                            ${row.categoryName().name}
                        </td>
                        <td class="has-text-right">
                            <fmt:formatNumber value="${row.amount().amount}" type="currency"
                                              currencySymbol="${row.amount().currency.sign}"/>
                        </td>
                        <custom:page-row-options editButtonUrl="/expense/edit?id=${row.id()}"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <custom:pagination page="${page}"/>

        </div>
    </c:if>
</section>