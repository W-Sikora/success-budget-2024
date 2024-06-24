package pl.wsikora.successbudget.v3.user.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;


@Embeddable
@Access(AccessType.FIELD)
public class Password {

    public static final int MINIMUM_LENGTH = 8;
    public static final int MAXIMUM_LENGTH = 40;

    @Column(name = "password")
    private String value;

    protected Password() {}

    private Password(String value) {

        this.value = value;
    }

    public static Password of(String value) {

        Assert.isTrue(hasText(value), "password value must not be empty");
        Assert.isTrue(hasValidLength(value), "password value must be of valid length");

        return new Password(value);
    }

    public static Password encoded(String encodedValue) {

        Assert.isTrue(hasText(encodedValue), "password encodedValue must not be empty");

        return new Password(encodedValue);
    }

    public static boolean hasValidLength(String value) {

        int length = value.length();

        return length >= MINIMUM_LENGTH && length <= MAXIMUM_LENGTH;
    }

    public static Object[] getLengthRange() {

        return new Object[]{MINIMUM_LENGTH, MAXIMUM_LENGTH};
    }

    public String getValue() {

        return value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }

        if (!(o instanceof Password password)) {

            return false;
        }

        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
