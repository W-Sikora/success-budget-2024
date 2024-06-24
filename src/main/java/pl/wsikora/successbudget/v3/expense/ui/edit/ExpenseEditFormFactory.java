package pl.wsikora.successbudget.v3.expense.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseDto;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseQuery;

import static java.util.Objects.isNull;


@Service
class ExpenseEditFormFactory {

	private final ExpenseQuery expenseQuery;

	ExpenseEditFormFactory(ExpenseQuery expenseQuery) {

		Assert.notNull(expenseQuery, "expenseQuery must not be null");

		this.expenseQuery = expenseQuery;
	}

	ExpenseEditForm createEditForm(Long id, UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		if (isNull(id)) {

			return new ExpenseEditForm(userId.getValue());
		}

		ExpenseDto expenseDto = expenseQuery.getById(id, userId);

		return toTransactionEditForm(expenseDto, userId);
	}

	private ExpenseEditForm toTransactionEditForm(ExpenseDto expenseDto, UserId userId) {

		ExpenseEditForm categoryEditForm = new ExpenseEditForm(userId.getValue());

		categoryEditForm.setId(expenseDto.id());
		categoryEditForm.setName(expenseDto.name().getName());
		categoryEditForm.setDescription(expenseDto.description().getDescription());
		categoryEditForm.setDate(expenseDto.date().toForm());
		categoryEditForm.setAmount(expenseDto.amount().getAmount());
		categoryEditForm.setCurrency(expenseDto.amount().getCurrency().ordinal());
		categoryEditForm.setCategoryId(expenseDto.categoryId().getCategoryId());

		return categoryEditForm;
	}
}
