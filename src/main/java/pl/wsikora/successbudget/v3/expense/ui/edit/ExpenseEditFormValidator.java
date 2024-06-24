package pl.wsikora.successbudget.v3.expense.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractFormValidator;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryIdValidator;
import pl.wsikora.successbudget.v3.common.type.currency.CurrencyValidator;
import pl.wsikora.successbudget.v3.common.type.date.DateValidator;
import pl.wsikora.successbudget.v3.common.type.description.DescriptionValidator;
import pl.wsikora.successbudget.v3.common.type.money.AmountGreaterThanZeroValidator;
import pl.wsikora.successbudget.v3.common.type.name.NameValidator;

import static pl.wsikora.successbudget.v3.expense.ui.edit.ExpenseEditForm.*;


@Service
class ExpenseEditFormValidator extends AbstractFormValidator<ExpenseEditForm> {

	@Override
	public void validateForm(ExpenseEditForm expenseEditForm, Errors errors) {

		Assert.notNull(expenseEditForm, "expenseEditForm must not be null");
		Assert.notNull(errors, "errors must not be null");

		NameValidator.validate(F_NAME, expenseEditForm.getName(), errors);
		DescriptionValidator.validate(F_DESCRIPTION, expenseEditForm.getDescription(), errors);
		DateValidator.validate(F_DATE, expenseEditForm.getDate(), errors);
		AmountGreaterThanZeroValidator.validate(F_AMOUNT, expenseEditForm.getAmount(), errors);
		CurrencyValidator.validate(F_CURRENCY, expenseEditForm.getCurrency(), errors);
		CategoryIdValidator.validate(F_CATEGORY_ID, expenseEditForm.getCategoryId(), errors);
	}
}
