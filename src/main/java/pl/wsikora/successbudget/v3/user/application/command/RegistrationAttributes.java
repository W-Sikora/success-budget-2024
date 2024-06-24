package pl.wsikora.successbudget.v3.user.application.command;

public interface RegistrationAttributes {

    String getUsername();

    String getPassword();

    String getRepeatedPassword();

    Integer getPrimaryCurrency();
}
