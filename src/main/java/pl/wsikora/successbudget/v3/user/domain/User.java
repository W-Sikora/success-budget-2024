package pl.wsikora.successbudget.v3.user.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntity;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User extends AbstractEntity {

	@Embedded
	@AttributeOverride(name = Name.F_NAME, column = @Column(name = "username"))
	private Name username;

	@Embedded
	private Password password;

	@Enumerated(EnumType.ORDINAL)
	private Currency primaryCurrency;

	protected User() {}

	public User(Name username, Password password, Currency primaryCurrency) {

		Assert.notNull(username, "username must not be null");
		Assert.notNull(password, "password must not be null");
		Assert.notNull(primaryCurrency, "primaryCurrency must not be null");

		this.username = username;
		this.password = password;
		this.primaryCurrency = primaryCurrency;
	}

    public UserId getUserId() {

        return UserId.of(getId());
    }

	public Name getUsername() {

		return username;
	}

	public Password getPassword() {

		return password;
	}

	public Currency getPrimaryCurrency() {

		return primaryCurrency;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof User user)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(username, user.username)
				&& Objects.equals(password, user.password)
				&& primaryCurrency == user.primaryCurrency;
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), username, password, primaryCurrency);
	}
}
