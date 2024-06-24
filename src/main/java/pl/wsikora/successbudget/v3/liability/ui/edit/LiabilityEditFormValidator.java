package pl.wsikora.successbudget.v3.liability.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractFormValidator;
import pl.wsikora.successbudget.v3.common.type.currency.CurrencyValidator;
import pl.wsikora.successbudget.v3.common.type.description.DescriptionValidator;
import pl.wsikora.successbudget.v3.common.type.money.AmountGreaterThanOrEqualToZeroValidator;
import pl.wsikora.successbudget.v3.common.type.money.AmountGreaterThanZeroValidator;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.name.NameValidator;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;

import java.math.BigDecimal;

import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.isLessThan;
import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.isLessThanOrEqualTo;
import static pl.wsikora.successbudget.v3.liability.ui.edit.LiabilityEditForm.*;


@Service
class LiabilityEditFormValidator extends AbstractFormValidator<LiabilityEditForm> {

	private final LiabilityQuery liabilityQuery;

	LiabilityEditFormValidator(LiabilityQuery liabilityQuery) {

		Assert.notNull(liabilityQuery, "liabilityQuery must not be null");

		this.liabilityQuery = liabilityQuery;
	}

	@Override
	public void validateForm(LiabilityEditForm liabilityEditForm, Errors errors) {

		Assert.notNull(liabilityEditForm, "liabilityEditForm must not be null");
		Assert.notNull(errors, "errors must not be null");

		String name = liabilityEditForm.getName();
		NameValidator.validate(F_NAME, name, errors);
		DescriptionValidator.validate(F_DESCRIPTION, liabilityEditForm.getDescription(), errors);
		NameValidator.validate(F_CREDITOR, liabilityEditForm.getCreditor(), errors);

		BigDecimal totalAmount = liabilityEditForm.getTotalAmount();
		AmountGreaterThanZeroValidator.validate(F_TOTAL_AMOUNT, totalAmount, errors);
		CurrencyValidator.validate(F_TOTAL_CURRENCY, liabilityEditForm.getTotalCurrency(), errors);

		BigDecimal minimumMonthlyAmount = liabilityEditForm.getMinimumMonthlyAmount();
		AmountGreaterThanZeroValidator.validate(F_MINIMUM_MONTHLY_AMOUNT, minimumMonthlyAmount, errors);
		CurrencyValidator.validate(F_MINIMUM_MONTHLY_CURRENCY, liabilityEditForm.getTotalCurrency(), errors);

		BigDecimal alreadyRepaidAmount = liabilityEditForm.getAlreadyRepaidAmount();
		AmountGreaterThanOrEqualToZeroValidator.validate(F_ALREADY_REPAID_AMOUNT, alreadyRepaidAmount, errors);
		CurrencyValidator.validate(F_ALREADY_REPAID_CURRENCY, liabilityEditForm.getTotalCurrency(), errors);

		if (errors.hasErrors()) {

			return;
		}

		if (isLessThanOrEqualTo(totalAmount, minimumMonthlyAmount)) {

			errors.rejectValue(F_MINIMUM_MONTHLY_AMOUNT, E_FIELD_VALUE_IS_TOO_HIGH);
		}

		if (isLessThanOrEqualTo(totalAmount, alreadyRepaidAmount)) {

			errors.rejectValue(F_ALREADY_REPAID_AMOUNT, E_FIELD_VALUE_IS_TOO_HIGH);
		}

		if (isLessThan(totalAmount, minimumMonthlyAmount.add(alreadyRepaidAmount))) {

			errors.rejectValue(F_TOTAL_AMOUNT, E_FIELD_VALUE_IS_TOO_LOW);
		}

		if (liabilityEditForm.hasNoId() && liabilityQuery.existsByNameAndUserId(Name.of(name), UserId.of(liabilityEditForm.getUserId()))) {

			errors.rejectValue(F_NAME, E_FIELD_VALUE_MUST_BE_UNIQUE);
		}
	}
}
