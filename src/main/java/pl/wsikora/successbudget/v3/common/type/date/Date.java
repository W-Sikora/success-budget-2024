package pl.wsikora.successbudget.v3.common.type.date;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


@Embeddable
@Access(AccessType.FIELD)
public class Date {

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static final DateTimeFormatter DATE_FORMATTER_FOR_HTML = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final Date YESTERDAY = new Date(LocalDate.now().minusDays(1));
	public static final Date TODAY = new Date(LocalDate.now());
	public static final Date TOMORROW = new Date(LocalDate.now().plusDays(1));

	public static final String F_DATE = "date";

	@Column(name = "date", nullable = false)
	private LocalDate date;

	protected Date() {}

	private Date(String date) {

		this.date = LocalDate.parse(date);
	}

	private Date(LocalDate date) {

		this.date = date;
	}

	public static Date of(String date) {

		Assert.hasText(date, "date must not be empty");
		Assert.isTrue(hasValidFormat(date), "date must be in valid format");

		return new Date(date);
	}

	public LocalDate getDate() {

		return date;
	}

	public String asString() {

		return date.format(DATE_FORMATTER);
	}

	public String toForm() {

		return date.format(DATE_FORMATTER_FOR_HTML);
	}

	static boolean hasValidFormat(String value) {

		try {

			LocalDate.parse(value);

			return true;
		}
		catch (DateTimeParseException exception) {

			return false;
		}
	}

	static boolean isInFuture(String date) {

		Assert.isTrue(hasValidFormat(date), "date must be in valid format");

		return LocalDate.parse(date).isAfter(LocalDate.now());
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Date date1)) {

			return false;
		}

		return Objects.equals(date, date1.date);
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(date);
	}
}
