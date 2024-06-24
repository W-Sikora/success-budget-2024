package pl.wsikora.successbudget.v3.common.type.step;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class StepTest {

	@ParameterizedTest
	@MethodSource("provideDataForShouldReturnNextStep")
	void shouldReturnNextStep(Step step, Step expectedNextStep) {

		// when
		Step nextStep = step.getNextStep();

		// then
		assertThat(nextStep).isEqualTo(expectedNextStep);
	}

	private static Stream<Arguments> provideDataForShouldReturnNextStep() {

		return Stream.of(
				Arguments.of(Step.INCOME, Step.LIABILITY),
				Arguments.of(Step.LIABILITY, Step.CATEGORY),
				Arguments.of(Step.CATEGORY, Step.SHOULD_CATEGORY_PREFERENCE),
				Arguments.of(Step.SHOULD_CATEGORY_PREFERENCE, Step.COULD_CATEGORY_PREFERENCE),
				Arguments.of(Step.COULD_CATEGORY_PREFERENCE, Step.WOULD_CATEGORY_PREFERENCE),
				Arguments.of(Step.WOULD_CATEGORY_PREFERENCE, Step.EXPENSE),
				Arguments.of(Step.EXPENSE, Step.BUDGET_PREFERENCE),
				Arguments.of(Step.BUDGET_PREFERENCE, Step.AMOUNT_SETTING),
				Arguments.of(Step.AMOUNT_SETTING, Step.BUDGET)
		);
	}
}