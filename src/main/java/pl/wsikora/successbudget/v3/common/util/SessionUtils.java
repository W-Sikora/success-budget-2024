package pl.wsikora.successbudget.v3.common.util;

import jakarta.servlet.http.HttpSession;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;


public class SessionUtils {

	private SessionUtils() {}

	public static Currency getCurrency(HttpSession session) {

		return (Currency) session.getAttribute("currency");
	}
}
