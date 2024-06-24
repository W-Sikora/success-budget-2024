package pl.wsikora.successbudget.v3.common.type.currency;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


public enum Currency {

    POLISH_ZLOTY("PLN", "zł"),
    EURO("EUR", "€"),
    UNITED_STATES_DOLLAR("USD", "$"),
    STERLING("GBP", "£"),
    SWISS_FRANC("CHF", "Fr");

    public static final String F_CURRENCY = "currency";

    private final String code;
    private final String sign;

    Currency(String code, String sign) {

        this.code = code;
        this.sign = sign;
    }

    public static Currency of(Integer ordinal) {

        Assert.isTrue(hasValidOrdinalRange(ordinal), "currency ordinal must be of valid value");

        return values()[ordinal];
    }

    public static List<Integer> getOrdinals() {

        return Arrays.stream(values())
            .map(Currency::ordinal)
            .toList();
    }

    public String getCode() {

        return code;
    }

    public String getSign() {

        return sign;
    }

    static boolean hasValidOrdinalRange(Integer ordinal) {

        Assert.notNull(ordinal, "currency ordinal must not be null");

        return ordinal >= 0 && ordinal < values().length;
    }
}
