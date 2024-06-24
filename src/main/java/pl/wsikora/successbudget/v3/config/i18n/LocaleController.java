package pl.wsikora.successbudget.v3.config.i18n;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;

import java.util.Locale;

import static pl.wsikora.successbudget.v3.config.i18n.LocaleConfig.COOKIE_MAX_AGE;
import static pl.wsikora.successbudget.v3.config.i18n.LocaleConfig.LANGUAGE;


@Controller
class LocaleController extends AbstractController {

	private final LocaleResolver localeResolver;

	LocaleController(LocaleResolver localeResolver) {

		Assert.notNull(localeResolver, "LocaleResolver must not be null");

		this.localeResolver = localeResolver;
	}

	@PostMapping("/set-locale")
	private String handleSetLocale(@RequestParam(LANGUAGE) String language, @RequestParam("originalUrl") String originalUrl,
								   HttpServletRequest request, HttpServletResponse response) {

		Assert.hasText(language, "locale must not be empty");

		setLocale(language, request, response);
		setCookie(language, response);

		return redirect(originalUrl);
	}

	private void setLocale(String language, HttpServletRequest request, HttpServletResponse response) {

		Locale locale = new Locale(language);

		localeResolver.setLocale(request, response, locale);
	}

	private void setCookie(String language, HttpServletResponse response) {

		Cookie languageCookie = new Cookie(LANGUAGE, language);

		languageCookie.setMaxAge((int) COOKIE_MAX_AGE.toSeconds());

		response.addCookie(languageCookie);
	}
}
