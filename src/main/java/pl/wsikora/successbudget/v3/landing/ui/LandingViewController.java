package pl.wsikora.successbudget.v3.landing.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.wsikora.successbudget.v3.common.util.Constants.LANDING_PAGE_PATH;


@Controller
@RequestMapping(LANDING_PAGE_PATH)
class LandingViewController {

	@GetMapping
	private String landingView() {

		return "landing/landing-page-view";
	}
}
