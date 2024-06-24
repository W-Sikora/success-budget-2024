package pl.wsikora.successbudget.v3.common.abstraction.ui;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;


public abstract class AbstractController {

	private static final String USER_CURRENCY = "userCurrency";
	private static final String REDIRECT_PATTERN = "redirect:%s";

	protected static final String PRIORITIES = "priorities";
	protected static final String TRANSACTIONS = "transactions";
	protected static final String REDIRECT = "redirect:";
	protected static final String TODAY = "today";
	protected static final String PAGE = "page";
	protected static final String CURRENT_STEP = "currentStep";
	protected static final String ADD_BUTTON_URL = "addButtonUrl";
	protected static final String ADD_BUTTON_DISABLED = "addButtonDisabled";
	protected static final String LIMIT = "limit";
	protected static final String CATEGORIES = "categories";
	protected static final String NEXT_BUTTON_DISABLED = "nextButtonDisabled";
	protected static final String BACK_BUTTON_DISABLED = "backButtonDisabled";
	protected static final String SHOW_EDIT_BUTTON = "showEditButton";
	protected static final String HIDE_MIDDLE_BUTTON = "hideMiddleButton";

	protected String redirect(String path) {

		Assert.hasText(path, "path must not be empty");

		return String.format(REDIRECT_PATTERN, path);
	}

	@ModelAttribute
	private void addAttributes(Model model, HttpSession session) {

		model.addAttribute("currencies", Currency.values());
		model.addAttribute(USER_CURRENCY, session.getAttribute(USER_CURRENCY));
		model.addAttribute("locale", LocaleContextHolder.getLocale());
	}
}
