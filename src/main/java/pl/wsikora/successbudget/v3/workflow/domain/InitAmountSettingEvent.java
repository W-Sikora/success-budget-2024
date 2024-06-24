package pl.wsikora.successbudget.v3.workflow.domain;

import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public class InitAmountSettingEvent extends ApplicationEvent {

	private final UserId userId;

	public InitAmountSettingEvent(Object source, UserId userId) {

		super(source);

		Assert.notNull(userId, "User id must not be null");

		this.userId = userId;
	}

	public UserId getUserId() {

		return userId;
	}
}
