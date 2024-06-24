package pl.wsikora.successbudget.v3.common.type.description;

import jakarta.annotation.Nullable;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import static org.springframework.util.StringUtils.hasText;

@Embeddable
@Access(AccessType.FIELD)
public class Description {

	public static final String F_DESCRIPTION = "description";

	static final int MAXIMUM_LENGTH = 200;
	static final Description EMPTY = new Description("");

	@Column(name = "description")
	private String description;

	protected Description() {}

	private Description(String description) {

		this.description = description;
	}

	public static Description of(@Nullable String description) {

		if (!hasText(description)) {

			return EMPTY;
		}

		Assert.isTrue(hasValidLength(description), "description must be of valid length");

		return new Description(description);
	}

	public String getDescription() {

		return description;
	}

	static boolean hasValidLength(String value) {

		int length = value.length();

		return length <= MAXIMUM_LENGTH;
	}

	static Object[] getMaxLength() {

		return new Object[]{MAXIMUM_LENGTH};
	}
}
