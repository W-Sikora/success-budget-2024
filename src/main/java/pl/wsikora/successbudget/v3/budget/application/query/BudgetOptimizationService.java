package pl.wsikora.successbudget.v3.budget.application.query;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPSolver.ResultStatus;
import com.google.ortools.linearsolver.MPVariable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.lang.Double.POSITIVE_INFINITY;


@Service
public class BudgetOptimizationService {

	private static final double MIN_VALUE = 0.01;

	public BudgetSolution orToolsOptimizeBudget(double[] shouldWeights,
												double[] couldWeights,
												double[] wouldWeights,
												double[] alphas,
												double disposableIncome,
												double[] mustHaveMin,
												double maxShouldSum,
												double maxCouldSum,
												double maxWouldSum,
												double[] liabilitiesMin,
												double[] liabilitiesMax) {

		Loader.loadNativeLibraries();

		int shouldLength = shouldWeights.length;
		int couldLength = couldWeights.length;
		int wouldLength = wouldWeights.length;
		int liabilitiesLength = liabilitiesMin.length;

		MPSolver solver = MPSolver.createSolver("GLOP");

		// Definition of variables
		MPVariable[] x_must = createVariables(solver, mustHaveMin, "x_must");
		MPVariable[] x_should = createVariables(solver, shouldLength, "x_should");
		MPVariable[] x_could = createVariables(solver, couldLength, "x_could");
		MPVariable[] x_would = createVariables(solver, wouldLength, "x_would");
		MPVariable[] l_repayment = createVariables(solver, liabilitiesLength, liabilitiesMin, liabilitiesMax, "l_repayment");
		MPVariable savings = solver.makeNumVar(MIN_VALUE, POSITIVE_INFINITY, "savings");

		// Objective function
		setObjective(solver, x_should, shouldWeights, x_could, couldWeights, x_would, wouldWeights, l_repayment, savings, alphas);

		// Budget constraint
		MPConstraint budgetConstraint = solver.makeConstraint(MIN_VALUE, disposableIncome, "budget");

		setCoefficient(x_must, budgetConstraint);
		setCoefficient(x_should, budgetConstraint);
		setCoefficient(x_could, budgetConstraint);
		setCoefficient(x_would, budgetConstraint);
		setCoefficient(l_repayment, budgetConstraint);
		budgetConstraint.setCoefficient(savings, 1.0);

		// maxShouldSum  constraint
		MPConstraint shouldConstraint = solver.makeConstraint(MIN_VALUE, maxShouldSum, "should_budget");
		setCoefficient(x_should, shouldConstraint);

		// maxCouldSum constraint
		MPConstraint couldConstraint = solver.makeConstraint(MIN_VALUE, maxCouldSum, "could_budget");
		setCoefficient(x_could, couldConstraint);

		// maxWouldSum constraint
		MPConstraint wouldConstraint = solver.makeConstraint(MIN_VALUE, maxWouldSum, "would_budget");
		setCoefficient(x_would, wouldConstraint);

		// Problem solving
		ResultStatus resultStatus = solver.solve();

		if (resultStatus != ResultStatus.OPTIMAL) {

			throw new IllegalArgumentException("No optimal result");
		}

		double[] mustHaveAmounts = toDoubleArray(x_must);
		double[] shouldHaveAmounts = toDoubleArray(x_should);
		double[] couldHaveAmounts = toDoubleArray(x_could);
		double[] wouldHaveAmounts = toDoubleArray(x_would);
		double[] liabilitiesAmounts = toDoubleArray(l_repayment);
		double savingsValue = savings.solutionValue();

		return new BudgetSolution(
			mustHaveAmounts,
			shouldHaveAmounts,
			couldHaveAmounts,
			wouldHaveAmounts,
			liabilitiesAmounts,
			savingsValue
		);
	}

	private void setCoefficient(MPVariable[] variables, MPConstraint budgetConstraint) {

		for (MPVariable var : variables) {

			budgetConstraint.setCoefficient(var, 1.0);
		}
	}

	private MPVariable[] createVariables(MPSolver solver, int length, String name) {

		MPVariable[] variables = new MPVariable[length];

		for (int i = 0; i < length; i++) {

			variables[i] = solver.makeNumVar(BudgetOptimizationService.MIN_VALUE, Double.POSITIVE_INFINITY, name + i);
		}

		return variables;
	}

	private MPVariable[] createVariables(MPSolver solver, double[] lowerBounds, String name) {

		int length = lowerBounds.length;

		MPVariable[] variables = new MPVariable[length];

		for (int i = 0; i < length; i++) {

			variables[i] = solver.makeNumVar(lowerBounds[i], Double.POSITIVE_INFINITY, name + i);
		}

		return variables;
	}

	private MPVariable[] createVariables(MPSolver solver, int length, double[] lowerBounds, double[] upperBounds, String name) {

		MPVariable[] variables = new MPVariable[length];

		for (int i = 0; i < length; i++) {

			variables[i] = solver.makeNumVar(lowerBounds[i], upperBounds[i], name + i);
		}

		return variables;
	}

	private void setObjective(MPSolver solver,
							  MPVariable[] x_should, double[] shouldWeights,
							  MPVariable[] x_could, double[] couldWeights,
							  MPVariable[] x_would, double[] wouldWeights,
							  MPVariable[] l_repayment,
							  MPVariable savings,
							  double[] alphas) {

		MPObjective objective = solver.objective();

		for (int i = 0; i < x_should.length; i++) {

			objective.setCoefficient(x_should[i], alphas[0] * shouldWeights[i]);
		}

		for (int i = 0; i < x_could.length; i++) {

			objective.setCoefficient(x_could[i], alphas[1] * couldWeights[i]);
		}

		for (int i = 0; i < x_would.length; i++) {

			objective.setCoefficient(x_would[i], alphas[2] * wouldWeights[i]);
		}

		for (MPVariable mpVariable : l_repayment) {

			objective.setCoefficient(mpVariable, alphas[3]);
		}

		objective.setCoefficient(savings, alphas[4]);

		objective.setMaximization();
	}

	private double[] toDoubleArray(MPVariable[] variables) {

		return Arrays.stream(variables)
			.mapToDouble(MPVariable::solutionValue)
			.toArray();
	}
}