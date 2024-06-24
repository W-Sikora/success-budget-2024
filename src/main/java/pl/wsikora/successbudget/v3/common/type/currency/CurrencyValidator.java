package pl.wsikora.successbudget.v3.common.type.currency;

import jakarta.annotation.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static java.util.Objects.isNull;


public class CurrencyValidator implements ValidatorErrorCode {

	private CurrencyValidator() {}

	public static void validate(String fieldName, @Nullable Integer currency, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (isNull(currency)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
		}
		else if (!Currency.hasValidOrdinalRange(currency)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_CONTAIN_VALID_VALUE);
		}
	}
}
