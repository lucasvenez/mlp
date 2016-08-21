package io.github.lucasvenez.mlp.function;

import io.github.lucasvenez.mlp.function.ActivationFunction;

public class IdentityFunction implements ActivationFunction {

	@Override
	public double process(double input) {
		return input;
	}
}
