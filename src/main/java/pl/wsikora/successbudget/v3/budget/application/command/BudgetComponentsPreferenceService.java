package pl.wsikora.successbudget.v3.budget.application.command;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponents;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponentsPreference;
import pl.wsikora.successbudget.v3.budget.infrastructure.BudgetComponentsPreferenceRepository;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.StringUtils.hasText;


@Service
public class BudgetComponentsPreferenceService {

	private final BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository;

	BudgetComponentsPreferenceService(BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository) {

		Assert.notNull(budgetComponentsPreferenceRepository, "budgetComponentsPreferenceRepository must not be null");

		this.budgetComponentsPreferenceRepository = budgetComponentsPreferenceRepository;
	}

	void initAlwaysPresent(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		BudgetComponents.getAlwaysPresent().forEach(budgetComponents -> {

			BudgetComponentsPreference budgetComponentsPreference = new BudgetComponentsPreference(userId, budgetComponents);

			budgetComponentsPreferenceRepository.save(budgetComponentsPreference);
		});
	}

	void initRepaymentIfNeeded(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		boolean noRepayment = budgetComponentsPreferenceRepository.findAllByUserId(userId).stream()
			.filter(v -> v.getBudgetComponents().equals(BudgetComponents.REPAYMENT))
			.findFirst()
			.isEmpty();

		if (noRepayment) {

			BudgetComponentsPreference budgetComponentsPreference = new BudgetComponentsPreference(userId, BudgetComponents.REPAYMENT);

			budgetComponentsPreferenceRepository.save(budgetComponentsPreference);
		}
	}

	public void saveRanking(String orderedIds, UserId userId) {

		Assert.notNull(orderedIds, "orderedIds must not be null");
		Assert.notNull(userId, "userId must not be null");

		AtomicInteger counter = new AtomicInteger(1);

		convertStringToLongList(orderedIds).forEach(id -> {

			BudgetComponentsPreference budgetComponentsPreference = budgetComponentsPreferenceRepository.getByIdAndUserId(id, userId);

			budgetComponentsPreference.updateRanking(counter.getAndIncrement());

			budgetComponentsPreferenceRepository.save(budgetComponentsPreference);
		});
	}

	private List<Long> convertStringToLongList(String orderedIds) {

		if (!hasText(orderedIds)) {

			return List.of();
		}

		return Arrays.stream(orderedIds.split(","))
			.map(String::trim)
			.map(Long::parseLong)
			.toList();
	}
}
