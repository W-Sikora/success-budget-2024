package pl.wsikora.successbudget.v3.budget.application.query;

import org.springframework.util.Assert;


public class WeightDto {

	private final double[] shouldHaveWeights;
	private final double[] couldHaveWeights;
	private final double[] wouldHaveWeights;
	private final double[] alphaWeights;

	public WeightDto(double[] shouldHaveWeights,
					 double[] couldHaveWeights,
					 double[] wouldHaveWeights,
					 double[] alphaWeights) {

		Assert.notNull(shouldHaveWeights, "shouldHaveWeights must not be null");
		Assert.notNull(couldHaveWeights, "couldHaveWeights must not be null");
		Assert.notNull(wouldHaveWeights, "couldHaveWeights must not be null");
		Assert.notNull(alphaWeights, "alphaWeights must not be null");

		this.shouldHaveWeights = shouldHaveWeights;
		this.couldHaveWeights = couldHaveWeights;
		this.wouldHaveWeights = wouldHaveWeights;
		this.alphaWeights = alphaWeights;
	}

	public double[] getShouldHaveWeights() {

		return shouldHaveWeights;
	}

	public double[] getCouldHaveWeights() {

		return couldHaveWeights;
	}

	public double[] getWouldHaveWeights() {

		return wouldHaveWeights;
	}

	public double[] getAlphaWeights() {

		return alphaWeights;
	}
}
