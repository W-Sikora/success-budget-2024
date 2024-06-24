package pl.wsikora.successbudget.v3.common.type.categoryid;

import jakarta.annotation.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static java.util.Objects.isNull;


public class CategoryIdValidator implements ValidatorErrorCode {

	private CategoryIdValidator() {}

	public static void validate(String fieldName, @Nullable Long categoryId, Errors errors) {

		Assert.hasText(fieldName, "fieldName must not be empty");
		Assert.notNull(errors, "errors must not be null");

		if (isNull(categoryId)) {

			errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
		}
	}
}
