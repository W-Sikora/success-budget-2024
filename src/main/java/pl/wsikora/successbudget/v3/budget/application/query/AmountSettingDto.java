package pl.wsikora.successbudget.v3.budget.application.query;

import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;

import java.math.BigDecimal;
import java.util.List;


public record AmountSettingDto(
	List<CategoryDto> mustCategories,
	List<BigDecimal> minimumAllowableMustExpenseAmounts,
	List<LiabilityDto> liabilities,
	List<BigDecimal> minimumAllowableLiabilityAmounts,
	List<BigDecimal> maximumAllowableLiabilityAmounts,
	List<CategoryDto> shouldCategories,
	BigDecimal maximumAllowableAmountOfShouldExpense,
	List<CategoryDto> couldCategories,
	BigDecimal maximumAllowableAmountOfCouldExpense,
	List<CategoryDto> wouldCategories,
	BigDecimal maximumAllowableAmountOfWouldExpense,
	BigDecimal disposableIncome,
	Currency currency,
	boolean balanced,
	boolean minimumAllowableLiabilityAmountsTooHigh,
	boolean minimumAllowableMustExpenseAmountsTooHigh,
	boolean maximumAllowableAmountOfShouldExpenseTooHigh,
	boolean maximumAllowableAmountOfCouldExpenseTooHigh,
	boolean maximumAllowableAmountOfWouldExpenseTooHigh) {}
