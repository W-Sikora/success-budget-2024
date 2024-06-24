package pl.wsikora.successbudget.v3.common.type.date;

import jakarta.annotation.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.springframework.util.StringUtils.hasText;


public class DateValidator implements ValidatorErrorCode {

	private DateValidator() {}

	public static void validate(String fieldName, @Nullable String date, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (!hasText(date)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
		}
		else if (!Date.hasValidFormat(date)) {

			errors.rejectValue(fieldName, E_DATE_MUST_HAVE_FORMAT);
		}
	}
}
