package pl.wsikora.successbudget.v3.expense.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractQuery;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.date.Date;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseDto;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseFilterAttributes;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseQuery;
import pl.wsikora.successbudget.v3.expense.application.query.YearMonthDto;
import pl.wsikora.successbudget.v3.expense.domain.Expense;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.wsikora.successbudget.v3.expense.domain.Expense.LIMIT_PER_USER;


@Service
class ExpenseQueryImpl extends AbstractQuery<Expense, ExpenseDto, ExpenseFilterAttributes> implements ExpenseQuery {

	private final ExpenseJpaRepository expenseJpaRepository;
	private final CategoryQuery categoryQuery;

	ExpenseQueryImpl(ExpenseJpaRepository expenseJpaRepository, CategoryQuery categoryQuery) {

		Assert.notNull(expenseJpaRepository, "transactionJpaRepository must not be null");
		Assert.notNull(categoryQuery, "categoryQuery must not be null");

		this.expenseJpaRepository = expenseJpaRepository;
		this.categoryQuery = categoryQuery;
	}

	@Override
	public List<Money> getAllAmountsByCategoryIdAndUserIdAndYearAndMonth(CategoryId categoryId, UserId userId, Year year, Month month) {

		Assert.notNull(categoryId, "categoryId must not be null");
		Assert.notNull(userId, "userId must not be null");

		return expenseJpaRepository.findAllByCategoryIdAndUserIdAndYearAndMonth(
			categoryId.getCategoryId(), userId.getValue(), year.getValue(), month.getValue()).stream()
			.sorted()
			.map(Expense::getAmount)
			.toList();
	}

	@Override
	public boolean hasAny(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return expenseJpaRepository.existsByUserId(userId);
	}

	@Override
	public boolean isUserLimitExceeded(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return expenseJpaRepository.countAllByUserId(userId) >= LIMIT_PER_USER;
	}

	@Override
	public List<YearMonthDto> getAllYearMonthsByUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		List<LocalDate> dates = expenseJpaRepository.findDistinctDatesByUserId(userId).stream()
			.map(Date::getDate)
			.toList();

		return dates.stream()
			.map(YearMonth::from)
			.collect(Collectors.toSet())
			.stream()
			.map(yearMonth -> new YearMonthDto(Year.of(yearMonth.getYear()), yearMonth.getMonth()))
			.toList();
	}

	@Override
	protected JpaRepository<Expense, Long> getJpaRepository() {

		return expenseJpaRepository;
	}

	@Override
	protected JpaSpecificationExecutor<Expense> getJpaSpecificationExecutor() {

		return expenseJpaRepository;
	}

	@Override
	protected AbstractSpecification<Expense, ExpenseFilterAttributes> getSpecification() {

		return new ExpenseSpecification();
	}

	@Override
	protected ExpenseDto convertToDto(Expense expense) {

		CategoryId categoryId = expense.getCategoryId();

		return new ExpenseDto(
				expense.getId(),
				expense.getName(),
				expense.getDescription(),
				expense.getDate(),
				expense.getAmount(),
				categoryId,
				getCategoryName(categoryId)
		);
	}

	private Name getCategoryName(CategoryId categoryId) {

		return categoryQuery.findById(categoryId)
				.map(CategoryDto::name)
				.orElse(Name.EMPTY);
	}
}
