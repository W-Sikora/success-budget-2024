package pl.wsikora.successbudget.v3.category.ui.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.category.ui.edit.CategoryEditForm.F_NAME;


@ExtendWith(MockitoExtension.class)
class CategoryEditFormValidatorTest implements ValidatorErrorCode {

	private static final String RANDOM_NAME = randomAlphabetic(Name.MINIMUM_LENGTH);
	private static final Name NAME = Name.of(RANDOM_NAME);
	private static final Long RANDOM_USER_ID = nextLong();
	private static final UserId USER_ID = UserId.of(RANDOM_USER_ID);

	@Mock private Errors errors;
	@Mock private CategoryQuery categoryQuery;

	private CategoryEditFormValidator validator;
	private CategoryEditForm form;

	@BeforeEach
	void setUp() {

		validator = new CategoryEditFormValidator(categoryQuery);
		form = createForm();
	}

	@Test
	void shouldRejectNonUniqueName() {

		// given
		given(categoryQuery.existsByNameAndUserId(NAME, USER_ID)).willReturn(true);

		// when
		validator.validateForm(form, errors);

		// then
		verify(errors).rejectValue(F_NAME, E_FIELD_VALUE_MUST_BE_UNIQUE);
	}

	private CategoryEditForm createForm() {

		CategoryEditForm categoryEditForm = new CategoryEditForm(RANDOM_USER_ID);

		categoryEditForm.setName(RANDOM_NAME);

		return categoryEditForm;
	}
}