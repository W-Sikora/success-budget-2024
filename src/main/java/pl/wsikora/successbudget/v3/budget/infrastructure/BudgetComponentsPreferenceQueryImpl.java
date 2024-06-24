package pl.wsikora.successbudget.v3.budget.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.application.query.BudgetComponentsPreferenceDto;
import pl.wsikora.successbudget.v3.budget.application.query.BudgetComponentsPreferenceQuery;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponentsPreference;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;

import static java.util.Comparator.*;


@Service
class BudgetComponentsPreferenceQueryImpl implements BudgetComponentsPreferenceQuery {

	private final BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository;

	BudgetComponentsPreferenceQueryImpl(BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository) {

		Assert.notNull(budgetComponentsPreferenceRepository, "budgetComponentsPreferenceRepository must not be null");

		this.budgetComponentsPreferenceRepository = budgetComponentsPreferenceRepository;
	}

	@Override
	public List<BudgetComponentsPreferenceDto> findAllByUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return budgetComponentsPreferenceRepository.findAllByUserId(userId).stream()
			.sorted(comparing(BudgetComponentsPreference::getRanking, nullsLast(naturalOrder())))
			.map(this::convertToDto)
			.toList();
	}

	private BudgetComponentsPreferenceDto convertToDto(BudgetComponentsPreference entity) {

		return new BudgetComponentsPreferenceDto(entity.getId(), entity.getBudgetComponents());
	}
}
