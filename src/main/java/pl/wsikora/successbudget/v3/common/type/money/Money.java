package pl.wsikora.successbudget.v3.common.type.money;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.percentage.Percentage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;
import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.*;


@Embeddable
@Access(AccessType.FIELD)
public class Money {

	public static final String F_AMOUNT = "amount";
	public static final BigDecimal MINIMUM_VALUE = BigDecimal.ZERO;
	public static final BigDecimal MINIMUM_VALUE_GREATER_THAN_ZERO = new BigDecimal("0.01");
	public static final BigDecimal MAXIMUM_VALUE = new BigDecimal("100000000");

	private static final int SCALE = 2;
	private static final int PRECISE_SCALE = 6;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	@Enumerated(EnumType.ORDINAL)
	private Currency currency;

	@Column(name = "amount")
	private BigDecimal amount;

	protected Money() {}

	Money(Currency currency, BigDecimal amount) {

		this.currency = currency;
		this.amount = amount;
	}

	public static Money of(Currency currency, BigDecimal amount) {

		Assert.notNull(currency, "currency must not be null");
		Assert.isTrue(hasValidValue(amount), "amount must be valid");

		return new Money(currency, amount.setScale(SCALE, ROUNDING_MODE));
	}

	public static Money of(Currency currency) {

		Assert.notNull(currency, "currency must not be null");

		return new Money(currency, ZERO);
	}

	public Money add(Money money) {

		Assert.isTrue(hasTheSameCurrency(money), "added money must have the same currency");

		return new Money(
				this.currency,
				this.amount.add(money.amount).setScale(SCALE, ROUNDING_MODE)
		);
	}

	public Money subtract(Money money) {

		Assert.isTrue(hasTheSameCurrency(money), "subtracted money must have the same currency");

		return new Money(
				this.currency,
				this.amount.subtract(money.amount).setScale(SCALE, ROUNDING_MODE)
		);
	}

	public Percentage percentageOf(Money money) {

		Assert.isTrue(hasTheSameCurrency(money), "compared money must have the same currency");
		Assert.isTrue(!isZero(money.amount), "compared money amount must not be zero");

		BigDecimal dividedValue = amount.divide(money.amount, PRECISE_SCALE, RoundingMode.HALF_EVEN);

		BigDecimal percentageValue = dividedValue.multiply(HUNDRED).setScale(SCALE, RoundingMode.HALF_EVEN);

		return Percentage.of(percentageValue);
	}

	public Currency getCurrency() {

		return currency;
	}

	public BigDecimal getAmount() {

		return amount;
	}

	static boolean hasValidValue(BigDecimal value) {

		return isGreaterThanOrEqualTo(value, MINIMUM_VALUE)
				&& isLessThanOrEqualTo(value, MAXIMUM_VALUE);
	}

	static boolean hasValidValueGreaterThanZero(BigDecimal value) {

		return isGreaterThanOrEqualTo(value, MINIMUM_VALUE_GREATER_THAN_ZERO)
				&& isLessThanOrEqualTo(value, MAXIMUM_VALUE);
	}

	static Object[] getRange() {

		return new Object[]{MINIMUM_VALUE, MAXIMUM_VALUE};
	}

	static Object[] getRangeGreaterThanZero() {

		return new Object[]{MINIMUM_VALUE_GREATER_THAN_ZERO, MAXIMUM_VALUE};
	}

	private boolean hasTheSameCurrency(Money money) {

		Assert.notNull(money, "money must not be null");

		return this.currency.equals(money.currency);
	}
}
