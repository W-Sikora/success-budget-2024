package pl.wsikora.successbudget.v3.category.domain;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


public enum Priority {

    MUST_HAVE("must"),
    SHOULD_HAVE("should"),
    COULD_HAVE("could"),
    WOULD_HAVE("would");

    public static final String F_PRIORITY = "priority";

    private final String name;

    Priority(String name) {

        this.name = name;
    }

    public static Priority of(Integer ordinal) {

        Assert.isTrue(hasValidOrdinalRange(ordinal), "Priority ordinal must be of valid ordinal");

        return Priority.values()[ordinal];
    }

    public static Priority fromName(String name) {

        for (Priority priority : Priority.values()) {

            if (priority.name.equalsIgnoreCase(name)) {

                return priority;
            }
        }

        throw new IllegalArgumentException("No priority with name " + name);
    }

    public static List<Integer> getOrdinals() {

        return Arrays.stream(Priority.values())
            .map(Priority::ordinal)
            .toList();
    }

    public static boolean hasValidOrdinalRange(Integer ordinal) {

        Assert.notNull(ordinal, "priority ordinal must not be null");

        return ordinal >= 0 && ordinal < Priority.values().length;
    }
}
