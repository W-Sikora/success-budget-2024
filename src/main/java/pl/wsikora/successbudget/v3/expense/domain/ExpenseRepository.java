package pl.wsikora.successbudget.v3.expense.domain;


import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface ExpenseRepository {

	Expense getByIdAndUserId(Long id, UserId userId);

	Expense getById(Long id);

	void save(Expense objective);
}
