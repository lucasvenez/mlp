package io.github.lucasvenez.ann.mlp.training;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.ann.mlp.MultilayerPerceptron;
import io.github.lucasvenez.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.utils.ListUtils;

public class Backpropagation {

	private MultilayerPerceptron mlp;

	private final List<Double[]> traineInput = new ArrayList<Double[]>();

	private final List<Double[]> traineOutput = new ArrayList<Double[]>();

	private int iterations = 5000;

	private boolean randomPresentation = true;

	private ListUtils<Double[]> listsUtil = new ListUtils<Double[]>();

	private Update updateFunction = null;
	
	public Backpropagation(final MultilayerPerceptron mlp) {
		this.mlp = mlp;
		this.updateFunction = new OnlineUpdate(mlp);
	}
	
	public Backpropagation(final MultilayerPerceptron mlp, Update updateFunction) {
		this.mlp = mlp;
		this.updateFunction = updateFunction;
	}

	public void traine() throws NeuralNetworkFowardException {

		mlp.initializeWeightsWithRandomUniformDistribution();

		/*
		 * for each iteration
		 */
		for (int i = 0; i < iterations; i++) {

			if (randomPresentation) {

				int[] newOrder = listsUtil.shuffle(traineInput);

				listsUtil.shuffle(traineOutput, newOrder);
			}

			/*
			 * for each input j
			 */
			for (int j = 0; j < traineInput.size(); j++)
				updateFunction.update(traineInput.get(j), traineOutput.get(j));
		}
	}

	public void setTraineInput(List<Double[]> inputs) {
		this.traineInput.clear();
		this.traineInput.addAll(inputs);
	}

	public void setTraineOutput(List<Double[]> outputs) {
		traineOutput.clear();
		traineOutput.addAll(outputs);
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
}
