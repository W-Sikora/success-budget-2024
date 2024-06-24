package pl.wsikora.successbudget.v3.liability.ui.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;

import java.math.BigDecimal;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.liability.ui.edit.LiabilityEditForm.*;


@ExtendWith(MockitoExtension.class)
class LiabilityEditFormValidatorTest implements ValidatorErrorCode {

	private static final String RANDOM_NAME = randomAlphabetic(Name.MINIMUM_LENGTH);
	private static final Name NAME = Name.of(RANDOM_NAME);
	private static final Long RANDOM_USER_ID = nextLong();
	private static final UserId USER_ID = UserId.of(RANDOM_USER_ID);

	@Mock private Errors errors;
	@Mock private LiabilityQuery liabilityQuery;

	private LiabilityEditFormValidator validator;
	private LiabilityEditForm form;

	@BeforeEach
	void setUp() {

		validator = new LiabilityEditFormValidator(liabilityQuery);
		form = createLiabilityEditForm();
	}

	@Test
	void shouldMinimumMonthlyAmountBeLessThanTotalAmount() {

		// given
		form.setTotalAmount(BigDecimal.ONE);

		// when
		validator.validateForm(form, errors);

		// then
		verify(errors).rejectValue(F_MINIMUM_MONTHLY_AMOUNT, E_FIELD_VALUE_IS_TOO_HIGH);
	}

	@Test
	void shouldAlreadyRepaidAmountBeLessThanTotalAmount() {

		// given
		form.setTotalAmount(BigDecimal.ONE);

		// when
		validator.validateForm(form, errors);

		// then
		verify(errors).rejectValue(F_ALREADY_REPAID_AMOUNT, E_FIELD_VALUE_IS_TOO_HIGH);
	}

	@Test
	void shouldMinimumMonthlyAmountAndAlreadyRepaidAmountBeLessThanTotalAmount() {

		// given
		form.setMinimumMonthlyAmount(BigDecimal.TEN);
		form.setAlreadyRepaidAmount(BigDecimal.TEN);

		// when
		validator.validateForm(form, errors);

		// then
		verify(errors).rejectValue(F_ALREADY_REPAID_AMOUNT, E_FIELD_VALUE_IS_TOO_HIGH);
	}

	@Test
	void shouldRejectNonUniqueName() {

		// given
		given(liabilityQuery.existsByNameAndUserId(NAME, USER_ID)).willReturn(true);

		// when
		validator.validateForm(form, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_VALUE_MUST_BE_UNIQUE);
	}

	private LiabilityEditForm createLiabilityEditForm() {

		LiabilityEditForm form = new LiabilityEditForm(RANDOM_USER_ID);

		form.setName(RANDOM_NAME);
		form.setCreditor(randomAlphabetic(Name.MINIMUM_LENGTH));
		form.setTotalAmount(BigDecimal.TEN);
		form.setTotalCurrency(1);
		form.setAlreadyRepaidAmount(BigDecimal.ONE);
		form.setAlreadyRepaidCurrency(1);
		form.setMinimumMonthlyAmount(BigDecimal.ONE);
		form.setMinimumMonthlyCurrency(1);

		return form;
	}
}