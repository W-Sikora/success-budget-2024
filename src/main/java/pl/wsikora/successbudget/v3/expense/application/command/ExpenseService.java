package pl.wsikora.successbudget.v3.expense.application.command;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.date.Date;
import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.domain.Expense;
import pl.wsikora.successbudget.v3.expense.domain.ExpenseRepository;


@Service
public class ExpenseService {

	private final ExpenseRepository expenseRepository;

	ExpenseService(ExpenseRepository expenseRepository) {

		Assert.notNull(expenseRepository, "expenseRepository must not be null");

		this.expenseRepository = expenseRepository;
	}

	public void save(ExpenseAttributes expenseAttributes, UserId userId) {

		Assert.notNull(expenseAttributes, "expenseAttributes must not be null");
		Assert.notNull(userId, "userId must not be null");

		Expense expense;

		if (expenseAttributes.hasId()) {

			expense = update(expenseAttributes, userId);
		}
		else {

			expense = create(expenseAttributes, userId);
		}

		expenseRepository.save(expense);
	}

	private Expense update(ExpenseAttributes expenseAttributes, UserId userId) {

		Expense expense = expenseRepository.getByIdAndUserId(expenseAttributes.getId(), userId);

		expense.updateName(Name.of(expenseAttributes.getName()));
		expense.updateDescription(Description.of(expenseAttributes.getDescription()));
		expense.updateDate(Date.of(expenseAttributes.getDate()));
		expense.updateAmount(Money.of(Currency.of(expenseAttributes.getCurrency()), expenseAttributes.getAmount()));
		expense.updateCategoryId(CategoryId.of(expenseAttributes.getCategoryId()));

		return expense;
	}

	private Expense create(ExpenseAttributes expenseAttributes, UserId userId) {

		Name name = Name.of(expenseAttributes.getName());
		Description description = Description.of(expenseAttributes.getDescription());
		Date date = Date.of(expenseAttributes.getDate());
		Money amount = Money.of(Currency.of(expenseAttributes.getCurrency()), expenseAttributes.getAmount());
		CategoryId categoryId = CategoryId.of(expenseAttributes.getCategoryId());

		return new Expense(userId, name, description, date, amount, categoryId);
	}
}
