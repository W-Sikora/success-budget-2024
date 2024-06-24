package pl.wsikora.successbudget.v3.common.type.name;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntity;

import java.util.Objects;


@Embeddable
@Access(AccessType.FIELD)
public class Name implements Comparable<Name> {

	public static final int MINIMUM_LENGTH = 3;
	public static final int MAXIMUM_LENGTH = 60;
	public static final String F_NAME = "name";

	public static final Name EMPTY = new Name("");

	@Column(name = "name", length = MAXIMUM_LENGTH)
	private String name;

	protected Name() {}

	private Name(String name) {

		this.name = name;
	}

	public static Name of(String name) {

		Assert.hasText(name, "name must not be empty");
		Assert.isTrue(hasValidLength(name), "name must be of valid length");

		return new Name(name);
	}

	public String getName() {

		return name;
	}

	static boolean hasValidLength(String name) {

		int length = name.length();

		return length >= MINIMUM_LENGTH && length <= MAXIMUM_LENGTH;
	}

	static Object[] getLengthRange() {

		return new Object[]{MINIMUM_LENGTH, MAXIMUM_LENGTH};
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Name name1)) {

			return false;
		}

		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(name);
	}

	@Override
	public int compareTo(Name name) {

		return this.name.compareTo(name.name);
	}
}
