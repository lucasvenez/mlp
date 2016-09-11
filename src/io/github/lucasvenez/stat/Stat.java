package io.github.lucasvenez.stat;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class Stat {

	/**
	 * Calculates the mean of a sample
	 * @param sample is an array
	 * @return
	 */
	public double mean(double ... sample) {
		
		double result = 0;
		
		for (double d : sample)
			result += d;
		
		return result / sample.length;
	}
	
	/**
	 * Calculates the standard-deviation of a sample
	 * @param sample
	 * @return
	 */
	public double sd(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return Math.sqrt(result / sample.length);
	}
	
	/**
	 * Calculates the variance of a sample
	 * @param sample
	 * @return
	 */
	public double var(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return result / sample.length;
	}
	
	/**
	 * Calculates the sum of a sample of doubles numbers
	 * @param sample
	 * @return
	 */
	public double sum(double ... sample) {
		
		double result = 0.0;
		
		for (double d : sample)
			result += d;
		
		return result;
	}

	/**
	 * Calculates the sum of a sample of Doubles
	 * @param sample
	 * @return
	 */
	public Double sum(Double ... sample) {
		
		Double result = 0.0;
		
		for (Double d : sample)
			result += d;
		
		return result;
	}
	
	/**
	 * Calculates the sum of a sample of Integers
	 * @param sample an array of Integers
	 * @return sum of the sample array
	 */
	public Integer sum(Integer ... sample) {
		
		Integer result = 0;
		
		for (Integer i : sample)
			result += i;
		
		return result;
	}

	/**
	 * Transforms a number to positive
	 * @param value is a number
	 * @return a positive number
	 */
	public Double toPositive(Double value) {
		return value < 0.0 ? -value : value;
	}

	/**
	 * Transforms a number to negative
	 * @param value is a number
	 * @return a negative number
	 */
	public Double toNegative(Double value) {
		return value > 0.0 ? -value : value;
	}
}
