package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.Neuron;
import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;

public abstract class Layer {

	protected final List<Neuron> neurons = new ArrayList<Neuron>();

	protected boolean biases = false;

	public Layer() {
	}

	public Layer(int numberOfNeurons, ActivationFunction activationFunction) {

		for (int i = 0; i < numberOfNeurons; i++)
			neurons.add(new Neuron(activationFunction));
	}

	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {

		List<Double> outputs = new ArrayList<Double>();

		for (Neuron n : neurons)
			outputs.add(n.process(inputs));

		return outputs;
	}

	protected Layer(boolean biases) {
		this.biases = biases;
	}

	public boolean hasBiases() {
		return biases;
	}

	public void setBiases(boolean biases) {
		this.biases = biases;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void addNeuron(Neuron neuron) {
		this.neurons.add(neuron);
	}
}
