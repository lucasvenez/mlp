package io.github.lucasvenez.mlp.training;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;

public interface Update {

	public void update(Double[] input, Double[] output) throws NeuralNetworkFowardException;
}
