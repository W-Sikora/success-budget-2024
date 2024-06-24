package pl.wsikora.successbudget.v3.common.type.language;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


public enum Language {

    POLISH("pl"),
    ENGLISH("en");

    private final String code;

    Language(String code) {

        this.code = code;
    }

    public static Language of(Integer ordinal) {

        Assert.isTrue(hasValidOrdinalRange(ordinal), "language ordinal must be of valid value");

        return values()[ordinal];
    }

    public static boolean hasValidOrdinalRange(Integer ordinal) {

        Assert.notNull(ordinal, "language ordinal must not be null");

        return ordinal > 0 || ordinal < values().length;
    }

    public static List<Integer> getOrdinals() {

        return Arrays.stream(values())
            .map(Language::ordinal)
            .toList();
    }

    public String getCode() {

        return code;
    }
}
