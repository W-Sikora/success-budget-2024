package pl.wsikora.successbudget.v3.budget.application.command;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.workflow.domain.InitAmountSettingEvent;


@Service
class InitAmountSettingListener {

	private final AmountSettingService amountSettingService;

	InitAmountSettingListener(AmountSettingService amountSettingService) {

		Assert.notNull(amountSettingService, "amountSettingService must not be null");

		this.amountSettingService = amountSettingService;
	}

	@EventListener
	void onInitAmountSettingEvent(InitAmountSettingEvent event) {

		Assert.notNull(event, "event must not be null");

		UserId userId = event.getUserId();

		amountSettingService.initAmountSetting(userId);
	}
}
