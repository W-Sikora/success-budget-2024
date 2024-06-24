package pl.wsikora.successbudget.v3.user.ui.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.wsikora.successbudget.v3.common.util.Constants.LOGIN_URL;


@Controller
@RequestMapping(LOGIN_URL)
class LoginController {

	@GetMapping
	private String view() {

		return "user/login-view";
	}
}
