package pl.wsikora.successbudget.v3.common.type.description;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static pl.wsikora.successbudget.v3.common.type.description.Description.F_DESCRIPTION;
import static pl.wsikora.successbudget.v3.common.type.description.Description.MAXIMUM_LENGTH;


@ExtendWith(MockitoExtension.class)
class DescriptionValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    private String description;

    @Test
    void shouldAcceptEmptyDescription() {

        // given
        description = EMPTY;

        // when
        DescriptionValidator.validate(F_DESCRIPTION, description, errors);

        // then
        verifyNoInteractions(errors);
    }

    @Test
    void shouldDetectTooLongDescription() {

        // given
        description = randomAlphabetic(MAXIMUM_LENGTH + 1);

        // when
        DescriptionValidator.validate(F_DESCRIPTION, description, errors);

        // then
        verify(errors).rejectValue(F_DESCRIPTION, E_FIELD_MUST_CONTAIN_UP_TO_NUMBER_OF_CHARACTERS,
            Description.getMaxLength(), EMPTY);
    }
}
