package pl.wsikora.successbudget.v3.budget.application.query;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponentsPreference;
import pl.wsikora.successbudget.v3.budget.domain.InverseWeightsCalculator;
import pl.wsikora.successbudget.v3.budget.infrastructure.BudgetComponentsPreferenceRepository;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.category.infrastructure.CategoryJpaRepository;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class WeightsDataProvider {

	private final CategoryJpaRepository categoryJpaRepository;
	private final BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository;

	WeightsDataProvider(CategoryJpaRepository categoryJpaRepository,
						BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository) {

		Assert.notNull(categoryJpaRepository, "categoryJpaRepository must not be null");
		Assert.notNull(budgetComponentsPreferenceRepository, "budgetComponentsPreferenceRepository must not be null");

		this.categoryJpaRepository = categoryJpaRepository;
		this.budgetComponentsPreferenceRepository = budgetComponentsPreferenceRepository;
	}

	public WeightDto getWeights(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		List<Category> categories = categoryJpaRepository.findAllByUserId(userId);
		double[] shouldHaveWeights = calculateWeightsForPriority(categories, Priority.SHOULD_HAVE);
		double[] couldHaveWeights = calculateWeightsForPriority(categories, Priority.COULD_HAVE);
		double[] wouldHaveWeights = calculateWeightsForPriority(categories, Priority.WOULD_HAVE);

		List<BudgetComponentsPreference> budgetComponentsPreferences = budgetComponentsPreferenceRepository.findAllByUserId(userId);
		double[] alphaWeights = calculateAlphaWeights(budgetComponentsPreferences);

		return new WeightDto(shouldHaveWeights, couldHaveWeights, wouldHaveWeights, alphaWeights);
	}

	private double[] calculateWeightsForPriority(List<Category> categories, Priority priority) {

		List<Category> filteredCategories = categories.stream()
				.filter(category -> category.getPriority().equals(priority))
				.sorted(Comparator.comparingInt(Category::getRanking))
				.toList();

		double[] weights = InverseWeightsCalculator.calculate(filteredCategories.size());

		Map<Long, Double> categoryWeightsMap = filteredCategories.stream()
				.collect(Collectors.toMap(Category::getId, category -> weights[filteredCategories.indexOf(category)]));

		return filteredCategories.stream()
				.mapToDouble(category -> categoryWeightsMap.get(category.getId()))
				.toArray();
	}

	private double[] calculateAlphaWeights(List<BudgetComponentsPreference> preferences) {

		List<BudgetComponentsPreference> sortedPreferences = preferences.stream()
				.sorted(Comparator.comparingInt(BudgetComponentsPreference::getRanking))
				.toList();

		return InverseWeightsCalculator.calculate(sortedPreferences.size());
	}
}