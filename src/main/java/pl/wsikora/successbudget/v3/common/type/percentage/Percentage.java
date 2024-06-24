package pl.wsikora.successbudget.v3.common.type.percentage;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.*;


public class Percentage {

	private final BigDecimal percentage;

	private Percentage(BigDecimal percentage) {

		this.percentage = percentage;
	}

	public static Percentage of(BigDecimal percentage) {

		Assert.notNull(percentage, "percentage must not be null");
		Assert.isTrue(isGreaterThanOrEqualTo(percentage, BigDecimal.ZERO), "percentage must be greater than or equal to 0");
		Assert.isTrue(isLessThanOrEqualTo(percentage, HUNDRED), "percentage must be less than or equal to 100");

		return new Percentage(percentage);
	}

	public String roundToNearestPercent() {

		BigDecimal roundedPercentage = percentage.setScale(2, RoundingMode.HALF_EVEN);

		NumberFormat numberFormat = NumberFormat.getInstance(LocaleContextHolder.getLocale());

		String result = numberFormat.format(roundedPercentage) + " %";

		if (isZero(roundedPercentage)) {

			return result;
		}

		return "~ " + result;
	}
}
