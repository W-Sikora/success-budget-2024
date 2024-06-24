package pl.wsikora.successbudget.v3.user.domain;

import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public class UserRegisteredEvent extends ApplicationEvent {

	private final UserId userId;
	private final Currency currency;

	public UserRegisteredEvent(Object source, UserId userId, Currency currency) {

		super(source);

		Assert.notNull(userId, "userId must not be null");
		Assert.notNull(currency, "currency must not be null");

		this.userId = userId;
		this.currency = currency;
	}

	public UserId getUserId() {

		return userId;
	}

	public Currency getCurrency() {

		return currency;
	}
}
