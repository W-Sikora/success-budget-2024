package pl.wsikora.successbudget.v3.income.application.query;

import pl.wsikora.successbudget.v3.common.type.money.Money;


public record IncomeDto(Long id, Money initialAmount) {}
