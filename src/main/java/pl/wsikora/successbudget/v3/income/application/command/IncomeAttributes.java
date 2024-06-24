package pl.wsikora.successbudget.v3.income.application.command;

import pl.wsikora.successbudget.v3.common.abstraction.application.command.Attributable;

import java.math.BigDecimal;


public interface IncomeAttributes extends Attributable {

	Integer getInitialCurrency();

	BigDecimal getInitialAmount();
}
