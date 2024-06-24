package pl.wsikora.successbudget.v3.budget.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.budget.application.command.BudgetComponentsPreferenceService;
import pl.wsikora.successbudget.v3.budget.application.query.BudgetComponentsPreferenceDto;
import pl.wsikora.successbudget.v3.budget.application.query.BudgetComponentsPreferenceQuery;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;

import java.util.List;


@Controller
@RequestMapping("/budget-preference")
public class BudgetRankingController extends AbstractController {

	private static final String VIEW = "budget/budget-preference-view";

	private final BudgetComponentsPreferenceQuery budgetComponentsPreferenceRepository;
	private final BudgetComponentsPreferenceService budgetComponentsPreferenceService;

	BudgetRankingController(BudgetComponentsPreferenceQuery budgetComponentsPreferenceRepository,
							BudgetComponentsPreferenceService budgetComponentsPreferenceService) {

		Assert.notNull(budgetComponentsPreferenceRepository, "budgetComponentsPreferenceRepository must not be null");
		Assert.notNull(budgetComponentsPreferenceService, "budgetComponentsPreferenceService must not be null");

		this.budgetComponentsPreferenceRepository = budgetComponentsPreferenceRepository;
		this.budgetComponentsPreferenceService = budgetComponentsPreferenceService;
	}

	@GetMapping
	private String view(@CurrentUserId UserId userId, Model model) {

		List<BudgetComponentsPreferenceDto> budgetComponentsPreferences = budgetComponentsPreferenceRepository.findAllByUserId(userId);

		model.addAttribute(HIDE_MIDDLE_BUTTON, true);
		model.addAttribute(CURRENT_STEP, Step.BUDGET_PREFERENCE);
		model.addAttribute("budgetComponents", budgetComponentsPreferences);

		return VIEW;
	}

	@PostMapping
	private String save(String orderedIds, @CurrentUserId UserId userId) {

		budgetComponentsPreferenceService.saveRanking(orderedIds, userId);

		return redirect("/budget-preference");
	}
}
