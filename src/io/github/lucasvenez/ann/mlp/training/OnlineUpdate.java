package io.github.lucasvenez.ann.mlp.training;

import io.github.lucasvenez.ann.mlp.MultilayerPerceptron;
import io.github.lucasvenez.ann.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.ann.mlp.layer.ProcessingLayer;
import io.github.lucasvenez.ann.mlp.neuron.ProcessingNeuron;
import io.github.lucasvenez.stat.Stat;

public class OnlineUpdate implements Update {

	private MultilayerPerceptron mlp;

	private Double learningRate = 0.75;

	private Double trainingThreshold = 0.0;

	private final Stat stat = new Stat();

	public OnlineUpdate() {
	}

	@Override
	public void update(Double[] input, Double[] output) throws NeuralNetworkFowardException {

		Double[] result = mlp.apply(input);

		Double[] errors = new Double[result.length];

		/*
		 * for each output neuron calculate the error
		 */
		for (int k = 0; k < result.length; k++)
			errors[k] = output[k] - result[k];

		double errorSum = stat.sum(errors);

		/*
		 * if errors is not acceptable training, otherwise avoid over-training
		 */
		if (!(errorSum >= 0.0 && errorSum <= stat.toPositive(this.trainingThreshold)
				|| errorSum <= 0.0 && errorSum >= stat.toNegative(this.trainingThreshold))) {

			Double[] localGradients = new Double[errors.length];

			/* Update neurons at output layer */ {

				final ProcessingLayer outputLayer = this.mlp.getOutputLayer();

				for (int k = 0; k < errors.length; k++) {

					localGradients[k] = errors[k] * outputLayer.getNeuron(k).derivativeOfLastInducedLocalField();

					this.updateWeights(outputLayer.getNeuron(k), this.learningRate, localGradients[k]);
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
				 * for each neuron at hidden layer l
				 */
				for (int n = 0; n < currentLayer.getNumberOfNeurons(); n++) {

					/*
					 * for each connection between neuron n at layer l and
					 * neuron m at layer l + 1
					 */
					for (int m = 0; m < afterLayer.getNumberOfNeurons(); m++)
						sumLocalGradients += localGradients[m]
								* afterLayer.getNeuron(m).getWeight(n + (afterLayer.hasBiases() ? 1 : 0));

					currentLocalGradients[n] = currentLayer.getNeuron(n).derivativeOfLastInducedLocalField()
							* sumLocalGradients;

					updateWeights(currentLayer.getNeuron(n), this.learningRate, currentLocalGradients[n]);
				}

				localGradients = currentLocalGradients;
			}
		}
	}

	private void updateWeights(ProcessingNeuron neuron, Double learningRate, Double localGradient) {

		for (int i = 0; i < neuron.getWeights().size(); i++) {

			final Double w = neuron.getWeights().get(i) + learningRate * localGradient * neuron.getLastInputs().get(i);

			neuron.getWeights().set(i, w);
		}
	}

	public MultilayerPerceptron getMultlayerPerceptron() {
		return mlp;
	}

	public void setMultlayerPerceptron(MultilayerPerceptron mlp) {
		this.mlp = mlp;
	}

	public Double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(Double learningRate) {
		this.learningRate = learningRate;
	}

	public OnlineUpdate(final MultilayerPerceptron mlp) {
		this.mlp = mlp;
	}
}
