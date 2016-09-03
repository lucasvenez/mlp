package io.github.lucasvenez.mlp.function;

import io.github.lucasvenez.mlp.function.ActivationFunction;

import static java.lang.Math.exp;

public class SigmoidFunction implements ActivationFunction {

	private double alpha = 1.0;
	
	public SigmoidFunction() {}
	
	public SigmoidFunction(double alpha) {
		this.alpha = alpha;
	}
	
	@Override
	public double process(double input) {
		return 1.0 / (1.0 + exp(alpha * -input));
	}
	
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
}
