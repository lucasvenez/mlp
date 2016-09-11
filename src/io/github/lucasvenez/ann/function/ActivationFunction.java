package io.github.lucasvenez.ann.function;

import java.util.function.Function;

public interface ActivationFunction extends Function<Double, Double>{

	public Double apply(Double input);
	
	public Double derivative(Double input);
	
}
