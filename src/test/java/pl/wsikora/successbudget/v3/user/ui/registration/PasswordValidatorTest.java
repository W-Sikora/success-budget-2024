package pl.wsikora.successbudget.v3.user.ui.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;
import pl.wsikora.successbudget.v3.user.domain.Password;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.user.domain.Password.MAXIMUM_LENGTH;
import static pl.wsikora.successbudget.v3.user.domain.Password.MINIMUM_LENGTH;
import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationForm.F_PASSWORD;


@ExtendWith(MockitoExtension.class)
class PasswordValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    private PasswordValidator validator;
    private String password;

    @Test
    void shouldDetectNullPassword() {

        // given
        password = null;

        // when
        PasswordValidator.validate(F_PASSWORD, password, errors);

        // then
        verify(errors).rejectValue(F_PASSWORD, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectEmptyPassword() {

        // given
        password = EMPTY;

        // when
        PasswordValidator.validate(F_PASSWORD, password, errors);

        // then
        verify(errors).rejectValue(F_PASSWORD, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectBlankPassword() {

        // given
        password = SPACE;

        // when
        PasswordValidator.validate(F_PASSWORD, password, errors);

        // then
        verify(errors).rejectValue(F_PASSWORD, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectTooShortPassword() {

        // given
        password = randomAlphabetic(MINIMUM_LENGTH - 1);

        // when
        PasswordValidator.validate(F_PASSWORD, password, errors);

        // then
        verify(errors).rejectValue(F_PASSWORD, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS,
            Password.getLengthRange(), EMPTY);
    }

    @Test
    void shouldDetectTooLongPassword() {

        // given
        password = randomAlphabetic(MAXIMUM_LENGTH + 1);

        // when
        PasswordValidator.validate(F_PASSWORD, password, errors);

        // then
        verify(errors).rejectValue(F_PASSWORD, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS,
            Password.getLengthRange(), EMPTY);
    }
}
