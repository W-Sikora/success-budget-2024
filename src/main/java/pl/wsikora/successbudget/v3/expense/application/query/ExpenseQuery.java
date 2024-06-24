package pl.wsikora.successbudget.v3.expense.application.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.time.Month;
import java.time.Year;
import java.util.List;


public interface ExpenseQuery {

	ExpenseDto getById(Long id, UserId userId);

	Page<ExpenseDto> findAll(Pageable pageable, ExpenseFilterAttributes filter, UserId userId);

	boolean hasAny(UserId userId);

	List<Money> getAllAmountsByCategoryIdAndUserIdAndYearAndMonth(CategoryId categoryId, UserId userId, Year year, Month month);

	boolean isUserLimitExceeded(UserId userId);

	List<YearMonthDto> getAllYearMonthsByUserId(UserId userId);
}
