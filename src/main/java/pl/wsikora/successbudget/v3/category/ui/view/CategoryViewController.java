package pl.wsikora.successbudget.v3.category.ui.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;


@Controller
@RequestMapping("/category")
class CategoryViewController extends AbstractController {

	private final CategoryQuery categoryQuery;

	CategoryViewController(CategoryQuery categoryQuery) {

		Assert.notNull(categoryQuery, "categoryQuery cannot be null");

		this.categoryQuery = categoryQuery;
	}

	@GetMapping
	private String view() {

		return "category/category-view";
	}

	@ModelAttribute
	private void model(Model model, @CurrentUserId UserId userId,
					   @PageableDefault(size = 15) Pageable pageable,
					   CategoryFilterForm categoryFilterForm) {

		Page<CategoryDto> page = categoryQuery.findAll(pageable, categoryFilterForm, userId);
		boolean hasNoCategory = !categoryQuery.hasAllRequiredPriorities(userId);

		model.addAttribute(PAGE, page);
		model.addAttribute(NEXT_BUTTON_DISABLED, hasNoCategory);
		model.addAttribute(CURRENT_STEP, Step.CATEGORY);
		model.addAttribute(ADD_BUTTON_URL, "/category/edit");
		model.addAttribute(ADD_BUTTON_DISABLED, categoryQuery.isUserLimitExceeded(userId));
		model.addAttribute(PRIORITIES, Priority.values());
		model.addAttribute(LIMIT, Category.LIMIT_PER_USER);
	}
}
