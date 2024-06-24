package pl.wsikora.successbudget.v3.category.ui.edit;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.wsikora.successbudget.v3.category.application.command.CategoryService;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;


@Controller
@RequestMapping("/category/edit")
class CategoryEditViewController extends AbstractController {

	private static final String VIEW = "/category/category-edit-view";

	private static final String CATEGORY_EDIT_FORM = "categoryEditForm";

	private final CategoryEditFormValidator categoryEditFormValidator;
	private final CategoryService categoryService;
	private final CategoryEditFormFactory categoryEditFormFactory;

	CategoryEditViewController(CategoryEditFormValidator categoryEditFormValidator,
							   CategoryService categoryService,
							   CategoryEditFormFactory categoryEditFormFactory) {

		Assert.notNull(categoryEditFormValidator, "categoryEditFormValidator must not be null");
		Assert.notNull(categoryService, "categoryService must not be null");
		Assert.notNull(categoryEditFormFactory, "categoryEditFormFactory must not be null");

		this.categoryEditFormValidator = categoryEditFormValidator;
		this.categoryService = categoryService;
		this.categoryEditFormFactory = categoryEditFormFactory;
	}

	@GetMapping
	private String view() {

		return VIEW;
	}

	@PostMapping
	private String save(@ModelAttribute(CATEGORY_EDIT_FORM) CategoryEditForm categoryEditForm,
						@CurrentUserId UserId userId, Errors errors) {

		categoryEditFormValidator.validate(categoryEditForm, errors);

		if (errors.hasErrors()) {

			return VIEW;
		}

		categoryService.save(categoryEditForm, userId);

		return redirect("/category");
	}

	@ModelAttribute(CATEGORY_EDIT_FORM)
	private CategoryEditForm categoryEditForm(@RequestParam(required = false) Long id, @CurrentUserId UserId userId) {

		return categoryEditFormFactory.createEditForm(id, userId);
	}

	@ModelAttribute(PRIORITIES)
	private Priority[] priorities() {

		return Priority.values();
	}
}
