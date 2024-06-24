package pl.wsikora.successbudget.v3.user.ui.registration;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractFormValidator;
import pl.wsikora.successbudget.v3.common.type.currency.CurrencyValidator;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.name.NameValidator;
import pl.wsikora.successbudget.v3.user.application.query.UserQuery;

import java.util.Objects;

import static pl.wsikora.successbudget.v3.user.ui.registration.RegistrationForm.*;


@Service
class RegistrationFormValidator extends AbstractFormValidator<RegistrationForm> {

	static final String E_REPEATED_PASSWORD_IS_DIFFERENT = "repeated.password.is.different";
	static final String E_USERNAME_MUST_BE_UNIQUE = "username.must.be.unique";

	private final UserQuery userQuery;

	RegistrationFormValidator(UserQuery userQuery) {

		Assert.notNull(userQuery, "userQuery must not be null");

		this.userQuery = userQuery;
	}

	@Override
	public void validateForm(RegistrationForm registrationForm, Errors errors) {

		Assert.notNull(errors, "errors must not be null");

		String username = registrationForm.getUsername();
		NameValidator.validate(F_USERNAME, username, errors);

		String password = registrationForm.getPassword();
		PasswordValidator.validate(F_PASSWORD, password, errors);

		CurrencyValidator.validate(F_PRIMARY_CURRENCY, registrationForm.getPrimaryCurrency(), errors);

		if (errors.hasErrors()) {

			return;
		}

		if (userQuery.existsByUsername(Name.of(username))) {

			errors.rejectValue(F_USERNAME, E_USERNAME_MUST_BE_UNIQUE);
		}

		if (!Objects.equals(password, registrationForm.getRepeatedPassword())) {

			errors.rejectValue(F_REPEATED_PASSWORD, E_REPEATED_PASSWORD_IS_DIFFERENT);
		}
	}
}
