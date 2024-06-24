package pl.wsikora.successbudget.v3.user.ui.registration;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;
import pl.wsikora.successbudget.v3.user.domain.Password;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.util.StringUtils.hasText;


class PasswordValidator implements ValidatorErrorCode {

    private PasswordValidator() {}

    public static void validate(String fieldName, @Nullable String password, Errors errors) {

        Assert.hasText(fieldName, "fieldName must not be empty");
        Assert.notNull(errors, "errors must not be null");

        if (!hasText(password)) {

            errors.rejectValue(fieldName, E_FIELD_MUST_NOT_BE_EMPTY);
        }
        else if (!Password.hasValidLength(password)) {

            errors.rejectValue(fieldName, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS,
                Password.getLengthRange(), EMPTY);
        }
    }
}
