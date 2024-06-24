package pl.wsikora.successbudget.v3.common.util;

import java.math.BigDecimal;


public class BigDecimalUtils {

	public static final BigDecimal HUNDRED = new BigDecimal("100");

	private BigDecimalUtils() {}

	public static boolean isEqual(BigDecimal first, BigDecimal second) {

		if (first == null || second == null) {

			return first == second;
		}

		return first.compareTo(second) == 0;
	}

	public static boolean isZero(BigDecimal value) {

		return BigDecimal.ZERO.equals(value);
	}

	public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {

		if (first == null || second == null) {

			return false;
		}

		return first.compareTo(second) > 0;
	}

	public static boolean isGreaterThanZero(BigDecimal value) {

		return isGreaterThan(value, BigDecimal.ZERO);
	}

	public static boolean isLessThan(BigDecimal first, BigDecimal second) {

		if (first == null || second == null) {

			return false;
		}

		return first.compareTo(second) < 0;
	}

	public static boolean isGreaterThanOrEqualTo(BigDecimal first, BigDecimal second) {

		if (first == null || second == null) {

			return false;
		}

		return first.compareTo(second) >= 0;
	}

	public static boolean isLessThanOrEqualTo(BigDecimal first, BigDecimal second) {

		if (first == null || second == null) {

			return false;
		}

		return first.compareTo(second) <= 0;
	}
}

