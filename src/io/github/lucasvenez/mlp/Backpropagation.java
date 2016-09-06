package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.layer.ProcessingLayer;
import io.github.lucasvenez.utils.ListsUtil;

public class Backpropagation {

	private MultilayerPerceptron mlp;

	private final List<Double[]> traineInput = new ArrayList<Double[]>();

	private final List<Double[]> traineOutput = new ArrayList<Double[]>();

	private int batchSize = 1;

	private int iterations = 20000;

	private boolean randomPresentation = true;

	private double learningRate = 0.7;

	private double momentum = 0.9;

	private ListsUtil<Double[]> listsUtil = new ListsUtil<Double[]>();

	public Backpropagation(final MultilayerPerceptron mlp) {
		this.mlp = mlp;
	}

	public void traine() throws NeuralNetworkFowardException {

		mlp.initializeWeightsWithRandomUniformDistribution();

		/*
		 * for each iteration FIXME add learning rate constraint
		 */
		for (int i = 0; i < iterations; i++) {

			if (randomPresentation) {

				int[] newOrder = listsUtil.shuffle(traineInput);

				listsUtil.shuffle(traineOutput, newOrder);
			}

			/*
			 * for each input j
			 */
			for (int j = 0; j < traineInput.size(); j++) {

				Double[] result = mlp.process(traineInput.get(j));

				Double[] errors = new Double[result.length];

				/*
				 * for each output neuron calculate the error
				 */
				for (int k = 0; k < result.length; k++)
					errors[k] = traineOutput.get(j)[k] - result[k];

				/*
				 * FIXME use batch size for training. It is working only for
				 * one.
				 */
				if (batchSize == 1) {

					Double[] localGradients = new Double[errors.length];

					/* Update neurons at output layer */ {

						final ProcessingLayer outputLayer = this.mlp.getOutputLayer();

						for (int k = 0; k < errors.length; k++) {

							localGradients[k] = errors[k]
									* outputLayer.getNeuron(k).derivativeOfLastInducedLocalField();

							outputLayer.getNeuron(k).updateWeights(this.momentum, this.learningRate, localGradients[k]);
						}
					}

					/*
					 * for each hidden layer
					 */
					for (int l = mlp.getNumberOfHiddenLayers() - 1; l >= 0; l--) {

						final ProcessingLayer afterLayer = mlp.getProcessingLayer(l + 1);

						ProcessingLayer currentLayer = mlp.getProcessingLayer(l);

						Double[] currentLocalGradients = new Double[currentLayer.getNumberOfNeurons()];

						Double sumLocalGradients = 0.0;

						/*
						 * FIXME for each neuron at hidden layer l
						 */
						for (int n = 0; n < currentLayer.getNumberOfNeurons(); n++) {

							/*
							 * for each connection between neuron n at layer l
							 * and neuron m at layer l + 1
							 */
							for (int m = 0; m < afterLayer.getNumberOfNeurons(); m++)
								sumLocalGradients += localGradients[m]
										* afterLayer.getNeuron(m).getWeight(n + (afterLayer.hasBiases() ? 1 : 0));

							currentLocalGradients[n] = currentLayer.getNeuron(n).derivativeOfLastInducedLocalField()
									* sumLocalGradients;

							currentLayer.getNeuron(n).updateWeights(momentum, learningRate, currentLocalGradients[n]);
						}

						localGradients = currentLocalGradients;
					}
				}
			}
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
