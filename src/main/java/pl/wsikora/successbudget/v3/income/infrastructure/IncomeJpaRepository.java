package pl.wsikora.successbudget.v3.income.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.income.domain.Income;

import java.util.Optional;


@Repository
interface IncomeJpaRepository extends JpaRepository<Income, Long> {

	Income getByUserId(UserId userId);

	Optional<Income> findByUserId(UserId userId);
}
