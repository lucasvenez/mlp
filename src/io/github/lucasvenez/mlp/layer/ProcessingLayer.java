package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.neuron.ProcessingNeuron;

public class ProcessingLayer implements Layer {

	protected boolean biases = false;

	private Layer previousLayer;

	private final List<ProcessingNeuron> neurons = 
			new ArrayList<ProcessingNeuron>();
	
	/**
	 * 
	 */
	public ProcessingLayer() {
		super();
	}
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @param previousLayer
	 */
	public ProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction, Layer previousLayer) {
		this(numberOfNeurons, activationFunction);
		this.previousLayer = previousLayer;
	}
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public ProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		this();

		for (int i = 0; i < numberOfNeurons; i++)
			neurons.add(new ProcessingNeuron(activationFunction, this));
	}
	
	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {

		List<Double> outputs = new ArrayList<Double>();

		for (ProcessingNeuron n : neurons)
			outputs.add(n.process(inputs));

		return outputs;
	}
	
	/**
	 * 
	 * @param biases
	 */
	protected ProcessingLayer(boolean biases) {
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

	/**
	 * 
	 * @param neuron
	 */
	public void addNeuron(ProcessingNeuron neuron) {
		neuron.setLayer(this);
		this.neurons.add(neuron);
	}

	/**
	 * 
	 * @param neuronIndex
	 * @return
	 */
	public List<Double> getWeightsByNeuronIndex(Integer neuronIndex) {
		return neurons.get(neuronIndex).getWeights();
	}

	/**
	 * 
	 */
	public void initializeWeightsRandomly() {
		for (ProcessingNeuron n : this.neurons)
			n.initializeWeightsRandomly();
	}
	
	public List<ProcessingNeuron> getNeurons() {
		return this.neurons;
	}
}
