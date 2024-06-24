package pl.wsikora.successbudget.v3.expense.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.date.Date;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.application.query.YearMonthDto;
import pl.wsikora.successbudget.v3.expense.domain.Expense;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;


@Repository
public interface ExpenseJpaRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

	boolean existsByUserId(UserId userId);

	@Query(value = """
		select
		    e.*
		from
		    expenses e
		where
		    e.category_id = :categoryId
		and
		    e.user_id = :userId
		and
		    extract(year from e.date) = :year
		and
			extract(month from e.date) = :month
	""", nativeQuery = true)
	List<Expense> findAllByCategoryIdAndUserIdAndYearAndMonth(Long categoryId, Long userId, int year, int month);

	List<Expense> findAllByUserId(UserId userId);

	int countAllByUserId(UserId userId);

	@Query(value = """
				select distinct
				    e.date
				from
				    Expense e
				where
					e.userId = :userId
				""")
	List<Date> findDistinctDatesByUserId(@Param("userId") UserId userId);
}
