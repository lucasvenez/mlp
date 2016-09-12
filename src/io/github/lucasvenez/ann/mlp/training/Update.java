package io.github.lucasvenez.ann.mlp.training;

import io.github.lucasvenez.exception.NeuralNetworkFowardException;

public interface Update {

	public void update(Double[] input, Double[] output) throws NeuralNetworkFowardException;
}
