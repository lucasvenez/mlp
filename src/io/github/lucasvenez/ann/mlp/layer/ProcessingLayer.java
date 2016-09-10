package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.neuron.ProcessingNeuron;

public class ProcessingLayer implements Layer {

	protected boolean biases = true;

	private Layer previousLayer;

	protected final List<ProcessingNeuron> neurons = new ArrayList<ProcessingNeuron>();

	/**
	 * 
	 */
	public ProcessingLayer() {}

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

		final List<Double> outputs = new ArrayList<Double>();

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

	public void setWeights(Double... weights) {

		int j = 0;

		for (ProcessingNeuron neuron : this.neurons) {

			Double[] w = new Double[this.previousLayer.getNeurons().size() + (this.hasBiases() ? 1 : 0)];

			for (int i = 0; i < w.length; i++)
				w[i] = weights[j++];

			neuron.setWeights(w);
		}
	}

	public int getNumberOfIncomingConnections() {
		return (this.previousLayer.getNeurons().size() + (this.hasBiases() ? 1 : 0)) * this.neurons.size();
	}

	public ProcessingNeuron getNeuron(int index) {
		return this.neurons.get(index);
	}

	@Override
	public int getNumberOfNeurons() {
		return this.neurons.size();
	}
}
