package pl.wsikora.successbudget.v3.income.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractFormValidator;
import pl.wsikora.successbudget.v3.common.type.currency.CurrencyValidator;
import pl.wsikora.successbudget.v3.common.type.money.AmountGreaterThanZeroValidator;

import static pl.wsikora.successbudget.v3.income.ui.edit.IncomeEditForm.F_INITIAL_AMOUNT;
import static pl.wsikora.successbudget.v3.income.ui.edit.IncomeEditForm.F_INITIAL_CURRENCY;


@Service
class IncomeEditFormValidator extends AbstractFormValidator<IncomeEditForm> {

	@Override
	public void validateForm(IncomeEditForm incomeEditForm, Errors errors) {

		Assert.notNull(incomeEditForm, "incomeEditForm must not be null");
		Assert.notNull(errors, "errors must not be null");

		CurrencyValidator.validate(F_INITIAL_CURRENCY, incomeEditForm.getInitialCurrency(), errors);
		AmountGreaterThanZeroValidator.validate(F_INITIAL_AMOUNT, incomeEditForm.getInitialAmount(), errors);
	}
}
