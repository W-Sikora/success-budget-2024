package pl.wsikora.successbudget.v3.category.ui.edit;

import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static java.util.Objects.isNull;


class PriorityValidator implements ValidatorErrorCode {

    private PriorityValidator() {}

    public static void validate(String fieldName, @Nullable Integer priority, Errors errors) {

        if (isNull(priority)) {

            errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
        }
        else if (!Priority.hasValidOrdinalRange(priority)) {

            errors.rejectValue(fieldName, E_FIELD_MUST_CONTAIN_VALID_VALUE);
        }
    }
}
