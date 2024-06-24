package pl.wsikora.successbudget.v3.common.type.money;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.common.type.money.Money.F_AMOUNT;


@ExtendWith(MockitoExtension.class)
class AmountGreaterThanOrEqualToZeroValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    private BigDecimal amount;

    @Test
    void shouldDetectNullAmount() {

        // given
        amount = null;

        // when
        AmountGreaterThanOrEqualToZeroValidator.validate(F_AMOUNT, amount, errors);

        // then
        verify(errors).rejectValue(F_AMOUNT, E_FIELD_MUST_NOT_BE_EMPTY);
    }

    @Test
    void shouldDetectTooLowAmount() {

        // given
        amount = Money.MINIMUM_VALUE.subtract(BigDecimal.ONE);

        // when
        AmountGreaterThanOrEqualToZeroValidator.validate(F_AMOUNT, amount, errors);

        // then
        verify(errors).rejectValue(F_AMOUNT, E_VALUE_MUST_BE_WITHIN_ALLOWED_RANGE,
            Money.getRange(), EMPTY);
    }

    @Test
    void shouldDetectTooHighAmount() {

        // given
        amount = Money.MAXIMUM_VALUE.add(BigDecimal.ONE);

        // when
        AmountGreaterThanOrEqualToZeroValidator.validate(F_AMOUNT, amount, errors);

        // then
        verify(errors).rejectValue(F_AMOUNT, E_VALUE_MUST_BE_WITHIN_ALLOWED_RANGE,
            Money.getRange(), EMPTY);
    }
}
