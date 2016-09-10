package io.github.lucasvenez.mlp.function;

public class ThresholdSigmoidFunction implements ActivationFunction {

	private ThresholdFunction threshold;
	
	private SigmoidFunction sigmoid;
	
	public ThresholdSigmoidFunction() {
		this.threshold = new ThresholdFunction();
		this.sigmoid = new SigmoidFunction();
	}
	
	public ThresholdSigmoidFunction(double threshold, double alpha) {
		this.sigmoid = new SigmoidFunction(alpha);
	}
	
	@Override
	public Double apply(Double input) {
		return this.threshold.apply(this.sigmoid.apply(input));
	}
	
	@Override
	public Double derivative(Double input) {
		return this.sigmoid.derivative(input);
	}
	
	public void setThreshold(double threshold) {
		this.threshold.setThreshold(threshold);
	}
	
	public void setAlpha(double alpha) {
		this.sigmoid.setAlpha(alpha);
	}
	
}
