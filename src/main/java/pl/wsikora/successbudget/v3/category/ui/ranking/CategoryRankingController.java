package pl.wsikora.successbudget.v3.category.ui.ranking;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.category.application.command.CategoryService;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;

import java.util.List;


@Controller
@RequestMapping("/category-preference/{name}")
class CategoryRankingController extends AbstractController {

	private static final String VIEW = "category/preference-category-view";

	private final CategoryQuery categoryQuery;
	private final CategoryService categoryService;

	CategoryRankingController(CategoryQuery categoryQuery, CategoryService categoryService) {

		Assert.notNull(categoryQuery, "categoryQuery must not be null");
		Assert.notNull(categoryService, "categoryService must not be null");

		this.categoryQuery = categoryQuery;
		this.categoryService = categoryService;
	}

	@GetMapping
	private String view(@PathVariable String name, @CurrentUserId UserId userId, Model model) {

		model.addAttribute(HIDE_MIDDLE_BUTTON, true);
		model.addAttribute("name", name);

		Step step = Step.fromUrl("/category-preference/" + name);
		model.addAttribute(CURRENT_STEP, step);

		Priority priority = Priority.fromName(name);
		List<CategoryDto> allByPriorityAndUserId = categoryQuery.findAllByPriorityAndUserId(priority, userId);

		model.addAttribute("categoryGroup", allByPriorityAndUserId);

		return VIEW;
	}

	@PostMapping
	private String save(@PathVariable String name, String orderedIds, @CurrentUserId UserId userId) {

		categoryService.saveRanking(orderedIds, userId);

		return redirect("/category-preference/" + name);
	}
}
