<section class="section has-medium-padding">
    <p class="subtitle">
        <fmt:message key="income.owned"/>:
        <strong>
            <c:if test="${not empty incomeDto}">
                <fmt:formatNumber value="${incomeDto.initialAmount().amount}" type="currency" currencySymbol="${incomeDto.initialAmount().currency.sign}"/>
            </c:if>
            <c:if test="${empty incomeDto}">
                <fmt:formatNumber value="0" type="currency" currencySymbol="${userCurrency.sign}"/>
            </c:if>
        </strong>
    </p>
</section>