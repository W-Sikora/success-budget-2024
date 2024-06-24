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
                                    <fmt:message key="budget.page.title"/>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <div class="columns is-centered">
                        <div class="column is-11">

                            <custom:page-menu/>

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
                                    <fmt:message key="saving.owned"/>:
                                    <strong>
                                        <c:if test="${not empty budgetSolution.savings()}">
                                            <fmt:formatNumber value="${budgetSolution.savings()}" type="currency" currencySymbol="${sign}"/>
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
                                                <th class="has-text-right"><fmt:message key="amount"/></th>
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
                                                        <fmt:formatNumber value="${budgetSolution.liabilitiesAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
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
                                                <th class="has-text-right"><fmt:message key="amount"/> </th>
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
                                                        <fmt:formatNumber value="${budgetSolution.mustHaveAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
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
                                                <th style="width: 10%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                                <th class="has-text-right"><fmt:message key="amount"/> </th>
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
                                                    <th class="has-text-right">
                                                        <fmt:formatNumber value="${budgetSolution.shouldHaveAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
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
                                    <fmt:message key="category.group"/> "could have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                                <th class="has-text-right"><fmt:message key="amount"/> </th>
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
                                                    <th class="has-text-right">
                                                        <fmt:formatNumber value="${budgetSolution.couldHaveAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
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
                                    <fmt:message key="category.group"/> "would have":
                                </p>
                                <div class="table-container">
                                    <table class="table is-fullwidth is-hoverable">
                                        <thead>
                                            <tr>
                                                <th style="width: 10%">
                                                    <fmt:message key="no"/>
                                                </th>
                                                <th><fmt:message key="category"/></th>
                                                <th class="has-text-right"><fmt:message key="amount"/> </th>
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
                                                    <th class="has-text-right">
                                                        <fmt:formatNumber value="${budgetSolution.wouldHaveAmounts()[i]}" type="currency" currencySymbol="${sign}"/>
                                                    </th>
                                                </tr>
                                            </c:forEach>
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
