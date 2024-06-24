package pl.wsikora.successbudget.v3.income.ui.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.income.application.query.IncomeDto;
import pl.wsikora.successbudget.v3.income.application.query.IncomeQuery;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Controller
@RequestMapping("/income")
class IncomeViewController extends AbstractController {

	private final IncomeQuery incomeQuery;

	IncomeViewController(IncomeQuery incomeQuery) {

		Assert.notNull(incomeQuery, "incomeQuery must not be null");

		this.incomeQuery = incomeQuery;
	}

	@GetMapping
	private String view() {

		return "income/income-view";
	}

	@ModelAttribute
	private void model(Model model, @CurrentUserId UserId userId) {

		IncomeDto incomeDto = incomeQuery.findByUserId(userId).orElse(null);

		model.addAttribute("incomeDto", incomeDto);
		model.addAttribute(BACK_BUTTON_DISABLED, true);
		model.addAttribute(CURRENT_STEP, Step.INCOME);
		model.addAttribute(NEXT_BUTTON_DISABLED, isNull(incomeDto));
		model.addAttribute(SHOW_EDIT_BUTTON, nonNull(incomeDto));
		model.addAttribute(ADD_BUTTON_URL, "/income/edit");
	}
}
