package pl.wsikora.successbudget.v3.expense.ui.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseDto;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseQuery;
import pl.wsikora.successbudget.v3.expense.domain.Expense;


@Controller
@RequestMapping("/expense")
class ExpenseViewController extends AbstractController {

	private final ExpenseQuery expenseQuery;
	private final CategoryQuery categoryQuery;

	ExpenseViewController(ExpenseQuery expenseQuery, CategoryQuery categoryQuery) {

		Assert.notNull(expenseQuery, "expenseQuery cannot be null");
		Assert.notNull(categoryQuery, "categoryQuery cannot be null");

		this.expenseQuery = expenseQuery;
		this.categoryQuery = categoryQuery;
	}

	@GetMapping
	private String view() {

		return "expense/expense-view";
	}

	@ModelAttribute
	private void model(Model model, @CurrentUserId UserId userId,
					   @PageableDefault(size = 15) Pageable pageable,
					   ExpenseFilterForm transactionFilterForm) {

		Page<ExpenseDto> page = expenseQuery.findAll(pageable, transactionFilterForm, userId);
		boolean hasNoExpense = !expenseQuery.hasAny(userId);

		model.addAttribute(PAGE, page);
		model.addAttribute(NEXT_BUTTON_DISABLED, hasNoExpense);
		model.addAttribute(CURRENT_STEP, Step.EXPENSE);
		model.addAttribute(ADD_BUTTON_URL, "/expense/edit");
		model.addAttribute(ADD_BUTTON_DISABLED, expenseQuery.isUserLimitExceeded(userId));
		model.addAttribute(CATEGORIES, categoryQuery.findAllByUserIdSortedByName(userId));
		model.addAttribute(LIMIT, Expense.LIMIT_PER_USER);
	}
}
