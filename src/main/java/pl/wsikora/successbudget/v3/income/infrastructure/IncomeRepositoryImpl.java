package pl.wsikora.successbudget.v3.income.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.income.domain.Income;
import pl.wsikora.successbudget.v3.income.domain.IncomeRepository;


@Service
class IncomeRepositoryImpl implements IncomeRepository {

	private final IncomeJpaRepository incomeJpaRepository;

	IncomeRepositoryImpl(IncomeJpaRepository incomeJpaRepository) {

		Assert.notNull(incomeJpaRepository, "incomeJpaRepository must not be null");

		this.incomeJpaRepository = incomeJpaRepository;
	}

	@Override
	public Income getByUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return incomeJpaRepository.getByUserId(userId);
	}

	@Override
	public void save(Income income) {

		Assert.notNull(income, "income must not be null");

		incomeJpaRepository.save(income);
	}
}
