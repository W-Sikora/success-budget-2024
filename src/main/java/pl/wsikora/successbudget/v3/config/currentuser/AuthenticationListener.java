package pl.wsikora.successbudget.v3.config.currentuser;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private final HttpSession httpSession;

	AuthenticationListener(HttpSession httpSession) {

		Assert.notNull(httpSession, "HttpSession must not be null");

		this.httpSession = httpSession;
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		Authentication authentication = event.getAuthentication();

		if (authentication.getPrincipal() instanceof CurrentUser) {

			CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

			httpSession.setAttribute("userCurrency", currentUser.getCurrency());
		}
	}
}

