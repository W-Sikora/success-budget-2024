package pl.wsikora.successbudget.v3.expense.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractRepository;
import pl.wsikora.successbudget.v3.expense.domain.Expense;
import pl.wsikora.successbudget.v3.expense.domain.ExpenseRepository;


@Service
class ExpenseRepositoryImpl extends AbstractRepository<Expense> implements ExpenseRepository {

	private final ExpenseJpaRepository expenseJpaRepository;

	ExpenseRepositoryImpl(ExpenseJpaRepository expenseJpaRepository) {

		super(expenseJpaRepository);

		Assert.notNull(expenseJpaRepository, "transactionJpaRepository must not be null");

		this.expenseJpaRepository = expenseJpaRepository;
	}

	@Override
	public Expense getById(Long id) {

		Assert.notNull(id, "id must not be null");

		return expenseJpaRepository.getById(id);
	}
}
