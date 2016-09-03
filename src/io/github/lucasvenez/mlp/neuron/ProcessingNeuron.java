package io.github.lucasvenez.mlp.neuron;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.Layer;
import io.github.lucasvenez.mlp.layer.ProcessingLayer;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class ProcessingNeuron extends Neuron<ProcessingLayer> {

	private final List<Double> weights = new ArrayList<Double>();

	public ProcessingNeuron(ActivationFunction activationFunction, ProcessingLayer processingLayer) {
		super(activationFunction, processingLayer);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public Double process(Double input) {
		return super.activationFunction.process(input);
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public Double process(List<Double> inputs) throws NeuralNetworkFowardException {

		if (inputs.size() != this.weights.size())
			throw new NeuralNetworkFowardException(
				"The number of inputs (" + inputs.size() + ") should be equals to the number of weights ("
					+ (this.weights.size() + (this.parentLayer.hasBiases() ? 1 : 0)) + ") at a neuron.");

		Double result = parentLayer.hasBiases() ? weights.get(0) : 0.0;

		for (int i = parentLayer.hasBiases() ? 1 : 0; i < inputs.size(); i++)
			result += weights.get(i) * inputs.get(i);

		return this.activationFunction.process(result);
	}

	/**
	 * 
	 */
	public void initializeWeightsRandomly() {

		int numberOfConnections = this.parentLayer.getPreviousLayer() != null
				? this.parentLayer.getPreviousLayer().getNeurons().size() : 0;

		for (int i = 0; i < numberOfConnections; i++)
			weights.add(Math.random() * (Math.random() < .5 ? -1 : 1));
	}

	/**
	 * 
	 * @param weights
	 */
	public void setWeights(List<Double> weights) {

		this.weights.clear();

		for (Double w : weights)
			this.weights.add(w);
	}

	/**
	 * 
	 * @return
	 */
	public Layer getLayer() {
		return this.parentLayer;
	}

	/**
	 * 
	 * @return
	 */
	public List<Double> getWeights() {
		return this.weights;
	}
}
