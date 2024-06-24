package pl.wsikora.successbudget.v3.budget.domain;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.stream.IntStream;


public class InverseWeightsCalculator {

	static final String VALIDATION_MESSAGE = "numberOfCriteria must be greater then zero";

	private InverseWeightsCalculator() {}

	public static double[] calculate(int numberOfCriteria) {

		Assert.isTrue(numberOfCriteria > 0, VALIDATION_MESSAGE);

		int[] ranks = IntStream.rangeClosed(1, numberOfCriteria).toArray();

		double[] inverseRanks = Arrays.stream(ranks)
				.mapToDouble(rank -> 1.0 / rank)
				.toArray();

		double sum = Arrays.stream(inverseRanks, 0, numberOfCriteria).sum();

		return IntStream.range(0, numberOfCriteria)
				.mapToDouble(i -> inverseRanks[i] / sum)
				.toArray();
	}
}
