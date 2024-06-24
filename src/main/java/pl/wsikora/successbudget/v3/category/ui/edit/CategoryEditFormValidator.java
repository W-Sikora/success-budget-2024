package pl.wsikora.successbudget.v3.category.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractFormValidator;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.name.NameValidator;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import static pl.wsikora.successbudget.v3.category.ui.edit.CategoryEditForm.F_NAME;
import static pl.wsikora.successbudget.v3.category.ui.edit.CategoryEditForm.F_PRIORITY;


@Service
class CategoryEditFormValidator extends AbstractFormValidator<CategoryEditForm> {

	private final CategoryQuery categoryQuery;

	CategoryEditFormValidator(CategoryQuery categoryQuery) {

		Assert.notNull(categoryQuery, "categoryQuery must not be null");

		this.categoryQuery = categoryQuery;
	}

	@Override
	public void validateForm(CategoryEditForm categoryEditForm, Errors errors) {

		Assert.notNull(categoryEditForm, "categoryEditForm must not be null");
		Assert.notNull(errors, "errors must not be null");

		String name = categoryEditForm.getName();
		NameValidator.validate(F_NAME, name, errors);
		PriorityValidator.validate(F_PRIORITY, categoryEditForm.getPriority(), errors);

		if (errors.hasErrors()) {

			return;
		}

		if (categoryEditForm.hasNoId() && categoryQuery.existsByNameAndUserId(Name.of(name),
			UserId.of(categoryEditForm.getUserId()))) {

			errors.rejectValue(F_NAME, E_FIELD_VALUE_MUST_BE_UNIQUE);
		}
	}
}
