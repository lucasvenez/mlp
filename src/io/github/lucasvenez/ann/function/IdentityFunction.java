package io.github.lucasvenez.ann.function;

import io.github.lucasvenez.ann.function.ActivationFunction;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
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
