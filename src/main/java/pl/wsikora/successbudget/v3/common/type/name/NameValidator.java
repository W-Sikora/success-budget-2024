package pl.wsikora.successbudget.v3.common.type.name;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.util.StringUtils.hasText;


public class NameValidator implements ValidatorErrorCode {

	private NameValidator() {}

	public static void validate(String fieldName, @Nullable String name, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (!hasText(name)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
		}
		else if (!Name.hasValidLength(name)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS, Name.getLengthRange(), EMPTY);
		}
	}
}
