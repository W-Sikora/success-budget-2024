package pl.wsikora.successbudget.v3.income.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.income.application.query.IncomeDto;
import pl.wsikora.successbudget.v3.income.application.query.IncomeQuery;
import pl.wsikora.successbudget.v3.income.domain.Income;

import java.util.Optional;


@Service
class IncomeQueryImpl implements IncomeQuery {

	private final IncomeJpaRepository incomeJpaRepository;

	IncomeQueryImpl(IncomeJpaRepository incomeJpaRepository) {

		Assert.notNull(incomeJpaRepository, "incomeJpaRepository must not be null");

		this.incomeJpaRepository = incomeJpaRepository;
	}

	@Override
	public Optional<IncomeDto> findByUserId(UserId userId) {

		return incomeJpaRepository.findByUserId(userId)
				.map(this::convertToDto);
	}

	private IncomeDto convertToDto(Income income) {

		return new IncomeDto(
				income.getId(),
				income.getInitial()
		);
	}
}
