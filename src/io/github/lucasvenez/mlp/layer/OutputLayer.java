package io.github.lucasvenez.mlp.layer;

import io.github.lucasvenez.mlp.function.ActivationFunction;

public class OutputLayer extends Layer {

	public OutputLayer() {
		super(false);
	}

	public OutputLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}
}
