package pl.wsikora.successbudget.v3.budget.application.query;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;

public interface BudgetComponentsPreferenceQuery {

	List<BudgetComponentsPreferenceDto> findAllByUserId(UserId userId);
}
