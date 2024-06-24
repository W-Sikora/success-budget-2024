package pl.wsikora.successbudget.v3.common.abstraction.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.MappedSuperclass;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntityWithUserId extends AbstractEntity {

	private UserId userId;

	protected AbstractEntityWithUserId() {}

	protected AbstractEntityWithUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		this.userId = userId;
	}

	public UserId getUserId() {

		return userId;
	}

	public boolean hasDifferentUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return !this.userId.equals(userId);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof AbstractEntityWithUserId that)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), userId);
	}
}
