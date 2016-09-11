package io.github.lucasvenez.ann.function;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 * @see <a href="http://goo.gl/ggm241">Hyperbolic Functions</a>
 */
public class HyperbolicTangentFunction implements ActivationFunction {

	@Override
	public Double apply(Double input) {
		return (exp(2.0 * input) - 1.0) / (exp(2.0 * input) + 1.0);
	}

	@Override
	public Double derivative(Double input) {
		return 1.0 - pow(apply(input), 2.0);
	}
}
