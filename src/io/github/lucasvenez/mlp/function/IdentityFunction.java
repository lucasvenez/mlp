package io.github.lucasvenez.mlp.function;

import io.github.lucasvenez.mlp.function.ActivationFunction;

public class IdentityFunction implements ActivationFunction {

	@Override
	public Double apply(Double input) {
		return input;
	}
	
	@Override
	public Double derivative(Double input) {
		return 1.0;
	}
}
