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
	public double process(double input) {
		return this.threshold.process(this.sigmoid.process(input));
	}
	
	public void setThreshold(double threshold) {
		this.threshold.setThreshold(threshold);
	}
	
	public void setAlpha(double alpha) {
		this.sigmoid.setAlpha(alpha);
	}
	
}
