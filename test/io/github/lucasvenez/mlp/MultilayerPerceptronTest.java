package io.github.lucasvenez.mlp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import io.github.lucasvenez.ann.mlp.MultilayerPerceptron;
import io.github.lucasvenez.ann.mlp.exception.NeuralNetworkBuildingException;
import io.github.lucasvenez.ann.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.ann.mlp.function.IdentityFunction;
import io.github.lucasvenez.ann.mlp.function.SigmoidFunction;
import io.github.lucasvenez.ann.mlp.function.ThresholdSigmoidFunction;
import io.github.lucasvenez.ann.mlp.training.Backpropagation;

/**
 * This network receives three inputs: two boolean values (0 = false, 1 = true)
 * and an indicator of the logical operation (1 = xor, 2 = and, 3 = or). The
 * result is the logical operation between the boolean values.
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 * 
 */
public class MultilayerPerceptronTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	private final double TRUE = 1, FALSE = 0;

	private final double AND = 0, OR = 1, XOR = 2;

	private MultilayerPerceptron mlp;

	private final List<Double[]> inputs = new ArrayList<Double[]>();

	private final List<Double[]> outputs = new ArrayList<Double[]>();

	@Before
	public void setUp() throws NeuralNetworkBuildingException {

		/*
		 * Creating and configuring neural network
		 */
		this.mlp = new MultilayerPerceptron();

		mlp.setInputLayer(3, new IdentityFunction());

		mlp.addHiddenLayer(6, new SigmoidFunction());

		mlp.setOutputLayer(1, new ThresholdSigmoidFunction());

		/*
		 * Creating training data
		 */
		for (double i : new double[] { TRUE, FALSE })
			for (double j : new double[] { TRUE, FALSE }) {
				inputs.add(new Double[] { i, j, AND });
				outputs.add(new Double[] { i + j == 2 ? TRUE : FALSE });

				inputs.add(new Double[] { i, j, OR });
				outputs.add(new Double[] { i + j > 0 ? TRUE : FALSE });

				inputs.add(new Double[] { i, j, XOR });
				outputs.add(new Double[] { i + j == 1 ? TRUE : FALSE });
			}
	}

	@Test
	public void architectureTest() {
		assertEquals(mlp.getNumberOfConnections(), 31);
	}
	
	@Test
	public void logicalOperationsTest() throws NeuralNetworkFowardException, NeuralNetworkBuildingException {

		/*
		 * Training neural network
		 */
		Backpropagation back = new Backpropagation(mlp);

		back.setTraineInput(inputs);

		back.setTraineOutput(outputs);

		back.traine();

		/*
		 * Using neural network
		 */
		for (double i = 0; i < 2; i++)

			for (double j = 0; j < 2; j++) {

				collector.checkThat(
					doubleToLogicalString(i) + " XOR " + doubleToLogicalString(j),
					mlp.apply(i, j, XOR)[0],
					new IsEqual<Double>(new Double(i + j != 1 ? FALSE : TRUE)));

				collector.checkThat(
					doubleToLogicalString(i) + " OR " + doubleToLogicalString(j),
					mlp.apply(i, j, OR)[0],
					new IsEqual<Double>(new Double(i + j == 0 ? FALSE : TRUE)));

				collector.checkThat(
					doubleToLogicalString(i) + " AND " + doubleToLogicalString(j),
					mlp.apply(i, j, AND)[0],
					new IsEqual<Double>(new Double(i + j < 2 ? FALSE : TRUE)));
			}
	}
	
	/**
	 * Converting doubles {< 0.5, >= 0.5} to the Strings {"FALSE", "TRUE"}, respectively.
	 * @param value a double value  corresponding to a logical value
	 * @return a String with corresponding logical value {TRUE, FALSE} of a double value {0, 1}.
	 */
	public String doubleToLogicalString(double value) {
		return value >= 0.5 ? "TRUE" : "FALSE";
	}
}
