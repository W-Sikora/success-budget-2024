package pl.wsikora.successbudget.v3.budget.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.application.query.AmountSettingDto;
import pl.wsikora.successbudget.v3.budget.application.query.AmountSettingQuery;
import pl.wsikora.successbudget.v3.budget.domain.AmountSetting;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;

import java.util.List;
import java.util.function.Predicate;


@Service
class AmountSettingQueryImpl implements AmountSettingQuery {

	private final AmountSettingJpaRepository amountSettingJpaRepository;
	private final CategoryQuery categoryQuery;
	private final LiabilityQuery liabilityQuery;

	AmountSettingQueryImpl(AmountSettingJpaRepository amountSettingJpaRepository,
						   CategoryQuery categoryQuery,
						   LiabilityQuery liabilityQuery) {

		Assert.notNull(amountSettingJpaRepository, "amountSettingJpaRepository must not be null");
		Assert.notNull(categoryQuery, "categoryQuery must not be null");
		Assert.notNull(liabilityQuery, "liabilityQuery must not be null");

		this.amountSettingJpaRepository = amountSettingJpaRepository;
		this.categoryQuery = categoryQuery;
		this.liabilityQuery = liabilityQuery;
	}


	@Override
	public AmountSettingDto getAmountSettingDto(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		AmountSetting amountSetting = amountSettingJpaRepository.findByUserId(userId)
			.orElseThrow();

		List<CategoryDto> categories = categoryQuery.findAllByUserIdSortedById(userId);
		List<CategoryDto> mustCategories = getByPriority(categories, Priority.MUST_HAVE);
		List<CategoryDto> shouldCategories = getByPriority(categories, Priority.SHOULD_HAVE);
		List<CategoryDto> couldCategories = getByPriority(categories, Priority.COULD_HAVE);
		List<CategoryDto> wouldCategories = getByPriority(categories, Priority.WOULD_HAVE);
		List<LiabilityDto> liabilities = liabilityQuery.findAllByUserId(userId);

		return new AmountSettingDto(
			mustCategories,
			amountSetting.getMinimumAllowableMustExpenseAmounts(),
			liabilities,
			amountSetting.getMinimumAllowableLiabilityAmounts(),
			amountSetting.getMaximumAllowableLiabilityAmounts(),
			shouldCategories,
			amountSetting.getMaximumAllowableAmountOfShouldExpense(),
			couldCategories,
			amountSetting.getMaximumAllowableAmountOfCouldExpense(),
			wouldCategories,
			amountSetting.getMaximumAllowableAmountOfWouldExpense(),
			amountSetting.getDisposableIncome(),
			amountSetting.getCurrency(),
			amountSetting.isBalanced(),
			amountSetting.areMinimumAllowableLiabilityAmountsTooHigh(),
			amountSetting.areMinimumAllowableMustExpenseAmountsTooHigh(),
			amountSetting.areMaximumAllowableAmountOfShouldExpenseTooHigh(),
			amountSetting.areMaximumAllowableAmountOfCouldExpenseTooHigh(),
			amountSetting.areMaximumAllowableAmountOfWouldExpenseTooHigh()
		);
	}

	private List<CategoryDto> getByPriority(List<CategoryDto> categories, Priority priority) {

		return categories.stream()
			.filter(hasPriority(priority))
			.toList();
	}

	private Predicate<CategoryDto> hasPriority(Priority priority) {

		return categoryDto -> categoryDto.priority().equals(priority);
	}
}
