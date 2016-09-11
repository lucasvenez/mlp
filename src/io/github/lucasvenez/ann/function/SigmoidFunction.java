package io.github.lucasvenez.ann.function;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

import io.github.lucasvenez.ann.function.ActivationFunction;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class SigmoidFunction implements ActivationFunction {

	private double alpha = 1.0;
	
	public SigmoidFunction() {}
	
	public SigmoidFunction(double alpha) {
		this.alpha = alpha;
	}
	
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	@Override
	public Double apply(Double input) {
		return 1.0 / (1.0 + exp(-alpha * input));
	}
	
	@Override
	public Double derivative(Double input) {
		return alpha * exp(-alpha * input) / pow((1 + exp(-alpha * input)), 2);
	}
}
