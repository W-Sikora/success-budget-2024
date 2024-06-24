package pl.wsikora.successbudget.v3.budget.application.command;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.user.domain.UserRegisteredEvent;


@Service
class LiabilityDefinedListener {

	private final BudgetComponentsPreferenceService budgetComponentsPreferenceService;

	LiabilityDefinedListener(BudgetComponentsPreferenceService budgetComponentsPreferenceService) {

		Assert.notNull(budgetComponentsPreferenceService, "budgetComponentsPreferenceService must not be null");

		this.budgetComponentsPreferenceService = budgetComponentsPreferenceService;
	}

	@EventListener
	void onUserRegisteredEvent(UserRegisteredEvent event) {

		Assert.notNull(event, "event must not be null");

		UserId userId = event.getUserId();

		budgetComponentsPreferenceService.initRepaymentIfNeeded(userId);
	}
}
