package pl.wsikora.successbudget.v3.user.ui.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;
import pl.wsikora.successbudget.v3.user.application.query.UserQuery;
import pl.wsikora.successbudget.v3.user.domain.Password;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationForm.F_REPEATED_PASSWORD;
import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationForm.F_USERNAME;
import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationFormValidator.E_REPEATED_PASSWORD_IS_DIFFERENT;
import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationFormValidator.E_USERNAME_MUST_BE_UNIQUE;


@ExtendWith(MockitoExtension.class)
class RegistrationFormValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;
    @Mock private UserQuery userQuery;

    private RegistrationForm form;
    private RegistrationFormValidator registrationFormValidator;

    @BeforeEach
    void setUp() {

        String random = randomAlphabetic(Password.MAXIMUM_LENGTH);

        form = new RegistrationForm();
        form.setUsername(random);
        form.setPassword(random);

        registrationFormValidator = new RegistrationFormValidator(userQuery);
    }

    @Test
    void shouldDetectDifferentPassword() {

        // given
        form.setRepeatedPassword(randomAlphabetic(Password.MAXIMUM_LENGTH));

        // when
        registrationFormValidator.validateForm(form, errors);

        // then
        verify(errors).rejectValue(F_REPEATED_PASSWORD, E_REPEATED_PASSWORD_IS_DIFFERENT);
    }

    @Test
    void shouldDetectNotUniqueUsername() {

        // given
        form.setUsername(randomAlphabetic(Name.MAXIMUM_LENGTH));

        given(userQuery.existsByUsername(any())).willReturn(true);

        // when
        registrationFormValidator.validateForm(form, errors);

        // then
        verify(errors).rejectValue(F_USERNAME, E_USERNAME_MUST_BE_UNIQUE);
    }
}
