package pl.wsikora.successbudget.v3.common.type.date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.common.type.date.Date.F_DATE;


@ExtendWith(MockitoExtension.class)
class DateInFutureValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    private String value;

    @Test
    void shouldDetectNullDate() {

        // given
        value = null;

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectEmptyDate() {

        // given
        value = EMPTY;

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectBlankDate() {

        // given
        value = SPACE;

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectIncompleteDate() {

        // given
        value = "2023-02";

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_DATE_MUST_HAVE_FORMAT);
    }

    @Test
    void shouldDetectDateOfInvalidFormat() {

        // given
        value = "02/02/2023";

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_DATE_MUST_HAVE_FORMAT);
    }

    @Test
    void shouldDetectPastDate() {

        // given
        value = Date.YESTERDAY.toForm();

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_DATE_MUST_BE_IN_FUTURE);
    }

    @Test
    void shouldDetectTodayDate() {

        // given
        value = Date.TODAY.toForm();

        // when
        DateInFutureValidator.validate(F_DATE, value, errors);

        // then
        verify(errors).rejectValue(F_DATE, E_DATE_MUST_BE_IN_FUTURE);
    }
}
