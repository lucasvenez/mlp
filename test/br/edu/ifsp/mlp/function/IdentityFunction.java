package br.edu.ifsp.mlp.function;

import io.github.lucasvenez.mlp.function.ActivationFunction;

public class IdentityFunction implements ActivationFunction {

	@Override
	public double calculate(double input) {
		return input;
	}
}
