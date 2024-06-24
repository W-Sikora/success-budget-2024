package pl.wsikora.successbudget.v3.budget.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.budget.domain.BudgetComponentsPreference;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;

@Repository
public interface BudgetComponentsPreferenceRepository extends JpaRepository<BudgetComponentsPreference, Long> {

	BudgetComponentsPreference getByIdAndUserId(Long id, UserId userId);

	List<BudgetComponentsPreference> findAllByUserId(UserId userId);
}
