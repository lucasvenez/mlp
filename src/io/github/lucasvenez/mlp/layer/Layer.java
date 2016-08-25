package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.Neuron;
import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public abstract class Layer {

	protected final List<Neuron> neurons = new ArrayList<Neuron>();

	protected boolean biases = false;
	
	private Layer previousLayer;

	/**
	 * 
	 */
	public Layer() {}

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public Layer(int numberOfNeurons, ActivationFunction activationFunction) {
		this();
		for (int i = 0; i < numberOfNeurons; i++)
			neurons.add(new Neuron(activationFunction, this));
		
	}
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @param previousLayer
	 */
	public Layer(int numberOfNeurons, ActivationFunction activationFunction, Layer previousLayer) {
		this(numberOfNeurons, activationFunction);
		this.previousLayer = previousLayer;
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {

		List<Double> outputs = new ArrayList<Double>();

		for (Neuron n : neurons)
			outputs.add(n.process(inputs));

		return outputs;
	}

	/**
	 * 
	 * @param biases
	 */
	protected Layer(boolean biases) {
		this.biases = biases;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasBiases() {
		return biases;
	}

	/**
	 * 
	 * @param biases
	 */
	public void setBiases(boolean biases) {
		this.biases = biases;
	}

	/**
	 * 
	 * @return
	 */
	public List<Neuron> getNeurons() {
		return neurons;
	}

	/**
	 * 
	 * @param neuron
	 */
	public void addNeuron(Neuron neuron) {
		neuron.setLayer(this);
		this.neurons.add(neuron);
	}

	/**
	 * 
	 * @return
	 */
	public Layer getPreviousLayer() {
		return previousLayer;
	}

	/**
	 * 
	 * @param previousLayer
	 */
	public void setPreviousLayer(Layer previousLayer) {
		this.previousLayer = previousLayer;
	}
}
