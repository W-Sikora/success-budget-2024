package pl.wsikora.successbudget.v3.user.ui.registration;

import pl.wsikora.successbudget.v3.user.application.command.RegistrationAttributes;


public class RegistrationForm implements RegistrationAttributes {

    static final String F_USERNAME = "username";
    static final String F_PASSWORD = "password";
    static final String F_REPEATED_PASSWORD = "repeatedPassword";
    static final String F_PRIMARY_CURRENCY = "primaryCurrency";

    private String username;
    private String password;
    private String repeatedPassword;
    private Integer primaryCurrency;

    public RegistrationForm() {}

    @Override
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    @Override
    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String getRepeatedPassword() {

        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {

        this.repeatedPassword = repeatedPassword;
    }

    @Override
    public Integer getPrimaryCurrency() {

        return primaryCurrency;
    }

    public void setPrimaryCurrency(Integer primaryCurrency) {

        this.primaryCurrency = primaryCurrency;
    }
}
