<section class="section has-medium-padding">
    <p class="subtitle">
        <fmt:message key="liability.liabilities.owned"/>:
        <strong>
            <c:if test="${not empty liabilityCollectiveDto}">
                <fmt:formatNumber value="${liabilityCollectiveDto.total().amount}" type="currency" currencySymbol="${liabilityCollectiveDto.total().currency.sign}"/>
            </c:if>
            <c:if test="${empty liabilityCollectiveDto}">
                <fmt:formatNumber value="0" type="currency" currencySymbol="${userCurrency.sign}"/>
            </c:if>
        </strong>
    </p>
    <p class="subtitle">
        <fmt:message key="liability.liabilities.paid"/>:
        <strong>
            <c:if test="${not empty liabilityCollectiveDto}">
                <fmt:formatNumber value="${liabilityCollectiveDto.totalRepaid().amount}" type="currency" currencySymbol="${liabilityCollectiveDto.totalRepaid().currency.sign}"/>
            </c:if>
            <c:if test="${empty liabilityCollectiveDto}">
                <fmt:formatNumber value="0" type="currency" currencySymbol="${userCurrency.sign}"/>
            </c:if>
        </strong>
    </p>
    <p class="subtitle">
        <fmt:message key="liability.liabilities.remaining.to.be.repaid"/>:
        <strong>
            <c:if test="${not empty liabilityCollectiveDto}">
                <fmt:formatNumber value="${liabilityCollectiveDto.remainingToBeRepaid().amount}" type="currency" currencySymbol="${liabilityCollectiveDto.remainingToBeRepaid().currency.sign}"/>
            </c:if>
            <c:if test="${empty liabilityCollectiveDto}">
                <fmt:formatNumber value="0" type="currency" currencySymbol="${userCurrency.sign}"/>
            </c:if>
        </strong>
    </p>
</section>