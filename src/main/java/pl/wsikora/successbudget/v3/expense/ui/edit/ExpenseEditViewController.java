package pl.wsikora.successbudget.v3.expense.ui.edit;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.expense.application.command.ExpenseService;

import java.util.List;


@Controller
@RequestMapping("/expense/edit")
class ExpenseEditViewController extends AbstractController {

	private static final String VIEW = "/expense/expense-edit-view";

	private static final String EXPENSE_EDIT_FORM = "expenseEditForm";

	private final ExpenseEditFormValidator expenseEditFormValidator;
	private final ExpenseService expenseService;
	private final ExpenseEditFormFactory expenseEditFormFactory;
	private final CategoryQuery categoryQuery;

	ExpenseEditViewController(ExpenseEditFormValidator expenseEditFormValidator,
							  ExpenseService expenseService,
							  ExpenseEditFormFactory expenseEditFormFactory,
							  CategoryQuery categoryQuery) {


		Assert.notNull(expenseEditFormValidator, "expenseEditFormValidator must not be null");
		Assert.notNull(expenseService, "expenseService must not be null");
		Assert.notNull(expenseEditFormFactory, "expenseEditFormFactory must not be null");
		Assert.notNull(categoryQuery, "categoryQuery must not be null");

		this.expenseEditFormValidator = expenseEditFormValidator;
		this.expenseService = expenseService;
		this.expenseEditFormFactory = expenseEditFormFactory;
		this.categoryQuery = categoryQuery;
	}

	@GetMapping
	private String view() {

		return VIEW;
	}

	@PostMapping
	private String save(@ModelAttribute(EXPENSE_EDIT_FORM) ExpenseEditForm categoryEditForm,
						@CurrentUserId UserId userId, Errors errors) {

		expenseEditFormValidator.validate(categoryEditForm, errors);

		if (errors.hasErrors()) {

			return VIEW;
		}

		expenseService.save(categoryEditForm, userId);

		return redirect("/expense");
	}

	@ModelAttribute(EXPENSE_EDIT_FORM)
	private ExpenseEditForm categoryEditForm(@RequestParam(required = false) Long id, @CurrentUserId UserId userId) {

		return expenseEditFormFactory.createEditForm(id, userId);
	}

	@ModelAttribute(CATEGORIES)
	private List<CategoryDto> categories(@CurrentUserId UserId userId) {

		return categoryQuery.findAllByUserIdSortedByName(userId);
	}
}
