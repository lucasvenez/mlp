package io.github.lucasvenez.stat.regression;

import io.github.lucasvenez.stat.regression.fitting.FittingAlgorithm;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public abstract class Regression {
	
	private FittingAlgorithm fittingAlgorithm;
	
	public FittingAlgorithm getFittingAlgorithm() {
		return fittingAlgorithm;
	}

	public void setFittingAlgorithm(FittingAlgorithm fittingAlgorithm) {
		this.fittingAlgorithm = fittingAlgorithm;
	}
}