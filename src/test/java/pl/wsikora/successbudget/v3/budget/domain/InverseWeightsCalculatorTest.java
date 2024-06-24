package pl.wsikora.successbudget.v3.budget.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static pl.wsikora.successbudget.v3.budget.domain.InverseWeightsCalculator.VALIDATION_MESSAGE;


class InverseWeightsCalculatorTest {

	private static final double DELTA = 1e-9;

	@Test
	void shouldCalculateWithSingleCriteria() {

		// given
		int numberOfCriteria = 1;

		// when
		double[] result = InverseWeightsCalculator.calculate(numberOfCriteria);

		// then
		double[] expected = {1.0};

		assertArrayEquals(expected, result, DELTA);
	}

	@Test
	void shouldCalculateWithMultipleCriteria() {

		// given
		int numberOfCriteria = 3;

		// when
		double[] result = InverseWeightsCalculator.calculate(numberOfCriteria);

		// then
		double[] expected = {0.5454545454545454, 0.2727272727272727, 0.1818181818181818};

		assertArrayEquals(expected, result, DELTA);
	}

	@Test
	void shouldNotCalculateWithZeroCriteria() {

		// given
		int numberOfCriteria = 0;

		// when
		Executable executable = () -> InverseWeightsCalculator.calculate(numberOfCriteria);

		// then
		Exception exception = assertThrows(IllegalArgumentException.class, executable);

		assertEquals(VALIDATION_MESSAGE, exception.getMessage());
	}

	@Test
	void shouldNotCalculateWithNegativeCriteria() {

		// given
		int numberOfCriteria = -1;

		// when
		Executable executable = () -> InverseWeightsCalculator.calculate(numberOfCriteria);

		// then
		Exception exception = assertThrows(IllegalArgumentException.class, executable);

		assertEquals(VALIDATION_MESSAGE, exception.getMessage());
	}
}