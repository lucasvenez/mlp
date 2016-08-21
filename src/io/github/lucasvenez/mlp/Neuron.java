package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;

public class Neuron {

	private ActivationFunction activationFunction;
	
	private final List<Double> weights = new ArrayList<Double>();
	
	public Neuron(ActivationFunction activationFunction) {
		this.setActivationFunction(activationFunction);
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	public Double process(Double input) {
		return this.activationFunction.process(input);
	}

	public Double process(List<Double> inputs) throws NeuralNetworkFowardException {
		
		if (inputs.size() != weights.size())
			throw new NeuralNetworkFowardException("The number of inputs should be equals to the number of weights at a neuron.");
		
		Double result = 0.0;
		
		for (int i = 0; i < inputs.size(); i++)
			result += weights.get(i) * inputs.get(i);
		
		return this.activationFunction.process(result);
	}
}
