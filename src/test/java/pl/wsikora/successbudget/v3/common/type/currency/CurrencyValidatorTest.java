package pl.wsikora.successbudget.v3.common.type.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static pl.wsikora.successbudget.v3.common.type.currency.Currency.F_CURRENCY;


@ExtendWith(MockitoExtension.class)
class CurrencyValidatorTest implements ValidatorErrorCode {

	@Mock private Errors errors;

	private Integer currency;

	@Test
	void shouldDetectNullValue() {

		// given
		currency = null;

		// when
		CurrencyValidator.validate(F_CURRENCY, currency, errors);

		// then
		verify(errors).rejectValue(F_CURRENCY, E_FIELD_MUST_NOT_BE_EMPTY);
	}

	@Test
	void shouldDetectTooLowValue() {

		// given
		currency = -1;

		// when
		CurrencyValidator.validate(F_CURRENCY, currency, errors);

		// then
		verify(errors).rejectValue(F_CURRENCY, E_FIELD_MUST_CONTAIN_VALID_VALUE);
	}

	@Test
	void shouldAcceptValidValue() {

		// given
		currency = 0;

		// when
		CurrencyValidator.validate(F_CURRENCY, currency, errors);

		// then
		verifyNoInteractions(errors);
	}

	@Test
	void shouldDetectTooHighValue() {

		// given
		currency = Currency.values().length;

		// when
		CurrencyValidator.validate(F_CURRENCY, currency, errors);

		// then
		verify(errors).rejectValue(F_CURRENCY, E_FIELD_MUST_CONTAIN_VALID_VALUE);
	}
}
