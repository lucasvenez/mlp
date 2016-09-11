package io.github.lucasvenez.ann.mlp.neuron;

import io.github.lucasvenez.ann.mlp.function.ActivationFunction;
import io.github.lucasvenez.ann.mlp.layer.InputLayer;

public class InputNeuron extends Neuron<InputLayer> {

	public InputNeuron(ActivationFunction activationFunction, InputLayer inputLayer) {
		super(activationFunction, inputLayer);
	}

	public Double process(Double input) {
		return activationFunction.apply(input);
	}
}
