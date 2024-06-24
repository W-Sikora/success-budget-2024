package pl.wsikora.successbudget.v3.config.currentuser;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;

import static pl.wsikora.successbudget.v3.common.util.Constants.USER_ROLE;


public class CurrentUser extends User {

	private final UserId userId;
	private final Currency currency;

	public CurrentUser(String username, String password, UserId userId, Currency currency) {

		super(username, password, List.of(new SimpleGrantedAuthority(USER_ROLE)));

		Assert.notNull(userId, "userId must not be null");

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
