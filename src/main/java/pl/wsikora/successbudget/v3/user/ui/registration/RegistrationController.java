package pl.wsikora.successbudget.v3.user.ui.registration;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.user.application.command.UserService;

import static pl.wsikora.successbudget.v3.common.util.Constants.LOGIN_URL;
import static pl.wsikora.successbudget.v3.common.util.Constants.REGISTRATION_PATH;


@Controller
@RequestMapping(REGISTRATION_PATH)
class RegistrationController extends AbstractController {

	private static final String REGISTRATION_FORM = "registrationForm";
	private static final String VIEW = "user/registration-view";

	private final RegistrationFormValidator registrationFormValidator;
	private final UserService userService;

	RegistrationController(RegistrationFormValidator registrationFormValidator,
						   UserService userService) {

		Assert.notNull(registrationFormValidator, "registrationFormValidator must not be null");
		Assert.notNull(userService, "userApplicationService must not be null");

		this.registrationFormValidator = registrationFormValidator;
		this.userService = userService;
	}

	@GetMapping
	private String getView() {

		return VIEW;
	}

	@PostMapping
	private String save(@ModelAttribute(REGISTRATION_FORM) RegistrationForm registrationForm, Errors errors) {

		registrationFormValidator.validate(registrationForm, errors);

		if (errors.hasErrors()) {

			return VIEW;
		}

		userService.save(registrationForm);

		return redirect(LOGIN_URL);
	}

	@ModelAttribute(REGISTRATION_FORM)
	private RegistrationForm registrationForm() {

		return new RegistrationForm();
	}
}
