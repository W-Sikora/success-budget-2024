package pl.wsikora.successbudget.v3.category.ui.edit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static pl.wsikora.successbudget.v3.category.domain.Priority.F_PRIORITY;


@ExtendWith(MockitoExtension.class)
class PriorityValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    private Integer priority;

    @Test
    void shouldDetectNullValue() {

        // given
        priority = null;

        // when
        PriorityValidator.validate(F_PRIORITY, priority, errors);

        // then
        verify(errors).rejectValue(F_PRIORITY, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectTooLowValue() {

        // given
        priority = -1;

        // when
        PriorityValidator.validate(F_PRIORITY, priority, errors);

        // then
        verify(errors).rejectValue(F_PRIORITY, E_FIELD_MUST_CONTAIN_VALID_VALUE);
    }

    @Test
    void shouldAcceptValidValue() {

        // given
        priority = 0;

        // when
        PriorityValidator.validate(F_PRIORITY, priority, errors);

        // then
        verifyNoInteractions(errors);
    }

    @Test
    void shouldDetectTooHighValue() {

        // given
        priority = Priority.values().length;

        // when
        PriorityValidator.validate(F_PRIORITY, priority, errors);

        // then
        verify(errors).rejectValue(F_PRIORITY, E_FIELD_MUST_CONTAIN_VALID_VALUE);
    }
}
