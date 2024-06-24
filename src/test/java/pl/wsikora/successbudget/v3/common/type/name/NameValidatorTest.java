package pl.wsikora.successbudget.v3.common.type.name;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.common.type.name.Name.F_NAME;


@ExtendWith(MockitoExtension.class)
class NameValidatorTest implements ValidatorErrorCode {

	@Mock private Errors errors;

	private String name;

	@Test
	void shouldDetectNullName() {

		// given
		name = null;

		// when
		NameValidator.validate(F_NAME, name, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_MUST_NOT_BE_EMPTY);
	}

	@Test
	void shouldDetectEmptyName() {

		// given
		name = EMPTY;

		// when
		NameValidator.validate(F_NAME, name, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_MUST_NOT_BE_EMPTY);
	}

	@Test
	void shouldDetectBlankName() {

		// given
		name = SPACE;

		// when
		NameValidator.validate(F_NAME, name, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_MUST_NOT_BE_EMPTY);
	}

	@Test
	void shouldDetectTooShortName() {

		// given
		name = randomAlphabetic(Name.MINIMUM_LENGTH - 1);

		// when
		NameValidator.validate(F_NAME, name, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS, Name.getLengthRange(), EMPTY);
	}

	@Test
	void shouldDetectTooLongName() {

		// given
		name = randomAlphabetic(Name.MAXIMUM_LENGTH + 1);

		// when
		NameValidator.validate(F_NAME, name, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS,
				Name.getLengthRange(), EMPTY);
	}
}