<section>
    <div class="has-text-centered mb-6">
        <form class="is-filter">
            <sec:csrfMetaTags/>
            <custom:name-filter/>
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
                    <th class="has-text-right"><fmt:message key="liability.minimum.monthly.amount"/></th>
                    <th class="has-text-right"><fmt:message key="liability.total.repaid"/></th>
                    <th class="has-text-right"><fmt:message key="liability.repaid.amount"/></th>
                    <th class="has-text-right"><fmt:message key="liability.total.amount"/></th>
                    <th class="has-text-right">%</th>
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
                        <td class="has-text-right">
                            <fmt:formatNumber value="${row.minimumMonthly().amount}" type="currency"
                                              currencySymbol="${row.minimumMonthly().currency.sign}"/>
                        </td>
                        <td class="has-text-right">
                            <fmt:formatNumber value="${row.totalRepaid().amount}" type="currency"
                                              currencySymbol="${row.totalRepaid().currency.sign}"/>
                        </td>
                        <td class="has-text-right">
                            <fmt:formatNumber value="${row.remainingToBeRepaid().amount}" type="currency"
                                              currencySymbol="${row.remainingToBeRepaid().currency.sign}"/>
                        </td>
                        <td class="has-text-right">
                            <fmt:formatNumber value="${row.total().amount}" type="currency"
                                              currencySymbol="${row.total().currency.sign}"/>
                        </td>
                        <td class="has-text-right">
                            ${row.percentage().roundToNearestPercent()}
                        </td>
                        <custom:page-row-options editButtonUrl="/liability/edit?id=${row.id()}"/>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <custom:pagination page="${page}"/>

        </div>
    </c:if>
</section>