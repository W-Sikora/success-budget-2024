package pl.wsikora.successbudget.v3.category.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import static java.util.Objects.isNull;


@Service
class CategoryEditFormFactory {

	private final CategoryQuery categoryQuery;

	CategoryEditFormFactory(CategoryQuery categoryQuery) {

		Assert.notNull(categoryQuery, "categoryQuery must not be null");

		this.categoryQuery = categoryQuery;
	}

	CategoryEditForm createEditForm(Long id, UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		if (isNull(id)) {

			return new CategoryEditForm(userId.getValue());
		}

		CategoryDto categoryDto = categoryQuery.getById(id, userId);

		return toCategoryEditForm(categoryDto, userId);
	}

	private CategoryEditForm toCategoryEditForm(CategoryDto categoryDto, UserId userId) {

		CategoryEditForm categoryEditForm = new CategoryEditForm(userId.getValue());

		categoryEditForm.setId(categoryDto.id());
		categoryEditForm.setName(categoryDto.name().getName());
		categoryEditForm.setPriority(categoryDto.priority().ordinal());

		return categoryEditForm;
	}
}
