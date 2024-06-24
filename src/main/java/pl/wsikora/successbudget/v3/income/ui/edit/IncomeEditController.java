package pl.wsikora.successbudget.v3.income.ui.edit;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.income.application.command.IncomeService;


@Controller
@RequestMapping("/income/edit")
class IncomeEditController extends AbstractController {

	private static final String VIEW = "income/income-edit-view";

	private static final String INCOME_EDIT_FORM = "incomeEditForm";

	private final IncomeService incomeService;
	private final IncomeEditFormValidator incomeEditFormValidator;
	private final IncomeEditFormFactory incomeEditFormFactory;

	IncomeEditController(IncomeService incomeService,
						 IncomeEditFormValidator incomeEditFormValidator,
						 IncomeEditFormFactory incomeEditFormFactory) {

		Assert.notNull(incomeService, "incomeService must not be null");
		Assert.notNull(incomeEditFormValidator, "incomeEditFormValidator must not be null");
		Assert.notNull(incomeEditFormFactory, "incomeEditFormFactory must not be null");

		this.incomeService = incomeService;
		this.incomeEditFormValidator = incomeEditFormValidator;
		this.incomeEditFormFactory = incomeEditFormFactory;
	}

	@GetMapping
	private String view() {

		return VIEW;
	}

	@PostMapping
	private String save(@ModelAttribute(INCOME_EDIT_FORM) IncomeEditForm savingEditForm,
						  @CurrentUserId UserId userId, Errors errors) {

		incomeEditFormValidator.validate(savingEditForm, errors);

		if (errors.hasErrors()) {

			return VIEW;
		}

		incomeService.save(savingEditForm, userId);

		return redirect("/income");
	}

	@ModelAttribute(INCOME_EDIT_FORM)
	private IncomeEditForm savingEditForm(@CurrentUserId UserId userId) {

		return incomeEditFormFactory.createEditForm(userId);
	}
}
