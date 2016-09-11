package io.github.lucasvenez.ann.mlp.function;

import static java.lang.Math.max;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class ReLUFunction implements ActivationFunction {

	private Double threshold = 0.0;
	
	@Override
	public Double apply(Double input) {
		return max(this.threshold, input);
	}

	@Override
	public Double derivative(Double input) {
		return input <= 0.0 ? this.threshold : 1.0;
	}
}
