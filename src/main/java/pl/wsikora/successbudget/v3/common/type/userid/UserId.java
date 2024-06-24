package pl.wsikora.successbudget.v3.common.type.userid;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;


@Embeddable
@Access(AccessType.FIELD)
public class UserId {

	@Column(name = "user_id")
	private Long value;

	protected UserId() {}

	private UserId(Long value) {

		this.value = value;
	}

	public static UserId of(Long value) {

		Assert.notNull(value, "value must not be null");

		return new UserId(value);
	}

	public Long getValue() {

		return value;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

        if (!(o instanceof UserId userId)) {

			return false;
		}

        return Objects.equals(value, userId.value);
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(value);
	}
}
