package pl.wsikora.successbudget.v3.common.type.step;

import org.springframework.util.Assert;


public enum Step {

	INCOME("/income"),
	LIABILITY("/liability"),
	CATEGORY("/category"),
	SHOULD_CATEGORY_PREFERENCE("/category-preference/should"),
	COULD_CATEGORY_PREFERENCE("/category-preference/could"),
	WOULD_CATEGORY_PREFERENCE("/category-preference/would"),
	EXPENSE("/expense"),
	BUDGET_PREFERENCE("/budget-preference"),
	AMOUNT_SETTING("/amount-setting"),
	BUDGET("/budget");

	private final String url;

	Step(String url) {

		Assert.hasText(url, "url must not be empty");

		this.url = url;
	}

	public String getUrl() {

		return url;
	}

	public static Step fromUrl(String url) {

		for (Step step : Step.values()) {

			if (step.url.equalsIgnoreCase(url)) {

				return step;
			}
		}

		throw new IllegalArgumentException("No step with url " + url);
	}

	public static Step getFirstStep() {

		return Step.values()[0];
	}

	public boolean isLastStep() {

		return BUDGET.equals(this);
	}

	public String getNextStepUrl() {

		return getNextStep().getUrl();
	}

	public String getPreviousStepUrl() {

		return getPreviousStep().getUrl();
	}

	public Step getNextStep() {

		Step[] values = Step.values();
		int ordinal = ordinal();

		if (ordinal < values.length - 1) {

			return values[ordinal + 1];
		}

		return values[0];
	}

	Step getPreviousStep() {

		Step[] values = Step.values();
		int ordinal = ordinal();

		if (ordinal > 0) {

			return values[ordinal - 1];
		}

		return values[values.length - 1];
	}
}
