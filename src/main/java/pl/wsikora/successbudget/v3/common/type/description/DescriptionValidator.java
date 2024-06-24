package pl.wsikora.successbudget.v3.common.type.description;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;


public class DescriptionValidator implements ValidatorErrorCode {

	private DescriptionValidator() {}

	public static void validate(String fieldName, @Nullable String description, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (!isEmpty(description) && !Description.hasValidLength(description)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_CONTAIN_UP_TO_NUMBER_OF_CHARACTERS,
					Description.getMaxLength(), EMPTY);
		}
	}
}
