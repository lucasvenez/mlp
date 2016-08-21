package io.github.lucasvenez.mlp.function;

/**
 * The Threshold activation function.
 * @author Lucas Venezian Povoa
 */
public class ThresholdFunction implements ActivationFunction {

	private double threshold = 0.0;
	
	public ThresholdFunction() {
		
	}
	
	public ThresholdFunction(double threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * @param input it is a set of inputs for the function. For the threshold function:
	 * <ul>
	 *    <li><b>induced local field (x)</b>;</li>
	 *    <li><b>threshold (t)</b> [optional with default 0.0].</li>
	 * </ul>
	 * 
	 * This function is calculated as:<br/>
	 * <center>
	 * y = 1 if x >= t and 0 otherwise
	 * </center>
	 * @throws InvalidActivation
	 */
	@Override
	public double process(double x) {

		return x >= threshold ? 1.0 : 0.0;
	}
}
