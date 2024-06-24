<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="${cookie['language'].value}">
<%@ include file="../imports/head-imports.jsp" %>
<body>
<c:set var="sign" value="${amountSetting.currency().sign}"/>
<div>
    <div class="page-container">
        <div class="hero is-fullheight is-fullheight-with-navbar">

            <custom:navbar/>

            <div class="hero-body">
                <div class="container main-section">
                    <div class="columns is-centered">
                        <div class="column">
                            <div class="has-text-centered mb-5">
                                <h1 class="is-size-3">
                                    <fmt:message key="amount.setting.page.title"/>
                                </h1>
                            </div>
                            <div class="notification">
                                <div class="content">
                                    <strong>
                                        <fmt:message key="info"/>
                                    </strong>

                                    <%@include file="amount-setting-hints.jsp" %>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-11">

                            <custom:page-menu/>

                            <section class="section has-small-padding">

                                <c:if test="${not empty amountSetting && amountSetting.balanced()}">
                                    <p class="subtitle has-text-success">
                                        <fmt:message key="budget.is.plannable"/>
                                        <span class="ml-2">
                                           <i class="fas fa-check"></i>
                                        </span>
                                    </p>
                                </c:if>
                                <c:if test="${not empty amountSetting && !amountSetting.balanced()}">
                                    <p class="subtitle has-text-danger">
                                        <fmt:message key="budget.is.not.plannable"/>
                                        <span class="ml-2">
                                           <i class="fas fa-exclamation"></i>
                                        </span>
                                    </p>
                                    <div class="content">
                                        <ul>
                                            <c:if test="${amountSetting.minimumAllowableLiabilityAmountsTooHigh()}">
                                                <li>
                                                    <fmt:message key="amount.setting.minimum.allowable.liability.amounts.too.high"/>
                                                </li>
                                            </c:if>
                                            <c:if test="${amountSetting.minimumAllowableMustExpenseAmountsTooHigh()}">
                                                <li>
                                                    <fmt:message key="amount.setting.minimum.allowable.must.expense.amounts.too.high"/>
                                                </li>
                                            </c:if>
                                            <c:if test="${amountSetting.maximumAllowableAmountOfShouldExpenseTooHigh()}">
                                                <li>
                                                    <fmt:message key="amount.setting.maximum.allowable.amount.of.should.expense.too.high"/>
                                                </li>
                                            </c:if>
                                            <c:if test="${amountSetting.maximumAllowableAmountOfCouldExpenseTooHigh()}">
                                                <li>
                                                    <fmt:message key="amount.setting.maximum.allowable.amount.of.could.expense.too.high"/>
                                                </li>
                                            </c:if>
                                            <c:if test="${amountSetting.maximumAllowableAmountOfWouldExpenseTooHigh()}">
                                                <li>
                                                    <fmt:message key="amount.setting.maximum.allowable.amount.of.would.expense.too.high"/>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </c:if>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="income.owned"/>:
                                    <strong>
                                        <c:if test="${not empty amountSetting.disposableIncome()}">
                                            <fmt:formatNumber value="${amountSetting.disposableIncome()}" type="currency" currencySymbol="${sign}"/>
                                        </c:if>
                                    </strong>
                                </p>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="liability.page.title"/>:
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="name"/></th>
                                                <th class="has-text-right"><fmt:message key="liability.minimum.monthly.amount"/></th>
                                                <th class="has-text-right"><fmt:message key="liability.repaid.amount"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="${amountSetting.liabilities().size() - 1}" var="i" varStatus="index">
                                                <tr>
                                                    <td>
                                                        ${index.count}
                                                    </td>
                                                    <td>
                                                        ${amountSetting.liabilities()[i].name().name}
                                                    </td>
                                                    <th class="has-text-right">
                                                        <fmt:formatNumber value="${amountSetting.minimumAllowableLiabilityAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                    <td class="has-text-right">
                                                        <fmt:formatNumber value="${amountSetting.maximumAllowableLiabilityAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="category.group"/> "must have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                                <th class="has-text-right"><fmt:message key="minimum.destination.amount"/> </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="${amountSetting.mustCategories().size() - 1}" var="i" varStatus="index">
                                                <tr>
                                                    <td>
                                                        ${index.count}
                                                    </td>
                                                    <td>
                                                        ${amountSetting.mustCategories()[i].name().name}
                                                    </td>
                                                    <th class="has-text-right">
                                                        <fmt:formatNumber value="${amountSetting.minimumAllowableMustExpenseAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="category.group"/> "should have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 30%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="${amountSetting.shouldCategories().size() - 1}" var="i" varStatus="index">
                                                <tr>
                                                    <td>
                                                        ${index.count}
                                                    </td>
                                                    <td>
                                                        ${amountSetting.shouldCategories()[i].name().name}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tfoot>
                                                <tr>
                                                    <th>
                                                        <fmt:message key="maximum.sum.of.amounts"/>
                                                    </th>
                                                    <th>
                                                        <fmt:formatNumber value="${amountSetting.maximumAllowableAmountOfShouldExpense()}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                </tr>
                                            </tfoot>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="category.group"/> "could have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 30%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="${amountSetting.couldCategories().size() - 1}" var="i" varStatus="index">
                                                <tr>
                                                    <td>
                                                        ${index.count}
                                                    </td>
                                                    <td>
                                                        ${amountSetting.couldCategories()[i].name().name}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tfoot>
                                                <tr>
                                                    <th>
                                                        <fmt:message key="maximum.sum.of.amounts"/>
                                                    </th>
                                                    <th>
                                                        <fmt:formatNumber value="${amountSetting.maximumAllowableAmountOfCouldExpense()}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                </tr>
                                            </tfoot>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <hr>
                            <section class="section has-small-padding">
                                <p class="subtitle">
                                    <fmt:message key="category.group"/> "would have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 30%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach begin="0" end="${amountSetting.wouldCategories().size() - 1}" var="i" varStatus="index">
                                                <tr>
                                                    <td>
                                                        ${index.count}
                                                    </td>
                                                    <td>
                                                        ${amountSetting.wouldCategories()[i].name().name}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tfoot>
                                                <tr>
                                                    <th>
                                                        <fmt:message key="maximum.sum.of.amounts"/>
                                                    </th>
                                                    <th>
                                                        <fmt:formatNumber value="${amountSetting.maximumAllowableAmountOfWouldExpense()}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                </tr>
                                            </tfoot>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <custom:footer/>

</div>
</body>
</html>
