package pl.wsikora.successbudget.v3.common.type.categoryid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import static org.mockito.Mockito.verify;
import static pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId.F_CATEGORY_ID;


@ExtendWith(MockitoExtension.class)
class CategoryIdValidatorTest implements ValidatorErrorCode {

    @Mock private Errors errors;

    @Test
    void shouldDetectNullCategoryId() {

        // given
        Long value = null;

        // when
        CategoryIdValidator.validate(F_CATEGORY_ID, value, errors);

        // then
        verify(errors).rejectValue(F_CATEGORY_ID, E_FIELD_MUST_NOT_BE_EMPTY);
    }
}
