package pl.wsikora.successbudget.v3.common.type.money;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;


public class AmountGreaterThanZeroValidator implements ValidatorErrorCode {

	private AmountGreaterThanZeroValidator() {}

	public static void validate(String fieldName, @Nullable BigDecimal amount, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (isNull(amount)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
		}
		else if (!Money.hasValidValueGreaterThanZero(amount)) {

			errors.rejectValue(fieldName, E_VALUE_MUST_BE_WITHIN_ALLOWED_RANGE,
					Money.getRangeGreaterThanZero(), EMPTY);
		}
	}
}
