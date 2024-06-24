package pl.wsikora.successbudget.v3.expense.application.command;

import pl.wsikora.successbudget.v3.common.abstraction.application.command.Attributable;

import java.math.BigDecimal;


public interface ExpenseAttributes extends Attributable {

	String getName();

	String getDescription();

	String getDate();

	BigDecimal getAmount();

	Integer getCurrency();

	Long getCategoryId();
}
