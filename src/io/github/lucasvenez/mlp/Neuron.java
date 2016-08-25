package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.Layer;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class Neuron {

	private ActivationFunction activationFunction;

	private final List<Double> weights = new ArrayList<Double>();

	private Layer parentLayer;

	/**
	 * 
	 */
	public Neuron() {}

	/**
	 * 
	 * @param activationFunction
	 */
	public Neuron(ActivationFunction activationFunction) {
		this();
		this.setActivationFunction(activationFunction);
	}

	/**
	 * 
	 * @param activationFunction
	 * @param parentLayer
	 */
	public Neuron(ActivationFunction activationFunction, Layer parentLayer) {
		this(activationFunction);
		this.parentLayer = parentLayer;
		this.initializeWeights();
	}

	/**
	 * 
	 * @return
	 */
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * 
	 * @param activationFunction
	 */
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public Double process(Double input) {
		return this.activationFunction.process(input);
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public Double process(List<Double> inputs) throws NeuralNetworkFowardException {

		if (inputs.size() != this.weights.size() + (this.parentLayer.hasBiases() ? 1 : 0))
			throw new NeuralNetworkFowardException(
					"The number of inputs (" + inputs.size() + ") should be equals to the number of weights (" + (this.weights.size() + (this.parentLayer.hasBiases() ? 1 : 0)) + ") at a neuron.");

		Double result = parentLayer.hasBiases() ? weights.get(0) : 0.0;

		for (int i = parentLayer.hasBiases() ? 1 : 0; i < inputs.size(); i++)
			result += weights.get(i) * inputs.get(i);

		return this.activationFunction.process(result);
	}

	/**
	 * 
	 * @param layer
	 */
	public void setLayer(Layer layer) {
		this.parentLayer = layer;
		this.initializeWeights();
	}

	/**
	 * 
	 */
	private void initializeWeights() {

		if (this.parentLayer.getPreviousLayer() != null) {

			int n = this.parentLayer.getPreviousLayer().getNeurons().size() + (this.parentLayer.hasBiases() ? 1 : 0);

			for (int i = 0; i < n; i++)
				weights.add(Math.random());

		} else
			this.weights.clear();
	}

	/**
	 * 
	 * @return
	 */
	public Layer getLayer() {
		return this.parentLayer;
	}
}
