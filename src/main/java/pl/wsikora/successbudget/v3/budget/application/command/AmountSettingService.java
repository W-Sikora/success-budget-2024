package pl.wsikora.successbudget.v3.budget.application.command;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.domain.AmountSetting;
import pl.wsikora.successbudget.v3.budget.domain.AmountSettingRepository;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponents;
import pl.wsikora.successbudget.v3.budget.infrastructure.BudgetComponentsPreferenceRepository;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseQuery;
import pl.wsikora.successbudget.v3.expense.application.query.YearMonthDto;
import pl.wsikora.successbudget.v3.income.application.query.IncomeDto;
import pl.wsikora.successbudget.v3.income.application.query.IncomeQuery;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_EVEN;


@Service
public class AmountSettingService {

	private final AmountSettingRepository amountSettingRepository;
	private final BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository;
	private final CategoryQuery categoryQuery;
	private final ExpenseQuery expenseQuery;
	private final IncomeQuery incomeQuery;
	private final LiabilityQuery liabilityQuery;

	AmountSettingService(AmountSettingRepository amountSettingRepository,
						 BudgetComponentsPreferenceRepository budgetComponentsPreferenceRepository,
						 CategoryQuery categoryQuery,
						 ExpenseQuery expenseQuery,
						 IncomeQuery incomeQuery,
						 LiabilityQuery liabilityQuery) {

		Assert.notNull(amountSettingRepository, "amountSettingRepository must not be null");
		Assert.notNull(budgetComponentsPreferenceRepository, "budgetComponentsPreferenceRepository must not be null");
		Assert.notNull(categoryQuery, "categoryQuery must not be null");
		Assert.notNull(expenseQuery, "expenseQuery must not be null");
		Assert.notNull(incomeQuery, "incomeQuery must not be null");
		Assert.notNull(liabilityQuery, "liabilityQuery must not be null");

		this.amountSettingRepository = amountSettingRepository;
		this.budgetComponentsPreferenceRepository = budgetComponentsPreferenceRepository;
		this.categoryQuery = categoryQuery;
		this.expenseQuery = expenseQuery;
		this.incomeQuery = incomeQuery;
		this.liabilityQuery = liabilityQuery;
	}

	void create(UserId userId, Currency currency) {

		Assert.notNull(userId, "userId must not be null");

		AmountSetting amountSetting = new AmountSetting(userId, currency);

		amountSettingRepository.save(amountSetting);
	}

	void initAmountSetting(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		AmountSetting amountSetting = amountSettingRepository.getByUserId(userId);

		Assert.notNull(amountSetting, "amountSetting must not be null");

		List<YearMonthDto> allYearMonths = expenseQuery.getAllYearMonthsByUserId(userId);

		List<BigDecimal> mustAmounts = getAmounts(Priority.MUST_HAVE, userId, allYearMonths);
		amountSetting.updateMinimumAllowableMustExpenseAmounts(mustAmounts);

		BigDecimal shouldAmount = getTotalAmount(Priority.SHOULD_HAVE, userId, allYearMonths);
		amountSetting.updateMaximumAllowableAmountOfShouldExpense(shouldAmount);

		BigDecimal couldAmount = getTotalAmount(Priority.COULD_HAVE, userId, allYearMonths);
		amountSetting.updateMaximumAllowableAmountOfCouldExpense(couldAmount);

		BigDecimal wouldAmount = getTotalAmount(Priority.WOULD_HAVE, userId, allYearMonths);
		amountSetting.updateMaximumAllowableAmountOfWouldExpense(wouldAmount);

		incomeQuery.findByUserId(userId)
			.map(IncomeDto::initialAmount)
			.map(Money::getAmount)
			.ifPresent(amountSetting::updateDisposableIncome);

		boolean repaymentPresent = budgetComponentsPreferenceRepository.findAllByUserId(userId).stream()
			.anyMatch(budgetComponentsPreference -> budgetComponentsPreference.getBudgetComponents().equals(BudgetComponents.REPAYMENT));

		if (repaymentPresent) {

			initLiabilityAmounts(userId, amountSetting);
		}

		amountSettingRepository.save(amountSetting);
	}

	private void initLiabilityAmounts(UserId userId, AmountSetting amountSetting) {

		List<LiabilityDto> liabilities = liabilityQuery.findAllByUserId(userId);

		List<BigDecimal> minimumMonthly = getMonthly(liabilities, LiabilityDto::minimumMonthly);
		amountSetting.updateMinimumAllowableLiabilityAmounts(minimumMonthly);

		List<BigDecimal> maximumMonthly = getMonthly(liabilities, LiabilityDto::remainingToBeRepaid);
		amountSetting.updateMaximumAllowableLiabilityAmounts(maximumMonthly);
	}

	private List<BigDecimal> getMonthly(List<LiabilityDto> liabilities, Function<LiabilityDto, Money> function) {

		return liabilities.stream()
			.map(function)
			.map(Money::getAmount)
			.collect(Collectors.toList());
	}

	private BigDecimal getTotalAmount(Priority priority, UserId userId, List<YearMonthDto> allYearMonths) {

		return getAmounts(priority, userId, allYearMonths).stream()
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private List<BigDecimal> getAmounts(Priority priority, UserId userId, List<YearMonthDto> allYearMonths) {

		List<CategoryId> categoryIds = categoryQuery.findAllIdsByPriorityAndUserId(priority, userId);

		return getAmounts(categoryIds, userId, allYearMonths);
	}

	private List<BigDecimal> getAmounts(List<CategoryId> categoryIds, UserId userId, List<YearMonthDto> allYearMonths) {

		List<BigDecimal> amounts = new ArrayList<>();

		categoryIds.forEach(categoryId -> {

			BigDecimal averageAmount = BigDecimal.ZERO;

			int index = 0;

			for (YearMonthDto yearMonthDto : allYearMonths) {

				BigDecimal amount = expenseQuery.getAllAmountsByCategoryIdAndUserIdAndYearAndMonth(
						categoryId, userId, yearMonthDto.year(), yearMonthDto.month()).stream()
					.map(Money::getAmount)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

				averageAmount = averageAmount.add(amount);
				index++;
			}

			if (index > 0) {

				averageAmount = averageAmount.divide(BigDecimal.valueOf(index), HALF_EVEN);
			}

			amounts.add(averageAmount);
		});

		return amounts;
	}
}
